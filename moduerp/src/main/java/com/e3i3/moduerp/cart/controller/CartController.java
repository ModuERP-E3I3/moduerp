package com.e3i3.moduerp.cart.controller;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.e3i3.moduerp.cart.model.dto.CartDTO;
import com.e3i3.moduerp.cart.model.service.CartService;
import com.e3i3.moduerp.company.model.service.CompanyService;
import com.e3i3.moduerp.module.model.dto.ModuleDTO;
import com.e3i3.moduerp.module.model.service.ModuleService;
import com.e3i3.moduerp.pay.model.service.PayService;

@Controller
@RequestMapping("/")
public class CartController {

	@Autowired
	private CartService cartService;

	@Autowired
	private ModuleService moduleService;

	@Autowired
	private CompanyService companyService;

	@Autowired
	private PayService payService;

	private boolean allCartListsAreNull(List<CartDTO> cartList) {
		for (CartDTO cart : cartList) {
			// 하나라도 유효한 값이 있으면 false 반환
			if (cart.getCartList() != null && !cart.getCartList().trim().isEmpty()) {
				return false;
			}
		}
		// 모든 cartList가 null이거나 비어 있으면 true 반환
		return true;
	}

	@RequestMapping(value = "/forwardCart.do", method = RequestMethod.GET)
	public String buyModule(HttpSession session, Model model) {
		String bizNumber = (String) session.getAttribute("biz_number");
		String uuid = (String) session.getAttribute("uuid");

		// bizNumber 일치하는 장바구니 데이터 가져오기
		List<CartDTO> cartList = cartService.selectCartListByBizNumber(bizNumber);
		
		String companyCard = companyService.selectComplayCardExitence(bizNumber);

		boolean companyCardExistence = false;
		if (companyCard == null) {
			companyCardExistence = false;
			model.addAttribute("companyCardExistence", companyCardExistence);
		} else if (companyCard != null) {
			companyCardExistence = true;
			model.addAttribute("companyCardExistence", companyCardExistence);
			model.addAttribute("companyCard", companyCard);
		}
		System.out.println("카드 여부" + companyCardExistence);

		// 장바구니가 없거나 모든 cartList 컬럼이 null일 때 처리
		if (cartList == null || cartList.isEmpty() || allCartListsAreNull(cartList)) {
			System.out.println("장바구니 비었음 또는 모든 cartList 컬럼이 null");
			model.addAttribute("modules", new ArrayList<>()); // 빈 리스트 전달
			return "cart/cart";
		}

		// cartLists 문자열을 분리하여 리스트로 변환
		List<String> moduleGrades = new ArrayList<>();
		for (CartDTO cart : cartList) {
			String cartLists = cart.getCartList(); // 특정 컬럼 값 추출
			System.out.println("**********************");
			System.out.println("cartLists : " + cartLists); // 출력

			// 쉼표로 구분된 문자열을 배열로 변환하고, 각 값을 리스트에 추가 (쉼표는 제거됨)
			String[] gradesArray = cartLists.split(",");

			// 배열의 각 요소에서 공백을 제거하고 리스트에 추가
			for (String grade : gradesArray) {
				if (!grade.trim().isEmpty()) {
					moduleGrades.add(grade.trim()); // 공백 제거 후 리스트에 추가
				}
			}
		}

		// moduleGrades 리스트를 이용해 MODULE 테이블에서 데이터 조회
		List<ModuleDTO> modules = moduleService.selectModulesByGrades(moduleGrades);

		// 합계를 저장할 변수 선언
		int totalModulePrice = 0;

		// 반복문을 통해 모든 modulePrice 값을 합산
		for (ModuleDTO module : modules) {
			totalModulePrice += module.getModulePrice();
		}

		

		// company에 구매한 모듈이 뭐가 있는지
		String purchasedModule = companyService.selectPurchasedModule(bizNumber);
		boolean purchasedModuleExistence = false;
		String paymentDate = payService.selectPaymentDate(bizNumber);

		if (paymentDate != null) {
			// 오늘 날짜 가져오기
			LocalDate today = LocalDate.now();

			// paymentDate 문자열을 정수로 변환
			int paymentDay = Integer.parseInt(paymentDate.trim());

			// 이번 달 해당 일자 생성 (유효성 검사 포함)
			LocalDate thisMonthPaymentDate = today.withDayOfMonth(Math.min(paymentDay, today.lengthOfMonth()));

			// 다음 결제일 계산 (이번 달 결제일이 지났으면 다음 달로)
			LocalDate nextPaymentDate = !today.isAfter(thisMonthPaymentDate) ? thisMonthPaymentDate
					: thisMonthPaymentDate.plusMonths(1);

			// 남은 일 수 계산
			long daysUntilNextPayment = ChronoUnit.DAYS.between(today, nextPaymentDate);
			System.out.println("남은 일 수: " + daysUntilNextPayment + "일");
			if (purchasedModule != null) {
				purchasedModuleExistence = true;
				model.addAttribute("purchasedModuleExistence", purchasedModuleExistence);
				model.addAttribute("purchasedModule", purchasedModule);
				model.addAttribute("daysUntilNextPayment", daysUntilNextPayment);
			} else if (purchasedModule == null) {
				purchasedModuleExistence = false;
				model.addAttribute("purchasedModuleExistence", purchasedModuleExistence);
			}
		}

		// 조회된 모듈 데이터를 모델에 추가하여 JSP로 전달
		model.addAttribute("modules", modules);
		model.addAttribute("totalModulePrice", totalModulePrice);

		return "cart/cart"; // 페이지 반환
	}

	@RequestMapping(value = "/cartSelectDelete.do", method = RequestMethod.POST)
	public String deleteSelectedModules(@RequestParam("selectedModules") List<String> selectedModules,
			HttpSession session) {

		String bizNumber = (String) session.getAttribute("biz_number");

		// bizNumber로 CART_LIST 조회
		String existingCartList = cartService.getCartListByBizNumber(bizNumber);

		// 기존 Cart List를 List로 변환
		List<String> cartList = new ArrayList<>(Arrays.asList(existingCartList.split(",")));

		// selectedModules에 있는 값 제거
		cartList.removeAll(selectedModules);

		// 남은 항목을 쉼표로 구분된 문자열로 다시 생성
		String updatedCartList = String.join(",", cartList);

		// 업데이트된 Cart List 저장 (DB 업데이트 로직 필요)
		cartService.updateCartListByBizNumber(bizNumber, updatedCartList);

		// 삭제 후 페이지 리다이렉트
		return "redirect:/cartPage.do";
	}

	@RequestMapping(value = "/deleteAllItems.do", method = RequestMethod.GET)
	public String deleteAllItems(HttpSession session) {
		String bizNumber = (String) session.getAttribute("biz_number");

		// CART_LIST 컬럼 초기화
		cartService.clearCartListByBizNumber(bizNumber);

		// 삭제 후 페이지 리다이렉트
		return "redirect:/forwardCart.do";
	}

}
