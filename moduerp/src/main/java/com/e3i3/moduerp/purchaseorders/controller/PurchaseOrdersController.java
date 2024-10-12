package com.e3i3.moduerp.purchaseorders.controller;

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

import com.e3i3.moduerp.purchaseorders.model.dto.PurchaseOrdersDTO;
import com.e3i3.moduerp.employee.model.dto.Employee;

@Controller
@RequestMapping("/")
public class PurchaseOrdersController {

	@Autowired
	private com.e3i3.moduerp.purchaseorders.service.PurchaseOrdersService purchaseOrdersService;

	@RequestMapping(value = "/purchaseOrders.do", method = RequestMethod.GET)
	public String forwardPurchaseOrders(@RequestParam(value = "page", defaultValue = "1") int page, Model model,
			HttpSession session) {
		String bizNumber = (String) session.getAttribute("biz_number");

		// Fetch all purchase orders
		List<PurchaseOrdersDTO> purchaseOrdersList = purchaseOrdersService.getAllPurchaseOrders();

		// Pagination logic
		int ordersPerPage = 10;
		int totalOrders = purchaseOrdersList.size();
		int totalPages = (int) Math.ceil((double) totalOrders / ordersPerPage);
		int startIndex = (page - 1) * ordersPerPage;
		int endIndex = Math.min(startIndex + ordersPerPage, totalOrders);
		List<PurchaseOrdersDTO> paginatedList = purchaseOrdersList.subList(startIndex, endIndex);

		model.addAttribute("purchaseOrdersList", paginatedList);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("currentPage", page);

		return "purchaseOrders/purchaseOrders";
	}

	@RequestMapping(value = "/purchaseOrderCreate.do", method = RequestMethod.GET)
	public String showCreatePurchaseOrderForm(Model model, HttpSession session) {
		String bizNumber = (String) session.getAttribute("biz_number");

		List<String> empNames = purchaseOrdersService.getEmpNamesByBizNumber(bizNumber);
		List<String> departmentIds = purchaseOrdersService.getDepartmentIdsByBizNumber(bizNumber);
		List<Employee> empNameDepart = purchaseOrdersService.getEmpNameDepart(bizNumber);

		// 거래처이름, 품명 등 리스트로 가져옴!!!!
		List<String> accountNames = purchaseOrdersService.getAllAccountNames();

		List<PurchaseOrdersDTO> purchaseOrders = purchaseOrdersService.getPurchaseOrdersByBizNumber(bizNumber);

		model.addAttribute("empNames", empNames);
		model.addAttribute("departmentIds", departmentIds);
		model.addAttribute("purchaseOrders", purchaseOrders);
		model.addAttribute("empNameDepart", empNameDepart);
		model.addAttribute("accountNames", accountNames);

		return "purchaseOrders/purchaseOrdersCreate";
	}

	@PostMapping("/purchaseOrderCreate.do")
	public String purchaseOrderCreate(
	        @RequestParam("accountNo") String accountNo,
	        @RequestParam("accountName") String accountName,
	        @RequestParam("quantity") int quantity,
	        @RequestParam("supplyPrice") double supplyPrice,
	        @RequestParam("deliveryDate") String deliveryDate,
	        @RequestParam("mgrName") String mgrName,
	        @RequestParam("puItemName") String puItemName, 
	        Model model, HttpSession session) {

	    PurchaseOrdersDTO purchaseOrderDto = new PurchaseOrdersDTO();
	    purchaseOrderDto.setAccountNo(accountNo);
	    purchaseOrderDto.setAccountName(accountName);
	    purchaseOrderDto.setQuantity(quantity);
	    purchaseOrderDto.setSupplyPrice(supplyPrice);
	    purchaseOrderDto.setDeliveryDate(deliveryDate);
	    purchaseOrderDto.setMgrName(mgrName);
	    purchaseOrderDto.setPuItemName(puItemName);

	    purchaseOrdersService.purchaseOrderCreate(purchaseOrderDto);

	    return "redirect:/purchaseOrders.do";
	}


	@GetMapping("getPurchaseOrderDetails.do")
	public String getPurchaseOrderDetail(@RequestParam("orderId") String orderId, Model model) {
		PurchaseOrdersDTO purchaseOrderDetail = purchaseOrdersService.getPurchaseOrderDetail(orderId);
		model.addAttribute("purchaseOrderDetail", purchaseOrderDetail);
		return "purchaseOrders/purchaseOrdersDetail";
	}

	@GetMapping("purchaseOrderDetailUpdate.do")
	public String purchaseOrderDetailUpdate(@RequestParam("orderId") String orderId, Model model, HttpSession session) {
		PurchaseOrdersDTO purchaseOrderDetail = purchaseOrdersService.getPurchaseOrderDetail(orderId);
		String bizNumber = (String) session.getAttribute("biz_number");

		List<Employee> empNameDepart = purchaseOrdersService.getEmpNameDepart(bizNumber);
		List<PurchaseOrdersDTO> purchaseOrders = purchaseOrdersService.getPurchaseOrdersByBizNumber(bizNumber);

		model.addAttribute("purchaseOrders", purchaseOrders);
		model.addAttribute("empNameDepart", empNameDepart);
		model.addAttribute("purchaseOrderDetail", purchaseOrderDetail);

		return "purchaseOrders/purchaseOrdersDetailUpdate";
	}

	@PostMapping("/updatePurchaseOrder.do")
	public String updatePurchaseOrder(
			@RequestParam("orderId") String orderId,
			@RequestParam("accountNo") String accountNo,
			@RequestParam("quantity") int quantity, @RequestParam("supplyPrice") double supplyPrice,
			@RequestParam("deliveryDate") String deliveryDate,
			@RequestParam("mgrName") String mgrName,
			@RequestParam("puItemName") String puItemName)
			
	{

		PurchaseOrdersDTO purchaseOrderDto = new PurchaseOrdersDTO();
		purchaseOrderDto.setOrderId(orderId);
		purchaseOrderDto.setAccountNo(accountNo);
		purchaseOrderDto.setQuantity(quantity);
		purchaseOrderDto.setSupplyPrice(supplyPrice);
		purchaseOrderDto.setDeliveryDate(deliveryDate);
		purchaseOrderDto.setMgrName(mgrName);
		purchaseOrderDto.setPuItemName(puItemName);

		purchaseOrdersService.updatePurchaseOrder(purchaseOrderDto);

		return "redirect:/purchaseOrders.do";
	}

	@PostMapping("/deletePurchaseOrder.do")
	public String deletePurchaseOrder(@RequestParam("orderId") String orderId, HttpSession session) {
		purchaseOrdersService.deletePurchaseOrderByOrderId(orderId);
		return "redirect:/purchaseOrders.do";
	}
}
