package com.e3i3.moduerp.workorder.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.e3i3.moduerp.employee.model.service.EmployeeProductionService;
import com.e3i3.moduerp.item.model.dto.ItemDTO;
import com.e3i3.moduerp.item.model.service.ItemProductionstockService;
import com.e3i3.moduerp.workorder.model.dto.WorkOrderDTO;
import com.e3i3.moduerp.workorder.model.service.WorkOrderService;

@Controller
@RequestMapping("/")
public class WorkOrderController {

	@Autowired
	private WorkOrderService workOrderService;

	@Autowired
	private ItemProductionstockService itemProductionstockService;

	@Autowired
	private EmployeeProductionService employeeProductionService;

	@RequestMapping(value = "/productionWorkorder.do", method = RequestMethod.GET)
	public String showWorkOrders(@RequestParam(value = "page", defaultValue = "1") int page, Model model,
			HttpSession session) {
		// ?��?��?��?�� biz_number 꺼내�?
		String bizNumber = (String) session.getAttribute("biz_number");

		// DB?��?�� biz_number?�� ?��?��?��?�� WorkOrder 목록 �??��?���?
		List<WorkOrderDTO> workOrderList = workOrderService.getWorkOrdersByBizNumber(bizNumber);

		// ?��?���??�� ?���? ?�� ?��?��
		int itemsPerPage = 10;

		// �? ?���? ?�� 계산
		int totalItems = workOrderList.size();

		// �? ?��?���? ?�� 계산
		int totalPages = (int) Math.ceil((double) totalItems / itemsPerPage);

		// ?��?�� ?��?��?�� 계산
		int startIndex = (page - 1) * itemsPerPage;
		int endIndex = Math.min(startIndex + itemsPerPage, totalItems);

		// ?��브리?��?�� ?��?�� (?��?�� ?��?���??�� ?��목들�? 추출)
		List<WorkOrderDTO> paginatedList = workOrderList.subList(startIndex, endIndex);

		// 모델?�� WorkOrder 목록, �? ?��?���? ?��, ?��?�� ?��?���? 추�?
		model.addAttribute("workOrderList", paginatedList);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("currentPage", page);

		return "productionStock/productionWorkorder"; // JSP ?��?�� 경로 반환
	}

	@RequestMapping(value = "/productionWorkOrderCreate.do", method = RequestMethod.GET)
	public String showWorkOrderCreatePage(Model model, HttpSession session) {
		String bizNumber = (String) session.getAttribute("biz_number");
		String uuid = (String) session.getAttribute("uuid");

		// ITEM_CODE�? biz_number + "P"�? ?��?��?��?�� ?��?��?�� 목록�? stock 컬럼 �??��?���?
		List<ItemDTO> itemList = itemProductionstockService.getItemNamesAndStockByBizNumberStartingWith(bizNumber);
		
		// �? item?�� ???�� QTY?�� ?��계�?? 계산?���? 추�?
		Map<String, Integer> itemQtyMap = new HashMap<>();
		for (ItemDTO item : itemList) {
			int totalQty = workOrderService.getTotalQtyByItemCode(item.getItemCode());
			itemQtyMap.put(item.getItemCode(), totalQty);
		}
		// WORKER_TEAM 목록 �??��?���?
		List<String> workerTeams = workOrderService.getWorkerTeamsByBizNumber(bizNumber);

		// EMP_NAME 목록 �??��?���?
		List<String> employeeNames = employeeProductionService.getEmployeeNamesByBizNumber(bizNumber);

		// WORK_PLACE 목록 �??��?���?
		List<String> workPlaces = workOrderService.getWorkPlacesByBizNumber(bizNumber);

		// ?��?�� 로그?��?�� ?��?��?��?�� EMP_NAME �??��?���?
		String directorName = employeeProductionService.getEmployeeNameByUuid(uuid);

		model.addAttribute("itemList", itemList); // stock 컬럼?�� ?��?��?�� ?��?��?�� 목록
		model.addAttribute("itemQtyMap", itemQtyMap);
		model.addAttribute("workerTeams", workerTeams);
		model.addAttribute("employeeNames", employeeNames);
		model.addAttribute("workPlaces", workPlaces);
		model.addAttribute("directorName", directorName);

		return "productionStock/productionWorkOrderCreate"; // JSP ?��?�� 경로
	}

	@RequestMapping(value = "/productionWorkOrderInsert.do", method = RequestMethod.POST)
	public String insertWorkOrder(@RequestParam("itemName") String itemName, @RequestParam("itemCode") String itemCode,
			@RequestParam("taskName") String taskName, @RequestParam("startDate") String startDateStr, // ?��짜만 ?��?��받음
			@RequestParam("endExDate") String endExDateStr, // ?��짜만 ?��?��받음
			@RequestParam("qty") int qty, @RequestParam("progressStatus") String progressStatus,
			@RequestParam("workerTeam") String workerTeam, @RequestParam("worker") String worker,
			@RequestParam("workPlace") String workPlace, @RequestParam("wDirector") String wDirector,
			HttpSession session) throws Exception {
		
		if (itemName.contains(" ?���? :")) {
	        itemName = itemName.split(" ?���? :")[0];  // "?���? A" �?분만 추출
	    }
		// ?��?��?��?�� biz_number?? uuid �??��?���?
		String bizNumber = (String) session.getAttribute("biz_number");
		String uuid = (String) session.getAttribute("uuid");

		// ORDER_NUMBER ?��?��: biz_number + 'W' + ?��?�� ???��?��?��?��
		String orderNumber = bizNumber + "W" + System.currentTimeMillis();

		// startDate?? endExDate�? Timestamp�? �??��?���? ?��?�� ?��간을 추�?
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date parsedStartDate = dateFormat.parse(startDateStr);

		// ?��?�� ?��간을 �??��???�� ?��?��
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(parsedStartDate);
		calendar.set(Calendar.HOUR_OF_DAY, Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
		calendar.set(Calendar.MINUTE, Calendar.getInstance().get(Calendar.MINUTE));
		calendar.set(Calendar.SECOND, Calendar.getInstance().get(Calendar.SECOND));

		Timestamp startDate = new Timestamp(calendar.getTimeInMillis());

		// endExDate 처리 (?��?�� ?��간을 추�??���? ?���? 기본 ?���? 00:00:00?���? ?��?��)
		Date parsedEndExDate = dateFormat.parse(endExDateStr);
		Timestamp endExDate = new Timestamp(parsedEndExDate.getTime());

		
		String SitemName = itemName.split("\\|")[0].trim();
		System.out.println(SitemName);
		
		// WorkOrderDTO 객체 ?��?�� �? ?��?��?�� ?��?��
		WorkOrderDTO workOrderDTO = new WorkOrderDTO();
		workOrderDTO.setOrderNumber(orderNumber);
		workOrderDTO.setItemName(SitemName);
		workOrderDTO.setItemCode(itemCode);
		workOrderDTO.setTaskName(taskName);
		workOrderDTO.setStartDate(startDate); // �??��?�� Timestamp ?��?��
		workOrderDTO.setEndExDate(endExDate); // �??��?�� Timestamp ?��?��
		workOrderDTO.setQty(qty);
		workOrderDTO.setProgressStatus(progressStatus);
		workOrderDTO.setWorkerTeam(workerTeam);
		workOrderDTO.setWorker(worker);
		workOrderDTO.setWorkPlace(workPlace);
		workOrderDTO.setwDirector(wDirector);
		workOrderDTO.setBizNumber(bizNumber);
		workOrderDTO.setUuid(uuid);

		// ?��?���??��?�� ?��?��?�� ???��
		workOrderService.insertWorkOrder(workOrderDTO);

		// ???�� ?�� ?��?���??��?�� 리스?�� ?��?���?�? 리다?��?��?��
		return "redirect:/productionWorkorder.do";
	}

	// ?��?�� �??��?�� ?��?��보기 메서?��
	@RequestMapping(value = "/getProductionWorkOrderDetails.do", method = RequestMethod.GET)
	public String getProductionWorkOrderDetails(@RequestParam("orderNumber") String orderNumber, Model model) {

		// orderNumber�? ?��?�� �??��?�� 조회
		WorkOrderDTO workOrderDetails = workOrderService.getWorkOrderByOrderNumber(orderNumber);

		// 조회?�� ?��?�� �??��?���? 모델?�� 추�??��?�� JSP�? ?��?��
		model.addAttribute("workOrderDetails", workOrderDetails);

		// ?��?�� ?���? ?��?���?�? ?��?��
		return "productionStock/productionWorkOrderDetail";
	}

	// ?��?�� �??��?�� ?��?�� ?��?���?�? ?��?��
	@RequestMapping(value = "/productionWorkOrderDetailUpdate.do", method = RequestMethod.GET)
	public String updateProductionWorkOrderDetails(@RequestParam("orderNumber") String orderNumber, Model model,
			HttpSession session) {
		// ?��?��?��?�� biz_number?? uuid �??��?���?
		String bizNumber = (String) session.getAttribute("biz_number");

		// orderNumber�? ?��?�� �??��?�� 조회
		WorkOrderDTO workOrderDetails = workOrderService.getWorkOrderByOrderNumber(orderNumber);

		List<String> employeeNames = employeeProductionService.getEmployeeNamesByBizNumber(bizNumber);

		// 조회?�� ?��?�� �??��?���? 모델?�� 추�??��?�� ?��?�� ?��?���?�? ?��?��
		model.addAttribute("workOrderDetails", workOrderDetails);
		model.addAttribute("employeeNames", employeeNames);

		// ?��?�� ?��?���?�? ?��?��
		return "productionStock/productionWorkOrderDetailUpdate";

	}

	// ?��?�� �??��?�� ?��?�� ?���? 처리
	@RequestMapping(value = "/updateProductionWorkOrderUpdateDo.do", method = RequestMethod.POST)
	public String insertWorkOrder(
			@RequestParam("taskName") String taskName,
			@RequestParam("startDate") String startDateStr, // ?��짜만 ?��?��받음
			@RequestParam("endExDate") String endExDateStr, // ?��짜만 ?��?��받음
			@RequestParam("qty") int qty, 
			@RequestParam("progressStatus") String progressStatus,
			@RequestParam("workerTeam") String workerTeam,
			@RequestParam("worker") List<String> workerList,
			@RequestParam("workPlace") String workPlace,
			@RequestParam("orderNumber") String orderNumber,
			HttpSession session) throws Exception {

		// startDate�? Timestamp�? �??��?���? ?��?�� ?��간을 추�?
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date parsedStartDate = dateFormat.parse(startDateStr);

		// ?��?�� ?���? ?��?��
		Calendar startCalendar = Calendar.getInstance();
		startCalendar.setTime(parsedStartDate);
		startCalendar.set(Calendar.HOUR_OF_DAY, Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
		startCalendar.set(Calendar.MINUTE, Calendar.getInstance().get(Calendar.MINUTE));
		startCalendar.set(Calendar.SECOND, Calendar.getInstance().get(Calendar.SECOND));

		Timestamp startDate = new Timestamp(startCalendar.getTimeInMillis());

		SimpleDateFormat enddateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date parsedEndExDate = enddateFormat.parse(endExDateStr);

		
		
		// WorkOrderDTO 객체 ?��?�� �? ?��?��?�� ?��?��
		WorkOrderDTO workOrderDTO = new WorkOrderDTO();
		workOrderDTO.setOrderNumber(orderNumber);
		workOrderDTO.setTaskName(taskName);
		workOrderDTO.setStartDate(startDate);
		workOrderDTO.setEndExDate(parsedEndExDate);
		workOrderDTO.setQty(qty);
		workOrderDTO.setProgressStatus(progressStatus);
		workOrderDTO.setWorkerTeam(workerTeam);

		String worker = String.join(",", workerList);
		workOrderDTO.setWorker(worker);

		workOrderDTO.setWorkPlace(workPlace);

		// ?��?�� ?��간을 ???��?��?��?���? ?��?��
		Timestamp updateDate = new Timestamp(System.currentTimeMillis());
		workOrderDTO.setUpdateDate(updateDate);

		// progressStatus�? "?��?�� ?���?"?���? endDate?�� ?��?�� ?��간을 ?���?, 그렇�? ?��?���? null�? ?��?��
		if ("?��?�� ?���?".equals(progressStatus)) {
			Timestamp endDate = new Timestamp(System.currentTimeMillis());
			workOrderDTO.setEndDate(endDate);
		} else {
			workOrderDTO.setEndDate(null);
		}

		// ?��?���??��?�� ?��?��?�� ?��?��?��?��
		workOrderService.updateWorkOrder(workOrderDTO);

		// ???�� ?�� ?��?���??��?�� 리스?�� ?��?���?�? 리다?��?��?��
		return "redirect:/productionWorkorder.do";
	}

	// ?��?���??��?�� ?��?�� 처리 메서?��
	@PostMapping("/deleteProductionWorkOrder.do")
	public String deleteProductionWorkOrder(@RequestParam("orderNumber") String orderNumber, HttpSession session) {

		// ?��?�� orderNumber?�� ?��?��?��?�� ?��?���??��?���? ?��?��
		workOrderService.deleteWorkOrder(orderNumber);

		// ?��?�� ?�� ?��?���??��?�� 리스?�� ?��?���?�? 리다?��?��?��
		return "redirect:/productionWorkorder.do";
	}
}
