package com.e3i3.moduerp.financialclosing.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.e3i3.moduerp.bankaccountmanagement.model.dto.Bankmg;
import com.e3i3.moduerp.financialclosing.model.dto.FinClose;
import com.e3i3.moduerp.financialclosing.model.service.FinCloseService;

@Controller
public class FinCloseController {

	@Autowired
	private FinCloseService finCloseService;

	// 결산 목록 조회
	@RequestMapping("/finClose.do")
	public String getFinCloseList(Model model) {
		List<FinClose> FinColses = finCloseService.getAllFinCloses();
		model.addAttribute("finClose", FinColses);
		return "financialClosing/finClose"; // finColse.jsp 파일로 이동
	}

	// 결산 상세 보기
	@RequestMapping("/finCloseDetail.do")
	public String getFinCloseDetail(@RequestParam("closingId") String FinClose, Model model) {
		FinClose finClose = finCloseService.getFinCloseById(FinClose);
		model.addAttribute("finClose", finClose);
		return "financialClosing/finCloseDetail"; // 상세 뷰 JSP로 이동
	}

	// 결산 등록 폼 이동
	@RequestMapping("/finCloseAddForm.do")
	public String finCloseAddForm(HttpSession session, Model model) {

		String bizNumber = (String) session.getAttribute("biz_number");
		List<Bankmg> bankmgList = finCloseService.getBankmgListByBizNumber(bizNumber);

		model.addAttribute("bankmgList", bankmgList);

		return "financialClosing/finCloseAddForm";
	}

	// 결산 등록 처리
	@RequestMapping(value = "finCloseAddForm.do", method = RequestMethod.POST)
	public String finCloseAdd(@RequestParam("bankId") String bankId, @RequestParam("startDate") java.sql.Date startDate,
			@RequestParam("endDate") java.sql.Date endDate, @RequestParam("totalSales") int totalSales,
			@RequestParam("totalExpenses") int totalExpenses, @RequestParam("netProfit") int netProfit,
			@RequestParam("closingDate") java.sql.Date closingDate, @RequestParam("closingType") String closingType,
			HttpSession session) {

		String bizNumber = (String) session.getAttribute("biz_number");
		String uuid = (String) session.getAttribute("uuid");

		String closingid = bizNumber + "FC" + System.currentTimeMillis();

		FinClose finClosedto = new FinClose();
		finClosedto.setClosingId(closingid);
		finClosedto.setUuId(uuid);
		finClosedto.setBankId(bankId);
		finClosedto.setStartDate(startDate);
		finClosedto.setEndDate(endDate);
		finClosedto.setTotalSales(totalSales);
		finClosedto.setTotalExpenses(totalExpenses);
		finClosedto.setNetProfit(netProfit);
		finClosedto.setClosingType(closingType);
		finClosedto.setClosingDate(closingDate);

		finCloseService.insertFinClose(finClosedto);
		return "redirect:/finClose.do";
	}

	// 결산 수정 폼 이동
	@RequestMapping("/finCloseUpdateForm.do")
	public String finCloseUpdateForm(@RequestParam("closingId") String FinClose, Model model) {
		FinClose finClose = finCloseService.getFinCloseById(FinClose);
		model.addAttribute("finClose", finClose);
		return "financialClosing/finCloseUpdateForm";
	}

	// 결산 수정 처리
	@RequestMapping(value = "finCloseUpdateForm.do", method = RequestMethod.POST)
	public String finCloseUpdate(FinClose finclose) {

		finCloseService.updateFinClose(finclose);
		return "redirect:/finClose.do";
	}

	// 결산 삭제 처리
	@RequestMapping("/finCloseDelete.do")
	public String finCloseDelete(@RequestParam("closingId") String FinClose) {
		finCloseService.deleteFinClose(FinClose);
		return "redirect:/finClose.do";
	}
}