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
		// ?„¸?…˜?—?„œ biz_number êº¼ë‚´ê¸?
		String bizNumber = (String) session.getAttribute("biz_number");

		// DB?—?„œ biz_number?— ?•´?‹¹?•˜?Š” WorkOrder ëª©ë¡ ê°?? ¸?˜¤ê¸?
		List<WorkOrderDTO> workOrderList = workOrderService.getWorkOrdersByBizNumber(bizNumber);

		// ?˜?´ì§??‹¹ ?•­ëª? ?ˆ˜ ?„¤? •
		int itemsPerPage = 10;

		// ì´? ?•­ëª? ?ˆ˜ ê³„ì‚°
		int totalItems = workOrderList.size();

		// ì´? ?˜?´ì§? ?ˆ˜ ê³„ì‚°
		int totalPages = (int) Math.ceil((double) totalItems / itemsPerPage);

		// ?‹œ?‘ ?¸?±?Š¤ ê³„ì‚°
		int startIndex = (page - 1) * itemsPerPage;
		int endIndex = Math.min(startIndex + itemsPerPage, totalItems);

		// ?„œë¸Œë¦¬?Š¤?Š¸ ?ƒ?„± (?•´?‹¹ ?˜?´ì§??˜ ?•­ëª©ë“¤ë§? ì¶”ì¶œ)
		List<WorkOrderDTO> paginatedList = workOrderList.subList(startIndex, endIndex);

		// ëª¨ë¸?— WorkOrder ëª©ë¡, ì´? ?˜?´ì§? ?ˆ˜, ?˜„?¬ ?˜?´ì§? ì¶”ê?
		model.addAttribute("workOrderList", paginatedList);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("currentPage", page);

		return "productionStock/productionWorkorder"; // JSP ?ŒŒ?¼ ê²½ë¡œ ë°˜í™˜
	}

	@RequestMapping(value = "/productionWorkOrderCreate.do", method = RequestMethod.GET)
	public String showWorkOrderCreatePage(Model model, HttpSession session) {
		String bizNumber = (String) session.getAttribute("biz_number");
		String uuid = (String) session.getAttribute("uuid");

		// ITEM_CODEê°? biz_number + "P"ë¡? ?‹œ?‘?•˜?Š” ?•„?´?…œ ëª©ë¡ê³? stock ì»¬ëŸ¼ ê°?? ¸?˜¤ê¸?
		List<ItemDTO> itemList = itemProductionstockService.getItemNamesAndStockByBizNumberStartingWith(bizNumber);
		
		// ê°? item?— ???•œ QTY?˜ ?•©ê³„ë?? ê³„ì‚°?•˜ê³? ì¶”ê?
		Map<String, Integer> itemQtyMap = new HashMap<>();
		for (ItemDTO item : itemList) {
			int totalQty = workOrderService.getTotalQtyByItemCode(item.getItemCode());
			itemQtyMap.put(item.getItemCode(), totalQty);
		}
		// WORKER_TEAM ëª©ë¡ ê°?? ¸?˜¤ê¸?
		List<String> workerTeams = workOrderService.getWorkerTeamsByBizNumber(bizNumber);

		// EMP_NAME ëª©ë¡ ê°?? ¸?˜¤ê¸?
		List<String> employeeNames = employeeProductionService.getEmployeeNamesByBizNumber(bizNumber);

		// WORK_PLACE ëª©ë¡ ê°?? ¸?˜¤ê¸?
		List<String> workPlaces = workOrderService.getWorkPlacesByBizNumber(bizNumber);

		// ?˜„?¬ ë¡œê·¸?¸?•œ ?‚¬?š©??˜ EMP_NAME ê°?? ¸?˜¤ê¸?
		String directorName = employeeProductionService.getEmployeeNameByUuid(uuid);

		model.addAttribute("itemList", itemList); // stock ì»¬ëŸ¼?„ ?¬?•¨?•œ ?•„?´?…œ ëª©ë¡
		model.addAttribute("itemQtyMap", itemQtyMap);
		model.addAttribute("workerTeams", workerTeams);
		model.addAttribute("employeeNames", employeeNames);
		model.addAttribute("workPlaces", workPlaces);
		model.addAttribute("directorName", directorName);

		return "productionStock/productionWorkOrderCreate"; // JSP ?ŒŒ?¼ ê²½ë¡œ
	}

	@RequestMapping(value = "/productionWorkOrderInsert.do", method = RequestMethod.POST)
	public String insertWorkOrder(@RequestParam("itemName") String itemName, @RequestParam("itemCode") String itemCode,
			@RequestParam("taskName") String taskName, @RequestParam("startDate") String startDateStr, // ?‚ ì§œë§Œ ?…? ¥ë°›ìŒ
			@RequestParam("endExDate") String endExDateStr, // ?‚ ì§œë§Œ ?…? ¥ë°›ìŒ
			@RequestParam("qty") int qty, @RequestParam("progressStatus") String progressStatus,
			@RequestParam("workerTeam") String workerTeam, @RequestParam("worker") String worker,
			@RequestParam("workPlace") String workPlace, @RequestParam("wDirector") String wDirector,
			HttpSession session) throws Exception {
		
		if (itemName.contains(" ?¬ê³? :")) {
	        itemName = itemName.split(" ?¬ê³? :")[0];  // "?’ˆëª? A" ë¶?ë¶„ë§Œ ì¶”ì¶œ
	    }
		// ?„¸?…˜?—?„œ biz_number?? uuid ê°?? ¸?˜¤ê¸?
		String bizNumber = (String) session.getAttribute("biz_number");
		String uuid = (String) session.getAttribute("uuid");

		// ORDER_NUMBER ?ƒ?„±: biz_number + 'W' + ?˜„?¬ ???„?Š¤?ƒ¬?”„
		String orderNumber = bizNumber + "W" + System.currentTimeMillis();

		// startDate?? endExDateë¥? Timestampë¡? ë³??™˜?•˜ë©? ?˜„?¬ ?‹œê°„ì„ ì¶”ê?
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date parsedStartDate = dateFormat.parse(startDateStr);

		// ?˜„?¬ ?‹œê°„ì„ ê°?? ¸???„œ ?„¤? •
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(parsedStartDate);
		calendar.set(Calendar.HOUR_OF_DAY, Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
		calendar.set(Calendar.MINUTE, Calendar.getInstance().get(Calendar.MINUTE));
		calendar.set(Calendar.SECOND, Calendar.getInstance().get(Calendar.SECOND));

		Timestamp startDate = new Timestamp(calendar.getTimeInMillis());

		// endExDate ì²˜ë¦¬ (?˜„?¬ ?‹œê°„ì„ ì¶”ê??•˜ì§? ?•Šê³? ê¸°ë³¸ ?‹œê°? 00:00:00?œ¼ë¡? ?„¤? •)
		Date parsedEndExDate = dateFormat.parse(endExDateStr);
		Timestamp endExDate = new Timestamp(parsedEndExDate.getTime());

		// WorkOrderDTO ê°ì²´ ?ƒ?„± ë°? ?°?´?„° ?„¤? •
		WorkOrderDTO workOrderDTO = new WorkOrderDTO();
		workOrderDTO.setOrderNumber(orderNumber);
		workOrderDTO.setItemName(itemName);
		workOrderDTO.setItemCode(itemCode);
		workOrderDTO.setTaskName(taskName);
		workOrderDTO.setStartDate(startDate); // ë³??™˜?œ Timestamp ?„¤? •
		workOrderDTO.setEndExDate(endExDate); // ë³??™˜?œ Timestamp ?„¤? •
		workOrderDTO.setQty(qty);
		workOrderDTO.setProgressStatus(progressStatus);
		workOrderDTO.setWorkerTeam(workerTeam);
		workOrderDTO.setWorker(worker);
		workOrderDTO.setWorkPlace(workPlace);
		workOrderDTO.setwDirector(wDirector);
		workOrderDTO.setBizNumber(bizNumber);
		workOrderDTO.setUuid(uuid);

		// ?‘?—…ì§??‹œ?„œ ?°?´?„° ???¥
		workOrderService.insertWorkOrder(workOrderDTO);

		// ???¥ ?›„ ?‘?—…ì§??‹œ?„œ ë¦¬ìŠ¤?Š¸ ?˜?´ì§?ë¡? ë¦¬ë‹¤?´? ‰?Š¸
		return "redirect:/productionWorkorder.do";
	}

	// ?‘?—… ì§??‹œ?„œ ?ƒ?„¸ë³´ê¸° ë©”ì„œ?“œ
	@RequestMapping(value = "/getProductionWorkOrderDetails.do", method = RequestMethod.GET)
	public String getProductionWorkOrderDetails(@RequestParam("orderNumber") String orderNumber, Model model) {

		// orderNumberë¡? ?‘?—… ì§??‹œ?„œ ì¡°íšŒ
		WorkOrderDTO workOrderDetails = workOrderService.getWorkOrderByOrderNumber(orderNumber);

		// ì¡°íšŒ?œ ?‘?—… ì§??‹œ?„œë¥? ëª¨ë¸?— ì¶”ê??•˜?—¬ JSPë¡? ? „?‹¬
		model.addAttribute("workOrderDetails", workOrderDetails);

		// ?ƒ?„¸ ? •ë³? ?˜?´ì§?ë¡? ?´?™
		return "productionStock/productionWorkOrderDetail";
	}

	// ?‘?—… ì§??‹œ?„œ ?ˆ˜? • ?˜?´ì§?ë¡? ?´?™
	@RequestMapping(value = "/productionWorkOrderDetailUpdate.do", method = RequestMethod.GET)
	public String updateProductionWorkOrderDetails(@RequestParam("orderNumber") String orderNumber, Model model,
			HttpSession session) {
		// ?„¸?…˜?—?„œ biz_number?? uuid ê°?? ¸?˜¤ê¸?
		String bizNumber = (String) session.getAttribute("biz_number");

		// orderNumberë¡? ?‘?—… ì§??‹œ?„œ ì¡°íšŒ
		WorkOrderDTO workOrderDetails = workOrderService.getWorkOrderByOrderNumber(orderNumber);

		List<String> employeeNames = employeeProductionService.getEmployeeNamesByBizNumber(bizNumber);

		// ì¡°íšŒ?œ ?‘?—… ì§??‹œ?„œë¥? ëª¨ë¸?— ì¶”ê??•˜?—¬ ?ˆ˜? • ?˜?´ì§?ë¡? ? „?‹¬
		model.addAttribute("workOrderDetails", workOrderDetails);
		model.addAttribute("employeeNames", employeeNames);

		// ?ˆ˜? • ?˜?´ì§?ë¡? ?´?™
		return "productionStock/productionWorkOrderDetailUpdate";

	}

	// ?‘?—… ì§??‹œ?„œ ?ˆ˜? • ?™„ë£? ì²˜ë¦¬
	@RequestMapping(value = "/updateProductionWorkOrderUpdateDo.do", method = RequestMethod.POST)
	public String insertWorkOrder(@RequestParam("taskName") String taskName,
			@RequestParam("startDate") String startDateStr, // ?‚ ì§œë§Œ ?…? ¥ë°›ìŒ
			@RequestParam("endExDate") String endExDateStr, // ?‚ ì§œë§Œ ?…? ¥ë°›ìŒ
			@RequestParam("qty") int qty, @RequestParam("progressStatus") String progressStatus,
			@RequestParam("workerTeam") String workerTeam, @RequestParam("worker") List<String> workerList,
			@RequestParam("workPlace") String workPlace, @RequestParam("orderNumber") String orderNumber,
			HttpSession session) throws Exception {

		// startDateë¥? Timestampë¡? ë³??™˜?•˜ë©? ?˜„?¬ ?‹œê°„ì„ ì¶”ê?
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date parsedStartDate = dateFormat.parse(startDateStr);

		// ?˜„?¬ ?‹œê°? ?„¤? •
		Calendar startCalendar = Calendar.getInstance();
		startCalendar.setTime(parsedStartDate);
		startCalendar.set(Calendar.HOUR_OF_DAY, Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
		startCalendar.set(Calendar.MINUTE, Calendar.getInstance().get(Calendar.MINUTE));
		startCalendar.set(Calendar.SECOND, Calendar.getInstance().get(Calendar.SECOND));

		Timestamp startDate = new Timestamp(startCalendar.getTimeInMillis());

		SimpleDateFormat enddateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date parsedEndExDate = enddateFormat.parse(endExDateStr);

		// WorkOrderDTO ê°ì²´ ?ƒ?„± ë°? ?°?´?„° ?„¤? •
		WorkOrderDTO workOrderDTO = new WorkOrderDTO();
		workOrderDTO.setOrderNumber(orderNumber);
		workOrderDTO.setTaskName(taskName);
		workOrderDTO.setStartDate(startDate); // ë³??™˜?œ Timestamp ?„¤? •
		workOrderDTO.setEndExDate(parsedEndExDate);
		workOrderDTO.setQty(qty);
		workOrderDTO.setProgressStatus(progressStatus);
		workOrderDTO.setWorkerTeam(workerTeam);

		String worker = String.join(",", workerList);
		workOrderDTO.setWorker(worker);

		workOrderDTO.setWorkPlace(workPlace);

		// ?˜„?¬ ?‹œê°„ì„ ???„?Š¤?ƒ¬?”„ë¡? ?„¤? •
		Timestamp updateDate = new Timestamp(System.currentTimeMillis());
		workOrderDTO.setUpdateDate(updateDate);

		// progressStatusê°? "?‘?—… ?™„ë£?"?´ë©? endDate?— ?˜„?¬ ?‹œê°„ì„ ?„£ê³?, ê·¸ë ‡ì§? ?•Š?œ¼ë©? nullë¡? ?„¤? •
		if ("?‘?—… ?™„ë£?".equals(progressStatus)) {
			Timestamp endDate = new Timestamp(System.currentTimeMillis());
			workOrderDTO.setEndDate(endDate);
		} else {
			workOrderDTO.setEndDate(null);
		}

		// ?‘?—…ì§??‹œ?„œ ?°?´?„° ?—…?°?´?Š¸
		workOrderService.updateWorkOrder(workOrderDTO);

		// ???¥ ?›„ ?‘?—…ì§??‹œ?„œ ë¦¬ìŠ¤?Š¸ ?˜?´ì§?ë¡? ë¦¬ë‹¤?´? ‰?Š¸
		return "redirect:/productionWorkorder.do";
	}

	// ?‘?—…ì§??‹œ?„œ ?‚­? œ ì²˜ë¦¬ ë©”ì„œ?“œ
	@PostMapping("/deleteProductionWorkOrder.do")
	public String deleteProductionWorkOrder(@RequestParam("orderNumber") String orderNumber, HttpSession session) {

		// ?•´?‹¹ orderNumber?— ?•´?‹¹?•˜?Š” ?‘?—…ì§??‹œ?„œë¥? ?‚­? œ
		workOrderService.deleteWorkOrder(orderNumber);

		// ?‚­? œ ?›„ ?‘?—…ì§??‹œ?„œ ë¦¬ìŠ¤?Š¸ ?˜?´ì§?ë¡? ë¦¬ë‹¤?´? ‰?Š¸
		return "redirect:/productionWorkorder.do";
	}
}
