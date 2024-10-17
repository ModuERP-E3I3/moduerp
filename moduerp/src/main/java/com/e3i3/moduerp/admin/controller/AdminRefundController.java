package com.e3i3.moduerp.admin.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.e3i3.moduerp.paylog.model.service.PayLogService;
import com.e3i3.moduerp.refund.model.dto.RefundDTO;
import com.e3i3.moduerp.refund.model.service.RefundService;

@Controller
@RequestMapping("/")
public class AdminRefundController {

	@Autowired
	private RefundService refundService;
	
	@Autowired
	private PayLogService payLogService;

	@RequestMapping(value = "/adminRefund.do", method = RequestMethod.GET)
	public String showWorkOrders(Model model, HttpSession session) {

		List<RefundDTO> refundApply = refundService.selectRefundApply();

		List<RefundDTO> refunding = refundService.selectRefunding();
		List<RefundDTO> refundComplete = refundService.selectRefundComplete();

		model.addAttribute("refundApply", refundApply);
		model.addAttribute("refunding", refunding);
		model.addAttribute("refundComplete", refundComplete);

		return "admin/adminRefund";
	}

	@GetMapping("/refundUpdate.do")
	public String refundUpdate(@RequestParam("refundId") String refundId, Model model) {

		RefundDTO refundDetail = refundService.selectRefundDetail(refundId);

		model.addAttribute("refundDetail", refundDetail);

		return "admin/adminRefundUpdate";
	}

	@PostMapping("/adminRefundUpdate.do")
	public String adminRefundUpdate(@RequestParam("refundStatus") String refundStatus,
			@RequestParam("refundId") String refundId, @RequestParam("bizNumber") String bizNumber, Model model) {

		refundService.updateRefundStatus(refundStatus, refundId);
		
		payLogService.updateRefundStatus(refundStatus,bizNumber);

		return "redirect:/adminRefund.do";
	}
}
