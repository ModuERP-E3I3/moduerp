package com.e3i3.moduerp.purchaseorders.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.e3i3.moduerp.employee.model.dto.Employee;
import com.e3i3.moduerp.employee.model.service.EmployeeProductionService;
import com.e3i3.moduerp.purchaseorders.model.dto.PurchaseOrdersDTO;

@Controller
@RequestMapping("/")
public class PurchaseOrdersController {

	@Autowired
	private com.e3i3.moduerp.purchaseorders.service.PurchaseOrdersService purchaseOrdersService;

	@Autowired
	private EmployeeProductionService employeeProductionService;

	@RequestMapping(value = "/purchaseOrders.do", method = RequestMethod.GET)
	public String forwardPurchaseOrders(@RequestParam(value = "page", defaultValue = "1") int page, Model model,
			HttpSession session) {
		String bizNumber = (String) session.getAttribute("biz_number");

		// Fetch all purchase orders
		List<PurchaseOrdersDTO> purchaseOrdersList = purchaseOrdersService.getAllPurchaseOrders(bizNumber);

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

	@RequestMapping(value = "/purchaseOrdersFilter.do", method = RequestMethod.GET)
	public String forwardPurchaseOrdersFilter(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "filterOption", required = false) String option,
			@RequestParam(value = "filterText", required = false) String filterText,
			@RequestParam(value = "startDate", required = false) String startDate,
			@RequestParam(value = "endDate", required = false) String endDate, Model model, HttpSession session) {
		String bizNumber = (String) session.getAttribute("biz_number");
		List<PurchaseOrdersDTO> purchaseOrdersList;

		// 필터링 로직
		if (option != null && filterText != null) {
			if (startDate != null && !startDate.isEmpty() && endDate != null && !endDate.isEmpty()) {
				System.out.println("날짜 있는 필터 실행");
				purchaseOrdersList = purchaseOrdersService.getPurchaseOrdersByFilterDate(bizNumber, option, filterText,
						startDate, endDate);
			} else if ((option == null || filterText == null || filterText.isEmpty()) && startDate != null
					&& !startDate.isEmpty() && endDate != null && !endDate.isEmpty()) {
				// 날짜만 있는 경우 처리
				System.out.println("날짜만 있는 필터 실행");
				purchaseOrdersList = purchaseOrdersService.getPurchaseOrdersByFilterOnlyDate(bizNumber, startDate,
						endDate);
			} else if (startDate != null && !startDate.isEmpty() && (endDate == null || endDate.isEmpty())) {
				// 시작 날짜만 있는 경우 처리
				System.out.println("시작 날짜만 있는 필터 실행");
				purchaseOrdersList = purchaseOrdersService.getPurchaseOrdersByFilterStartDate(bizNumber, startDate);
			} else if ((startDate == null || startDate.isEmpty()) && endDate != null && !endDate.isEmpty()) {
				// 종료 날짜만 있는 경우 처리
				System.out.println("종료 날짜만 있는 필터 실행");
				purchaseOrdersList = purchaseOrdersService.getPurchaseOrdersByFilterEndDate(bizNumber, endDate);
			} else {
				System.out.println("날짜 없는 필터 실행");
				purchaseOrdersList = purchaseOrdersService.getPurchaseOrdersByFilter(bizNumber, option, filterText);
			}
		} else if ((option == null || filterText == null || filterText.isEmpty()) && startDate != null
				&& !startDate.isEmpty() && endDate != null && !endDate.isEmpty()) {
			// 필터 옵션과 텍스트 없이 날짜만 있는 경우
			System.out.println("날짜만 있는 필터 실행");
			purchaseOrdersList = purchaseOrdersService.getPurchaseOrdersByFilterOnlyDate(bizNumber, startDate, endDate);
		} else if (startDate != null && !startDate.isEmpty() && (endDate == null || endDate.isEmpty())) {
			// 시작 날짜만 있는 경우 처리
			System.out.println("시작 날짜만 있는 필터 실행");
			purchaseOrdersList = purchaseOrdersService.getPurchaseOrdersByFilterStartDate(bizNumber, startDate);
		} else if ((startDate == null || startDate.isEmpty()) && endDate != null && !endDate.isEmpty()) {
			// 종료 날짜만 있는 경우 처리
			System.out.println("종료 날짜만 있는 필터 실행");
			purchaseOrdersList = purchaseOrdersService.getPurchaseOrdersByFilterEndDate(bizNumber, endDate);
		} else {
			purchaseOrdersList = purchaseOrdersService.getPurchaseOrdersByBizNumber(bizNumber);
		}

		// 페이지네이션 처리
		int itemsPerPage = 10;
		int totalItems = purchaseOrdersList.size();
		int totalPages = (int) Math.ceil((double) totalItems / itemsPerPage);
		int startIndex = (page - 1) * itemsPerPage;
		int endIndex = Math.min(startIndex + itemsPerPage, totalItems);

		// 서브리스트 생성
		List<PurchaseOrdersDTO> paginatedList = purchaseOrdersList.subList(startIndex, endIndex);

		// 모델에 추가
		model.addAttribute("purchaseOrdersList", paginatedList);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("currentPage", page);
		model.addAttribute("option", option);
		model.addAttribute("filterText", filterText);
		model.addAttribute("startDate", startDate);
		model.addAttribute("endDate", endDate);

		return "purchaseOrders/purchaseOrdersFilter"; // JSP 파일 경로 반환
	}

	@RequestMapping(value = "/purchaseOrderCreate.do", method = RequestMethod.GET)
	public String showCreatePurchaseOrderForm(Model model, HttpSession session) {
		String bizNumber = (String) session.getAttribute("biz_number");
		String uuid = (String) session.getAttribute("uuid");

		List<String> empNames = purchaseOrdersService.getEmpNamesByBizNumber(bizNumber);
		List<String> departmentIds = purchaseOrdersService.getDepartmentIdsByBizNumber(bizNumber);
		List<Employee> empNameDepart = purchaseOrdersService.getEmpNameDepart(bizNumber);
		List<PurchaseOrdersDTO> purchaseOrders = purchaseOrdersService.getPurchaseOrdersByBizNumber(bizNumber);
		// 현재 로그인한 사용자의 EMP_NAME 가져오기
		String directorName = employeeProductionService.getEmployeeNameByUuid(uuid);

		// ----- accountNo와 accountName 가져오는 부분 !!! -----
		List<Map<String, Object>> accountNames = purchaseOrdersService.getAllAccountNames();
		model.addAttribute("accountNames", accountNames);
		// -----------------------------------------------------

		model.addAttribute("empNames", empNames);
		model.addAttribute("departmentIds", departmentIds);
		model.addAttribute("purchaseOrders", purchaseOrders);
		model.addAttribute("empNameDepart", empNameDepart);
		model.addAttribute("accountNames", accountNames);
		model.addAttribute("directorName", directorName);

		return "purchaseOrders/purchaseOrdersCreate";
	}

	@PostMapping("/purchaseOrderCreate.do")
	public String purchaseOrderCreate(@RequestParam("accountNo") String accountNo,
			@RequestParam("accountName") String accountName, 
			@RequestParam("quantity") int quantity,
			@RequestParam("supplyPrice") double supplyPrice, 
			@RequestParam("deliveryDate") Date deliveryDate,
			@RequestParam("puItemName") String puItemName, 
			@RequestParam("oDirector") String oDirector,		 // 담당자명 !!!
			
			Model model, HttpSession session) {

		// 세션에서 bizNumber 가져오기
		String bizNumber = (String) session.getAttribute("biz_number");

		// PurchaseOrdersDTO 객체 생성 및 값 설정
		PurchaseOrdersDTO purchaseOrderDto = new PurchaseOrdersDTO();
		purchaseOrderDto.setAccountNo(accountNo);
		purchaseOrderDto.setAccountName(accountName);
		purchaseOrderDto.setQuantity(quantity);
		purchaseOrderDto.setSupplyPrice(supplyPrice);
		purchaseOrderDto.setDeliveryDate(deliveryDate);
		purchaseOrderDto.setPuItemName(puItemName);
		purchaseOrderDto.setoDirector(oDirector); 				 // 담당자명!!
		purchaseOrderDto.setBizNumber(bizNumber); 	

		// purchaseOrderCreate 메서드 호출
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

		// ----- accountNo와 accountName 가져오는 부분 !!! -----
		List<Map<String, Object>> accountNames = purchaseOrdersService.getAllAccountNames();
		model.addAttribute("accountNames", accountNames);
		// -----------------------------------------------------

		model.addAttribute("purchaseOrders", purchaseOrders);
		model.addAttribute("empNameDepart", empNameDepart);
		model.addAttribute("purchaseOrderDetail", purchaseOrderDetail);

		return "purchaseOrders/purchaseOrdersDetailUpdate";
	}

	@PostMapping("/updatePurchaseOrder.do")
	public String updatePurchaseOrder(
			@RequestParam("orderId") String orderId,
			@RequestParam("accountNo") String accountNo, 
			@RequestParam("accountName") String accountName,
			@RequestParam("quantity") int quantity, 
			@RequestParam("supplyPrice") double supplyPrice,
			@RequestParam("deliveryDate") Date deliveryDate, 
			@RequestParam("puItemName") String puItemName,
			@RequestParam("oDirector") String oDirector) 			// 담당자명 !!!
	{ 	


		PurchaseOrdersDTO purchaseOrderDto = new PurchaseOrdersDTO();
		purchaseOrderDto.setOrderId(orderId);
		purchaseOrderDto.setAccountNo(accountNo);
		purchaseOrderDto.setAccountName(accountName);
		purchaseOrderDto.setQuantity(quantity);
		purchaseOrderDto.setSupplyPrice(supplyPrice);
		purchaseOrderDto.setDeliveryDate(deliveryDate);
		purchaseOrderDto.setPuItemName(puItemName);
		purchaseOrderDto.setoDirector(oDirector);					 // 담당자명 !!!

		purchaseOrdersService.updatePurchaseOrder(purchaseOrderDto);

		return "redirect:/purchaseOrders.do";
	}

	@PostMapping("/deletePurchaseOrder.do")
	public String deletePurchaseOrder(@RequestParam("orderId") String orderId, HttpSession session) {
		purchaseOrdersService.deletePurchaseOrderByOrderId(orderId);
		return "redirect:/purchaseOrders.do";
	}
}
