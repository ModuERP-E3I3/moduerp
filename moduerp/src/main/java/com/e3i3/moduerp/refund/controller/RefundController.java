package com.e3i3.moduerp.refund.controller;

import java.sql.Timestamp;
import java.util.Calendar;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.e3i3.moduerp.employee.model.service.EmployeeProductionService;
import com.e3i3.moduerp.paylog.model.dto.PayLogDTO;
import com.e3i3.moduerp.paylog.model.service.PayLogService;
import com.e3i3.moduerp.refund.model.dto.RefundDTO;
import com.e3i3.moduerp.refund.model.service.RefundService;

@Controller
@RequestMapping("/")
public class RefundController {

	@Autowired
	private PayLogService payLogService;

	@Autowired
	private EmployeeProductionService employeeService;

	@Autowired
	private RefundService refundService;

	@RequestMapping(value = "/refund.do", method = RequestMethod.POST)
	public String showWorkOrders(@RequestParam("logId") String logId, Model model, HttpSession session) {
		System.out.println(logId);

		PayLogDTO payLogDTO = payLogService.selectPayLogByLogId(logId);

		model.addAttribute("payLogDTO", payLogDTO);

		return "refund/refund";
	}

	@RequestMapping(value = "/refundInsert.do", method = RequestMethod.POST)
	public String refundInsert(@RequestParam("logId") String logId, @RequestParam("refundReseon") String refundReseon,
			Model model, HttpSession session) {
		String bizNumber = (String) session.getAttribute("biz_number");
		String uuid = (String) session.getAttribute("uuid");

		PayLogDTO payLogDTO = payLogService.selectPayLogByLogId(logId);

		String refundId = bizNumber + "RF" + System.currentTimeMillis();

		// 현재 시간 설정 (Calendar 사용)
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
		calendar.set(Calendar.MINUTE, Calendar.getInstance().get(Calendar.MINUTE));
		calendar.set(Calendar.SECOND, Calendar.getInstance().get(Calendar.SECOND));

		Timestamp refundDate = new Timestamp(calendar.getTimeInMillis());

		// 각 필드에 대한 getter 호출
		String orderId = payLogDTO.getOrderId();
		String payId = payLogDTO.getPayId();
		int refundPrice = payLogDTO.getTotalAmount();
		String name = employeeService.getEmployeeNameByUuid(uuid);

		RefundDTO refundDTO = new RefundDTO();
		refundDTO.setRefundId(refundId);
		refundDTO.setPayId(payId);
		refundDTO.setOrderId(orderId);
		refundDTO.setUuid(uuid);
		refundDTO.setRefundPrice(refundPrice);
		refundDTO.setRefundStatus("환불 신청 완료");
		refundDTO.setRefundReason(refundReseon);
		refundDTO.setRefundDate(refundDate);
		refundDTO.setBizNumber(bizNumber);
		refundDTO.setName(name);

		refundService.insertRefund(refundDTO);

		String payLogId = payLogDTO.getLogId();
		payLogService.updateRefund(payLogId);

		return "redirect:/cardLog.do";

	}

	@RequestMapping(value = "/cancelRefund.do", method = RequestMethod.POST)
	public String cancelRefund(@RequestParam("orderId") String orderId, @RequestParam("logId") String logId) {

		refundService.deleteRefundByOrderId(orderId);

		payLogService.updateRefundStatus(logId);

		return "redirect:/cardLog.do";
	}

	@RequestMapping(value = "/refundLog.do", method = RequestMethod.GET)
	public String refundLog(Model model, HttpSession session) {

		return "";
	}

}
