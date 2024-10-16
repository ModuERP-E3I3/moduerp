package com.e3i3.moduerp.scheduling;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.e3i3.moduerp.TossPayment.controller.CodeMapper;
import com.e3i3.moduerp.billingInfo.model.dto.BillingInfoDTO;
import com.e3i3.moduerp.billingInfo.model.service.BillingInfoService;
import com.e3i3.moduerp.pay.model.dto.PayDTO;
import com.e3i3.moduerp.pay.model.service.PayService;
import com.e3i3.moduerp.paylog.model.dto.PayLogDTO;
import com.e3i3.moduerp.paylog.model.service.PayLogService;

@Configuration
@EnableScheduling
public class SchedulingController {

	@Autowired
	private PayService payService;

	@Autowired
	private BillingInfoService billingInfoService;

	@Autowired
	private PayLogService payLogService;

	// 초 분 시 일 월(* 매월) 요일(? = 요일 무시)
	@Scheduled(cron = "0 0 0 * * ?") // 실제 실행 코드 매일 검사
//	@Scheduled(cron = "0 * * * * ?") // 테스트 코드
	public void processSubscriptionPayment() {
		System.out.println("스케줄러 메서드 실행");

		String today = String.valueOf(LocalDate.now().getDayOfMonth());
		System.out.println("스케줄링 실행: 오늘의 결제 요청 처리 - 일자: " + today);

		// 1. 오늘의 결제 요청 데이터 조회
		List<PayDTO> payList = payService.getPaymentsByRequestDay(today);

		// 2. bizNumber 리스트 추출
		List<String> bizNumbers = payList.stream().map(PayDTO::getBizNumber).distinct().collect(Collectors.toList());

		// 3. BILLING_INFO 데이터 조회
		List<BillingInfoDTO> billingInfoList = billingInfoService.getBillingInfoByBizNumbers(bizNumbers);

		System.out.println("BILLING_INFO 데이터:");

		// 4. BILLING_INFO와 PAY 데이터를 매칭하여 API 호출 수행
		for (BillingInfoDTO billingInfo : billingInfoList) {
			// 해당 billingInfo와 매칭되는 모든 PayDTO 찾기
			List<PayDTO> matchingPays = payList.stream()
					.filter(pay -> pay.getBizNumber().equals(billingInfo.getBizNumber())).collect(Collectors.toList());

			// 각 PayDTO에 대해 개별 API 호출 및 로그 저장
			for (PayDTO pay : matchingPays) {
				try {
					// Tosspayments API 호출
					String billingKey = billingInfo.getBillingKey();
					int totalAmount = pay.getPayPrice();
					String orderId = billingInfo.getBizNumber() + "OD" + System.currentTimeMillis();
					System.out.println("orderId : " + orderId);
					String fullOrderName = pay.getPaymentItem();
					String payId = pay.getPayId();

					HttpRequest request = HttpRequest.newBuilder()
							.uri(URI.create("https://api.tosspayments.com/v1/billing/" + billingKey))
							.header("Authorization", "Basic dGVzdF9za195WnFta0tlUDhnTmI0UnpYUDBHWXJiUVJ4QjlsOg==")
							.header("Content-Type", "application/json")
							.POST(HttpRequest.BodyPublishers.ofString("{\"customerKey\":\""
									+ billingInfo.getCustomerKey() + "\"," + "\"amount\":" + totalAmount + ","
									+ "\"orderId\":\"" + orderId + "\"," + "\"orderName\":\"" + fullOrderName + "\"}"))
							.build();

					HttpClient client = HttpClient.newHttpClient();
					HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

					// API 응답 출력
					System.out.println("API 응답: " + response.body());

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
					String orderName = jsonResponse.getString("orderName");

					// 로그 ID 생성
					String payLogId = billingInfo.getBizNumber() + "PY" + System.currentTimeMillis();

					// PayLogDTO 객체 생성 및 데이터 설정
					PayLogDTO payLogDTO = new PayLogDTO();
					payLogDTO.setLogId(payLogId);
					payLogDTO.setPayId(payId);
					payLogDTO.setStatus(status);
					payLogDTO.setDetails(orderName);
					payLogDTO.setBizNumber(billingInfo.getBizNumber());
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

					// PayLog 저장
					payLogService.insertPayLog(payLogDTO);

				} catch (Exception e) {
					System.out.println("API 호출 중 오류 발생: " + e.getMessage());
					e.printStackTrace();
				}
			}
		}
	}

}
