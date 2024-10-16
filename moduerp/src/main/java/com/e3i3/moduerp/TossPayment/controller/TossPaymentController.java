package com.e3i3.moduerp.TossPayment.controller;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.json.JSONObject; // JSON 파싱을 위한 라이브러리
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.e3i3.moduerp.billingInfo.model.dto.BillingInfoDTO;
import com.e3i3.moduerp.billingInfo.model.service.BillingInfoService;
import com.e3i3.moduerp.cart.model.service.CartService;
import com.e3i3.moduerp.company.model.service.CompanyService;
import com.e3i3.moduerp.module.model.dto.ModuleDTO;
import com.e3i3.moduerp.module.model.service.ModuleService;
import com.e3i3.moduerp.pay.model.dto.PayDTO;
import com.e3i3.moduerp.pay.model.service.PayService;
import com.e3i3.moduerp.paylog.model.dto.PayLogDTO;
import com.e3i3.moduerp.paylog.model.service.PayLogService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class TossPaymentController {

	@Autowired
	private BillingInfoService billingInfoService;

	@Autowired
	private CompanyService companyService;

	@Autowired
	private ModuleService moduleService;

	@Autowired
	private PayService payService;

	@Autowired
	private PayLogService payLogService;

	@Autowired
	private CartService cartService;

	// 결제 등록 화면으로 이동 (card_registration.jsp로 이동)
	@RequestMapping("payment.do")
	public String forwardMain() {
		return "regular_payment/card_registration";
	}

	// 카드 등록 후 정기 결제 화면(regular_payment.jsp)으로 이동
	@RequestMapping("regularPayment.do")
	public String forwardToRegularPayment(HttpSession session, Model model) {
		String uuid = (String) session.getAttribute("uuid");

		model.addAttribute("customerKey", uuid); // 'customerKey'라는 이름으로 UUID 저장
		return "regular_payment/regular_payment";
	}

	// 카드 등록 성공 시 빌링키 발급 처리
	@RequestMapping("regularPayment/success.do")
	public ModelAndView billingSuccess(@RequestParam("customerKey") String customerKey,
			@RequestParam("authKey") String authKey, HttpSession session) {
		String bizNumber = (String) session.getAttribute("biz_number");

		// API 호출 후 JSON 응답 받기
		String responseBody = issueBillingKey(customerKey, authKey);

		try {
			// JSON 응답을 BillingInfoDTO로 매핑
			ObjectMapper objectMapper = new ObjectMapper();
			BillingInfoDTO billingInfo = objectMapper.readValue(responseBody, BillingInfoDTO.class);

			String CardBillingId = bizNumber + "BK" + System.currentTimeMillis();

			// 현재 시간 설정 (Calendar 사용)
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.HOUR_OF_DAY, Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
			calendar.set(Calendar.MINUTE, Calendar.getInstance().get(Calendar.MINUTE));
			calendar.set(Calendar.SECOND, Calendar.getInstance().get(Calendar.SECOND));

			Timestamp createDate = new Timestamp(calendar.getTimeInMillis());

			// 1. 수동으로 추가 필드 설정
			billingInfo.setCardBillingId(CardBillingId); // 고유 ID 생성
			billingInfo.setCreatedAt(createDate); // 현재 시간 설정
			billingInfo.setBizNumber(bizNumber);

			// DTO 정보 출력
			System.out.println("BillingInfoDTO 정보: " + billingInfo);

			// 2. DB에 저장
			billingInfoService.insertBillingInfo(billingInfo);
			companyService.updateCompanyCardExistence(CardBillingId, bizNumber);

		} catch (Exception e) {
			throw new RuntimeException("Failed to process billing information: " + e.getMessage(), e);
		}

		// 성공 페이지로 이동
		ModelAndView mav = new ModelAndView("regular_payment/billing_success");
		mav.addObject("responseBody", responseBody);
		return mav;
	}

	// 카드 등록 실패 시 실패 화면으로 이동
	@RequestMapping("regularPayment/fail.do")
	public String billingFail(@RequestParam("code") String errorCode, @RequestParam("message") String errorMessage) {
		return "regular_payment/billing_fail";
	}

	// 빌링키 발급 API 호출 메서드
	private String issueBillingKey(String customerKey, String authKey) {
		try {
			// HttpClient 생성
			HttpClient client = HttpClient.newHttpClient();

			// 요청 본문 생성
			String requestBody = String.format("{\"authKey\":\"%s\", \"customerKey\":\"%s\"}", authKey, customerKey);

			// API 요청 생성
			HttpRequest request = HttpRequest.newBuilder()
					.uri(URI.create("https://api.tosspayments.com/v1/billing/authorizations/issue"))
					.header("Authorization", "Basic dGVzdF9za195WnFta0tlUDhnTmI0UnpYUDBHWXJiUVJ4QjlsOg==") // Base64
																											// 인코딩된 인증 키
					.header("Content-Type", "application/json").POST(HttpRequest.BodyPublishers.ofString(requestBody))
					.build();

			// API 호출 후 응답 받기
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

			// 응답 상태 코드 확인 후 성공 시 응답 본문 반환
			if (response.statusCode() == 200) {
				return response.body(); // 성공 시 응답 데이터(JSON) 반환
			} else {
				throw new RuntimeException("Failed to issue billing key: " + response.statusCode());
			}

		} catch (Exception e) {
			throw new RuntimeException("Billing key request failed: " + e.getMessage(), e);
		}
	}

	@PostMapping("/createPayment.do")
	public String createPayment(@RequestParam("moduleGrades") List<String> moduleGrades, Model model,
			HttpSession session) {
		// 1. 세션에서 bizNumber 가져오기
		String bizNumber = (String) session.getAttribute("biz_number");
		String uuid = (String) session.getAttribute("uuid");

		// 2. BillingInfo 데이터 조회
		BillingInfoDTO billingData = billingInfoService.selectBillingInfoData(bizNumber);
		if (billingData == null) {
			System.out.println("해당 bizNumber에 대한 데이터가 없습니다.");
			return "error/noBillingInfo"; // 빌링 정보가 없을 경우 처리
		}

		// List<String>을 쉼표(,)로 연결된 String으로 변환
		String moduleGradesStr = String.join(",", moduleGrades);

		// 3. MODULE 테이블에서 moduleGrades와 일치하는 데이터 조회
		List<ModuleDTO> modules = moduleService.getModulesByGrades(moduleGrades);
		model.addAttribute("modules", modules); // JSP로 데이터 전달

		// 4. 선택한 모듈의 총 가격 계산
		int totalAmount = modules.stream().mapToInt(ModuleDTO::getModulePrice).sum();

		System.out.println("총 결제 금액: " + totalAmount);

		// 5. 선택한 모듈 이름을 콤마(,)로 구분하여 결제 이름 구성
		// 모듈 이름을 콤마로 구분하여 생성
		String orderName = modules.stream().map(ModuleDTO::getModuleName).collect(Collectors.joining(", "));

		// "ModuERP 구독: (orderName)" 형식으로 문자열 생성
		String fullOrderName = "ModuERP 구독: (" + orderName + ")";

		System.out.println("주문 이름: " + orderName);

		// 6. 주문 ID 생성: bizNumber + "OD" + System.currentTimeMillis()
		String orderId = bizNumber + "OD" + System.currentTimeMillis();
		System.out.println("주문 ID: " + orderId);

		// 5. 'moduleName : modulePrice' 형식으로 변환하여 결제 항목 구성
		String paymentItems = modules.stream().map(module -> module.getModuleName() + " : " + module.getModulePrice())
				.collect(Collectors.joining(", "));

		// 7. 결제 API 호출 로직
		try {
			String billingKey = billingData.getBillingKey();
			String customerKey = billingData.getCustomerKey();

			// Tosspayments API 요청 생성
			HttpRequest request = HttpRequest.newBuilder()
					.uri(URI.create("https://api.tosspayments.com/v1/billing/" + billingKey))
					.header("Authorization", "Basic dGVzdF9za195WnFta0tlUDhnTmI0UnpYUDBHWXJiUVJ4QjlsOg==")
					.header("Content-Type", "application/json")
					.POST(HttpRequest.BodyPublishers
							.ofString("{\"customerKey\":\"" + customerKey + "\"," + "\"amount\":" + totalAmount + ","
									+ "\"orderId\":\"" + orderId + "\"," + "\"orderName\":\"" + fullOrderName + "\"}"))
					.build();

			// API 호출 및 응답 처리
			HttpClient client = HttpClient.newHttpClient();
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

			// 응답 출력 (로그용)
			System.out.println("결제 API 응답: " + response.body());

			// 응답 JSON에서 status 값 추출
			// JSON 응답 파싱
			JSONObject jsonResponse = new JSONObject(response.body());
			String status = jsonResponse.getString("status");

			String issuerCode = jsonResponse.getJSONObject("card").getString("issuerCode");
			String acquirerCode = jsonResponse.getJSONObject("card").getString("acquirerCode");

			String issuerCodeName = CodeMapper.getCardName(issuerCode);
			String acquirerCodeName = CodeMapper.getCardName(acquirerCode);

			String currency = jsonResponse.getString("currency");
			String cardNumber = jsonResponse.getJSONObject("card").getString("number");
			String cardType = jsonResponse.getJSONObject("card").getString("cardType");
			String ownerType = jsonResponse.getJSONObject("card").getString("ownerType");
			String approveNo = jsonResponse.getJSONObject("card").getString("approveNo");
			int total_amount = jsonResponse.getInt("totalAmount");
			int suppliedAmount = jsonResponse.getInt("suppliedAmount");
			int vat = jsonResponse.getInt("vat");
			Timestamp requestedAt = Timestamp
					.valueOf(jsonResponse.getString("requestedAt").replace("T", " ").substring(0, 19));
			String approvedAt = jsonResponse.getString("approvedAt");
			String mId = jsonResponse.getString("mId");
			String paymentKey = jsonResponse.getString("paymentKey");

			// 8. PayDTO 생성 및 값 설정
			String payId = bizNumber + "PY";
			Timestamp firstPayDate = new Timestamp(System.currentTimeMillis());

			// 현재 날짜의 '일(DAY)'만 추출
			String dayOfMonth = String.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));

			PayDTO payDTO = new PayDTO();
			payDTO.setPayId(payId);
			payDTO.setPayPrice(total_amount);
			payDTO.setBizNumber(bizNumber);
			payDTO.setFirstPayDate(firstPayDate);
			payDTO.setPaymentItem(paymentItems);
			payDTO.setPaymentRequestDay(dayOfMonth); // 현재 날짜의 일(DAY)만 설정
			payDTO.setPayStatus('Y');
			payDTO.setUuid(uuid);

			payService.insertPay(payDTO);

			String payLogId = bizNumber + "PY" + System.currentTimeMillis();

			PayLogDTO payLogDTO = new PayLogDTO();
			payLogDTO.setLogId(payLogId);
			payLogDTO.setPayId(payId);
			payLogDTO.setStatus(status);
			payLogDTO.setDetails(paymentItems);
			payLogDTO.setBizNumber(bizNumber);
			payLogDTO.setIssuerCode(issuerCodeName);
			payLogDTO.setAcquirerCode(acquirerCodeName);
			payLogDTO.setCurrency(currency);
			payLogDTO.setCardNumber(cardNumber);
			payLogDTO.setCardType(cardType);
			payLogDTO.setOwnerType(ownerType);
			payLogDTO.setApproveNo(approveNo);
			payLogDTO.setTotalAmount(total_amount);
			payLogDTO.setSuppliedAmount(suppliedAmount);
			payLogDTO.setVat(vat);
			payLogDTO.setRequestedAt(requestedAt);
			payLogDTO.setApprovedAt(approvedAt);
			payLogDTO.setmId(mId);
			payLogDTO.setPaymentKey(paymentKey);
			payLogDTO.setOrderId(orderId);

			payLogService.insertPayLog(payLogDTO);

			cartService.deleteCartList(bizNumber);

			companyService.insertModuleGradesOfCompany(moduleGradesStr, bizNumber);
		} catch (Exception e) {
			e.printStackTrace();
			return "error/paymentFailed"; // 결제 실패 시 처리
		}

		// 8. 결과를 표시할 JSP로 이동
		return "payment/paymentResult";
	}

	// 추가 결제
	@PostMapping("/createAdditionalPayment.do")
	public String createAdditionalPayment(@RequestParam("moduleGrades") List<String> moduleGrades,
			@RequestParam("totalModulePrice") int totalModulePrice, @RequestParam("thisMonthPrice") int thisMonthPrice,
			HttpSession session) {
		// 1. 세션에서 bizNumber 가져오기
		String bizNumber = (String) session.getAttribute("biz_number");
		String uuid = (String) session.getAttribute("uuid");

		// List<String>을 쉼표(,)로 연결된 String으로 변환
		String moduleGradesStr = String.join(",", moduleGrades);
		System.out.println("구매한 새로운 모듈 등급" + moduleGradesStr);
		System.out.println("list 모듈 등급" + moduleGrades);
		// 구매한 모듈 등급 가져오기
		String purchasedModuleGrades = companyService.selectPurchasedModule(bizNumber);
		System.out.println("이미 구매한 모듈 등급" + purchasedModuleGrades);

		String combinedModuleGrades = purchasedModuleGrades + "," + moduleGradesStr;
		System.out.println("합친 모듈 등급 : " + combinedModuleGrades);

		// 2. BillingInfo 데이터 조회
		BillingInfoDTO billingData = billingInfoService.selectBillingInfoData(bizNumber);
		if (billingData == null) {
			System.out.println("해당 bizNumber에 대한 데이터가 없습니다.");
			return "error/noBillingInfo"; // 빌링 정보가 없을 경우 처리
		}

		String billingKey = billingData.getBillingKey();
		String customerKey = billingData.getCustomerKey();
		String orderId = bizNumber + "OD" + System.currentTimeMillis();

		// 3. MODULE 테이블에서 moduleGrades와 일치하는 데이터 조회
		List<ModuleDTO> modules = moduleService.getModulesByGrades(moduleGrades);

		// 5. 선택한 모듈 이름을 콤마(,)로 구분하여 결제 이름 구성
		// 모듈 이름을 콤마로 구분하여 생성
		String orderName = modules.stream().map(ModuleDTO::getModuleName).collect(Collectors.joining(", "));

		// 새로 추가할 moduleName과 modulePrice 결합 로직
		String moduleNameWithPrice = modules.stream()
				.map(module -> module.getModuleName() + " : " + module.getModulePrice())
				.collect(Collectors.joining(", "));
		// 로그 출력 확인
		System.out.println("모듈 이름과 가격: " + moduleNameWithPrice);

		// "ModuERP 구독: (orderName)" 형식으로 문자열 생성
		String fullOrderName = "ModuERP 추가 구독: (" + orderName + ")";

		try {
			HttpRequest request = HttpRequest.newBuilder()
					.uri(URI.create("https://api.tosspayments.com/v1/billing/" + billingKey))
					.header("Authorization", "Basic dGVzdF9za195WnFta0tlUDhnTmI0UnpYUDBHWXJiUVJ4QjlsOg==")
					.header("Content-Type", "application/json")
					.POST(HttpRequest.BodyPublishers
							.ofString("{\"customerKey\":\"" + customerKey + "\"," + "\"amount\":" + thisMonthPrice + ","
									+ "\"orderId\":\"" + orderId + "\"," + "\"orderName\":\"" + fullOrderName + "\"}"))
					.build();

			// API 호출 및 응답 처리
			HttpClient client = HttpClient.newHttpClient();
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

			// 응답 출력 (로그용)
			System.out.println("추가 결제 API 응답: " + response.body());

			// 응답 JSON에서 status 값 추출
			// JSON 응답 파싱
			JSONObject jsonResponse = new JSONObject(response.body());
			String status = jsonResponse.getString("status");

			String issuerCode = jsonResponse.getJSONObject("card").getString("issuerCode");
			String acquirerCode = jsonResponse.getJSONObject("card").getString("acquirerCode");

			String issuerCodeName = CodeMapper.getCardName(issuerCode);
			String acquirerCodeName = CodeMapper.getCardName(acquirerCode);

			String currency = jsonResponse.getString("currency");
			String cardNumber = jsonResponse.getJSONObject("card").getString("number");
			String cardType = jsonResponse.getJSONObject("card").getString("cardType");
			String ownerType = jsonResponse.getJSONObject("card").getString("ownerType");
			String approveNo = jsonResponse.getJSONObject("card").getString("approveNo");
			int total_amount = jsonResponse.getInt("totalAmount");
			int suppliedAmount = jsonResponse.getInt("suppliedAmount");
			int vat = jsonResponse.getInt("vat");
			Timestamp requestedAt = Timestamp
					.valueOf(jsonResponse.getString("requestedAt").replace("T", " ").substring(0, 19));
			String approvedAt = jsonResponse.getString("approvedAt");
			String mId = jsonResponse.getString("mId");
			String paymentKey = jsonResponse.getString("paymentKey");

			String purchasedModules = payService.selectPurchasedModules(bizNumber);
			// 두 문자열을 쉼표로 연결 / 모듈 이름 가격 합친거
			String combinedModules = purchasedModules + ", " + moduleNameWithPrice;

			// 기존 페이 모듈 가격
			int existingPrice = payService.selectPayPrice(bizNumber);

			// 최종 가격
			int finalPrice = existingPrice + totalModulePrice;

			payService.updatePayPricePaymentItemByBizNumber(combinedModules, finalPrice, bizNumber);

			companyService.updateModuleGrades(combinedModuleGrades, bizNumber);

			cartService.deleteCartList(bizNumber);
			
			String existingPayId = payService.selectPayID(bizNumber);
			
			String payLogId = bizNumber + "PY" + System.currentTimeMillis();

			PayLogDTO payLogDTO = new PayLogDTO();
			payLogDTO.setLogId(payLogId);
			payLogDTO.setPayId(existingPayId);
			payLogDTO.setStatus(status);
			payLogDTO.setDetails(moduleNameWithPrice);
			payLogDTO.setBizNumber(bizNumber);
			payLogDTO.setIssuerCode(issuerCodeName);
			payLogDTO.setAcquirerCode(acquirerCodeName);
			payLogDTO.setCurrency(currency);
			payLogDTO.setCardNumber(cardNumber);
			payLogDTO.setCardType(cardType);
			payLogDTO.setOwnerType(ownerType);
			payLogDTO.setApproveNo(approveNo);
			payLogDTO.setTotalAmount(total_amount);
			payLogDTO.setSuppliedAmount(suppliedAmount);
			payLogDTO.setVat(vat);
			payLogDTO.setRequestedAt(requestedAt);
			payLogDTO.setApprovedAt(approvedAt);
			payLogDTO.setmId(mId);
			payLogDTO.setPaymentKey(paymentKey);
			payLogDTO.setOrderId(orderId);

			payLogService.insertPayLog(payLogDTO);

			

		} catch (Exception e) {
			e.printStackTrace();
			return "error/paymentFailed"; // 결제 실패 시 처리
		}
		return "payment/";
	}
}
