package com.e3i3.moduerp.financialclosing.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
	public String finCloseAddForm() {
		return "financialClosing/finCloseAddForm";
	}

	// 결산 등록 처리
	@RequestMapping(value = "finCloseAddForm.do", method = RequestMethod.POST)
	public String finCloseAdd(@RequestParam("departmentId") String departmentId,
			@RequestParam("startDate") java.sql.Date startDate, @RequestParam("endDate") java.sql.Date endDate,
			@RequestParam("totalSales") int totalSales, @RequestParam("totalExpenses") int totalExpenses,
			@RequestParam("netProfit") int netProfit, @RequestParam("approvalStatus") String approvalStatus,
			@RequestParam("closingDate") java.sql.Date closingDate, @RequestParam("closingType") String closingType,
			HttpSession session) {

		String bizNumber = (String) session.getAttribute("biz_number");

		String closingid = bizNumber + "BA" + System.currentTimeMillis();

		FinClose finClosedto = new FinClose();
		finClosedto.setClosingId(closingid);
		finClosedto.setDepartmentId(departmentId);
		finClosedto.setStartDate(startDate);
		finClosedto.setEndDate(endDate);
		finClosedto.setTotalSales(totalSales);
		finClosedto.setTotalExpenses(totalExpenses);
		finClosedto.setNetProfit(netProfit);
		finClosedto.setApprovalStatus(approvalStatus);
		finClosedto.setClosingDate(closingDate);
		finClosedto.setClosingType(closingType);

		finCloseService.insertFinClose(finClosedto);
		return "redirect:/finClose.do";
	}

}