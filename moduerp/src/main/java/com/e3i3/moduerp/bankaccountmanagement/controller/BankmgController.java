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
	public String selectmgList(Model model) {
		List<Bankmg> mgs = bankmgService.selectAllmgs();
		model.addAttribute("bankmg", mgs);
		return "bankmg/bankmg"; // bankmg.jsp 파일로 이동
	}

	// 계좌 상세 보기
	@RequestMapping("/bankmgDetail.do")
	public String getBankmgDetail(@RequestParam("bankId") String bankId, Model model) {

		Bankmg bankmg = bankmgService.selectmgById(bankId);
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
			@RequestParam("bankHolder") String bankHolder, @RequestParam("remarks") String remarks,
			HttpSession session) {

		String bizNumber = (String) session.getAttribute("biz_number");
		Bankmg bankmgdto = new Bankmg();

		bankmgdto.setBankId(java.util.UUID.randomUUID().toString()); // uuid(pk)
		bankmgdto.setBankName(bankName);
		bankmgdto.setBankNumber(bankNumber);
		bankmgdto.setBankHolder(bankHolder);
		bankmgdto.setRemarks(remarks);
		bankmgdto.setBizNumber(bizNumber);

		bankmgService.insertmg(bankmgdto);
		return "redirect:/bankmg.do";
	}

	// 계좌 수정 폼 이동
	@RequestMapping("/bankmgUpdateForm.do")
	public String bankmgUpdateForm(@RequestParam("bankId") String bankId, Model model) {

		Bankmg bankmg = bankmgService.selectmgById(bankId);
		model.addAttribute("bankmg", bankmg);

		return "bankmg/bankmgUpdateForm"; // 수정 폼으로 이동
	}

	// 계좌 수정 처리
	@RequestMapping(value = "bankmgUpdate.do", method = RequestMethod.POST)
	public String bankmgUpdate(@RequestParam("bankId") String bankId) {

		// 업데이트 호출
		bankmgService.updatemg(bankId);
		return "redirect:/bankmg.do";
	}

	// 계좌 삭제 처리
	@RequestMapping("/bankmgDelete.do")
	public String bankmgDelete(@RequestParam("bankId") String bankId) {
		
		bankmgService.deletemg(bankId);
		return "redirect:/bankmg.do";
	}
}
