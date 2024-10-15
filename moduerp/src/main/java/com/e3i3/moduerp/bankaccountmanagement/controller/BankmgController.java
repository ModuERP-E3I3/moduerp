package com.e3i3.moduerp.bankaccountmanagement.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.e3i3.moduerp.bankaccountmanagement.model.dto.Bankmg;
import com.e3i3.moduerp.bankaccountmanagement.model.service.BankmgService;

@Controller
public class BankmgController {

	@Autowired
	private BankmgService bankmgService;

	// 계좌 목록 조회
	@RequestMapping("/bankmg.do")
	public String getmgList(Model model) {
		List<Bankmg> mgs = bankmgService.getAllmgs();
		model.addAttribute("bankmg", mgs);
		return "bankmg/bankmg"; // bankmg.jsp 파일로 이동
	}

	// 계좌 상세 보기
	@RequestMapping("/bankmgDetail.do")
	public String getBankmgDetail(@RequestParam("bankNumber") String bankNumber, HttpSession session, Model model) {
		String bizNumber = (String) session.getAttribute("biz_number"); // 세션에서 bizNumber 가져오기

		List<Bankmg> bankmgList = bankmgService.getmgById(bankNumber, bizNumber);
		model.addAttribute("bankmgList", bankmgList);
		return "bankmg/bankmgDetail"; // 상세 뷰 JSP로 이동
	}

	// 계좌 등록 폼 이동
	@RequestMapping("/bankmgAddForm.do")
	public String bankmgAddForm() {
		return "bankmg/bankmgAddForm";
	}

	// 계좌 등록 처리
	@RequestMapping(value = "bankAddForm.do", method = RequestMethod.POST)
	public String bankAddmg(@RequestParam("bankName") String bankName, @RequestParam("bankNumber") String bankNumber,
			@RequestParam("bankHolder") String bankHolder, @RequestParam("transactionPrice") int transactionPrice,
			@RequestParam("transactionType") String transactionType,
			@RequestParam("transactionDate") java.sql.Date transactionDate, HttpSession session) {

		String bizNumber = (String) session.getAttribute("biz_number");

		String bankid = bizNumber + "BA" + System.currentTimeMillis();

		Bankmg bankmgdto = new Bankmg();
		bankmgdto.setBankId(bankid);
		bankmgdto.setBankName(bankName);
		bankmgdto.setBankNumber(bankNumber);
		bankmgdto.setBankHolder(bankHolder);
		bankmgdto.setTransactionPrice(transactionPrice);
		bankmgdto.setTransactionType(transactionType);
		bankmgdto.setTransactionDate(transactionDate);
		bankmgdto.setBizNumber(bizNumber);

		bankmgService.insertmg(bankmgdto);
		return "redirect:/bankmg.do";
	}

	// 계좌 수정 폼 이동
	@RequestMapping("/bankmgUpdateForm.do")
	public String bankmgUpdateForm(@RequestParam("bankNumber") String bankNumber,
			@RequestParam("bizNumber") String bizNumber, Model model) {

		// 선택한 행의 정보를 데이터베이스에서 가져옴
		List<Bankmg> bankmgList = bankmgService.getmgById(bankNumber, bizNumber);

		if (!bankmgList.isEmpty()) {
			Bankmg bankmg = bankmgList.get(0); // 첫 번째 요소 선택
			model.addAttribute("bankmg", bankmg); // 모델에 추가
		}

		return "bankmg/bankmgUpdateForm"; // 수정 폼으로 이동
	}

	// 계좌 수정 처리
	@RequestMapping(value = "bankmgUpdate.do", method = RequestMethod.POST)
	public String bankmgUpdate(@RequestParam("bankNumber") String bankNumber,
			@RequestParam("bizNumber") String bizNumber, Bankmg bankmg) {
		// bankNumber와 bizNumber를 Bankmg 객체에 설정
		bankmg.setBankNumber(bankNumber);
		bankmg.setBizNumber(bizNumber);

		// 업데이트 호출
		bankmgService.updatemg(bankmg);
		return "redirect:/bankmg.do";
	}

	// 계좌 삭제 처리
	@RequestMapping(value = "bankmgDelete.do", method = RequestMethod.POST)
	public String bankmgDelete(@RequestParam("bankIds") String[] bankIds, @RequestParam("bizNumber") String bizNumber) {
		for (String bankId : bankIds) {
			Bankmg mg = new Bankmg();
			mg.setBankNumber(bankId);
			mg.setBizNumber(bizNumber);

			bankmgService.deletemg(mg); // Bankmg 객체로 삭제
		}
		return "redirect:/bankmg.do"; // 삭제 후 리스트로 리다이렉트
	}
}
