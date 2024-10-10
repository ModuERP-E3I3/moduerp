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
	public String getBankmgDetail(@RequestParam("bankId") String bankId, Model model) {
		Bankmg bankmg = bankmgService.getmgById(bankId);
		model.addAttribute("bankmg", bankmg);
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
									@RequestParam("bankHolder") String bankHolder, @RequestParam("balance") int balance, 
									@RequestParam("transactionPrice") int transactionPrice, @RequestParam("transactionType") String transactionType, 
									@RequestParam("transactionDate") java.sql.Date transactionDate, HttpSession session) {

		String bizNumber = (String) session.getAttribute("biz_number");

		String bankid = bizNumber + "BA" + System.currentTimeMillis();

		Bankmg bankmgdto = new Bankmg();
		bankmgdto.setBankId(bankid);
		bankmgdto.setBankName(bankName);
		bankmgdto.setBankNumber(bankNumber);
		bankmgdto.setBankHolder(bankHolder);
		bankmgdto.setBalance(balance);
		bankmgdto.setTransactionPrice(transactionPrice);
		bankmgdto.setTransactionType(transactionType);
		bankmgdto.setTransactionDate(transactionDate);

		bankmgService.insertmg(bankmgdto);
		return "redirect:/bankmg.do";
	}

	// 계좌 수정 폼 이동
	@RequestMapping("/bankmgUpdateForm.do")
	public String bankmgUpdateForm(@RequestParam("bankId") String bankId, Model model) {
		Bankmg bankmg = bankmgService.getmgById(bankId);
		model.addAttribute("bankmg", bankmg);
		return "bankmg/bankmgUpdateForm";
	}

	// 계좌 수정 처리
	@RequestMapping(value = "bankmgUpdateForm.do", method = RequestMethod.POST)
	public String bankmgUpdate(Bankmg bankmg) {
		
		bankmgService.updatemg(bankmg);
		return "redirect:/bankmg.do";
	}

	// 계좌 삭제 처리
	@RequestMapping("/bankmgDelete.do")
	public String bankmgDelete(@RequestParam("bankId") String bankId) {
		bankmgService.deletemg(bankId);
		return "redirect:/bankmg.do";
	}
}
