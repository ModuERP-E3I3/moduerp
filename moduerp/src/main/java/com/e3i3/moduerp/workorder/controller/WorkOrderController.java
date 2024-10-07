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
		// ?Έ??? biz_number κΊΌλ΄κΈ?
		String bizNumber = (String) session.getAttribute("biz_number");

		// DB?? biz_number? ?΄?Ή?? WorkOrder λͺ©λ‘ κ°?? Έ?€κΈ?
		List<WorkOrderDTO> workOrderList = workOrderService.getWorkOrdersByBizNumber(bizNumber);

		// ??΄μ§??Ή ?­λͺ? ? ?€? 
		int itemsPerPage = 10;

		// μ΄? ?­λͺ? ? κ³μ°
		int totalItems = workOrderList.size();

		// μ΄? ??΄μ§? ? κ³μ°
		int totalPages = (int) Math.ceil((double) totalItems / itemsPerPage);

		// ?? ?Έ?±?€ κ³μ°
		int startIndex = (page - 1) * itemsPerPage;
		int endIndex = Math.min(startIndex + itemsPerPage, totalItems);

		// ?λΈλ¦¬?€?Έ ??± (?΄?Ή ??΄μ§?? ?­λͺ©λ€λ§? μΆμΆ)
		List<WorkOrderDTO> paginatedList = workOrderList.subList(startIndex, endIndex);

		// λͺ¨λΈ? WorkOrder λͺ©λ‘, μ΄? ??΄μ§? ?, ??¬ ??΄μ§? μΆκ?
		model.addAttribute("workOrderList", paginatedList);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("currentPage", page);

		return "productionStock/productionWorkorder"; // JSP ??Ό κ²½λ‘ λ°ν
	}

	@RequestMapping(value = "/productionWorkOrderCreate.do", method = RequestMethod.GET)
	public String showWorkOrderCreatePage(Model model, HttpSession session) {
		String bizNumber = (String) session.getAttribute("biz_number");
		String uuid = (String) session.getAttribute("uuid");

		// ITEM_CODEκ°? biz_number + "P"λ‘? ???? ??΄? λͺ©λ‘κ³? stock μ»¬λΌ κ°?? Έ?€κΈ?
		List<ItemDTO> itemList = itemProductionstockService.getItemNamesAndStockByBizNumberStartingWith(bizNumber);
		
		// κ°? item? ??? QTY? ?©κ³λ?? κ³μ°?κ³? μΆκ?
		Map<String, Integer> itemQtyMap = new HashMap<>();
		for (ItemDTO item : itemList) {
			int totalQty = workOrderService.getTotalQtyByItemCode(item.getItemCode());
			itemQtyMap.put(item.getItemCode(), totalQty);
		}
		// WORKER_TEAM λͺ©λ‘ κ°?? Έ?€κΈ?
		List<String> workerTeams = workOrderService.getWorkerTeamsByBizNumber(bizNumber);

		// EMP_NAME λͺ©λ‘ κ°?? Έ?€κΈ?
		List<String> employeeNames = employeeProductionService.getEmployeeNamesByBizNumber(bizNumber);

		// WORK_PLACE λͺ©λ‘ κ°?? Έ?€κΈ?
		List<String> workPlaces = workOrderService.getWorkPlacesByBizNumber(bizNumber);

		// ??¬ λ‘κ·Έ?Έ? ?¬?©?? EMP_NAME κ°?? Έ?€κΈ?
		String directorName = employeeProductionService.getEmployeeNameByUuid(uuid);

		model.addAttribute("itemList", itemList); // stock μ»¬λΌ? ?¬?¨? ??΄? λͺ©λ‘
		model.addAttribute("itemQtyMap", itemQtyMap);
		model.addAttribute("workerTeams", workerTeams);
		model.addAttribute("employeeNames", employeeNames);
		model.addAttribute("workPlaces", workPlaces);
		model.addAttribute("directorName", directorName);

		return "productionStock/productionWorkOrderCreate"; // JSP ??Ό κ²½λ‘
	}

	@RequestMapping(value = "/productionWorkOrderInsert.do", method = RequestMethod.POST)
	public String insertWorkOrder(@RequestParam("itemName") String itemName, @RequestParam("itemCode") String itemCode,
			@RequestParam("taskName") String taskName, @RequestParam("startDate") String startDateStr, // ? μ§λ§ ?? ₯λ°μ
			@RequestParam("endExDate") String endExDateStr, // ? μ§λ§ ?? ₯λ°μ
			@RequestParam("qty") int qty, @RequestParam("progressStatus") String progressStatus,
			@RequestParam("workerTeam") String workerTeam, @RequestParam("worker") String worker,
			@RequestParam("workPlace") String workPlace, @RequestParam("wDirector") String wDirector,
			HttpSession session) throws Exception {
		
		if (itemName.contains(" ?¬κ³? :")) {
	        itemName = itemName.split(" ?¬κ³? :")[0];  // "?λͺ? A" λΆ?λΆλ§ μΆμΆ
	    }
		// ?Έ??? biz_number?? uuid κ°?? Έ?€κΈ?
		String bizNumber = (String) session.getAttribute("biz_number");
		String uuid = (String) session.getAttribute("uuid");

		// ORDER_NUMBER ??±: biz_number + 'W' + ??¬ ????€?¬?
		String orderNumber = bizNumber + "W" + System.currentTimeMillis();

		// startDate?? endExDateλ₯? Timestampλ‘? λ³???λ©? ??¬ ?κ°μ μΆκ?
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date parsedStartDate = dateFormat.parse(startDateStr);

		// ??¬ ?κ°μ κ°?? Έ??? ?€? 
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(parsedStartDate);
		calendar.set(Calendar.HOUR_OF_DAY, Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
		calendar.set(Calendar.MINUTE, Calendar.getInstance().get(Calendar.MINUTE));
		calendar.set(Calendar.SECOND, Calendar.getInstance().get(Calendar.SECOND));

		Timestamp startDate = new Timestamp(calendar.getTimeInMillis());

		// endExDate μ²λ¦¬ (??¬ ?κ°μ μΆκ??μ§? ?κ³? κΈ°λ³Έ ?κ°? 00:00:00?Όλ‘? ?€? )
		Date parsedEndExDate = dateFormat.parse(endExDateStr);
		Timestamp endExDate = new Timestamp(parsedEndExDate.getTime());

		// WorkOrderDTO κ°μ²΄ ??± λ°? ?°?΄?° ?€? 
		WorkOrderDTO workOrderDTO = new WorkOrderDTO();
		workOrderDTO.setOrderNumber(orderNumber);
		workOrderDTO.setItemName(itemName);
		workOrderDTO.setItemCode(itemCode);
		workOrderDTO.setTaskName(taskName);
		workOrderDTO.setStartDate(startDate); // λ³??? Timestamp ?€? 
		workOrderDTO.setEndExDate(endExDate); // λ³??? Timestamp ?€? 
		workOrderDTO.setQty(qty);
		workOrderDTO.setProgressStatus(progressStatus);
		workOrderDTO.setWorkerTeam(workerTeam);
		workOrderDTO.setWorker(worker);
		workOrderDTO.setWorkPlace(workPlace);
		workOrderDTO.setwDirector(wDirector);
		workOrderDTO.setBizNumber(bizNumber);
		workOrderDTO.setUuid(uuid);

		// ??μ§??? ?°?΄?° ???₯
		workOrderService.insertWorkOrder(workOrderDTO);

		// ???₯ ? ??μ§??? λ¦¬μ€?Έ ??΄μ§?λ‘? λ¦¬λ€?΄? ?Έ
		return "redirect:/productionWorkorder.do";
	}

	// ?? μ§??? ??Έλ³΄κΈ° λ©μ?
	@RequestMapping(value = "/getProductionWorkOrderDetails.do", method = RequestMethod.GET)
	public String getProductionWorkOrderDetails(@RequestParam("orderNumber") String orderNumber, Model model) {

		// orderNumberλ‘? ?? μ§??? μ‘°ν
		WorkOrderDTO workOrderDetails = workOrderService.getWorkOrderByOrderNumber(orderNumber);

		// μ‘°ν? ?? μ§???λ₯? λͺ¨λΈ? μΆκ???¬ JSPλ‘? ? ?¬
		model.addAttribute("workOrderDetails", workOrderDetails);

		// ??Έ ? λ³? ??΄μ§?λ‘? ?΄?
		return "productionStock/productionWorkOrderDetail";
	}

	// ?? μ§??? ??  ??΄μ§?λ‘? ?΄?
	@RequestMapping(value = "/productionWorkOrderDetailUpdate.do", method = RequestMethod.GET)
	public String updateProductionWorkOrderDetails(@RequestParam("orderNumber") String orderNumber, Model model,
			HttpSession session) {
		// ?Έ??? biz_number?? uuid κ°?? Έ?€κΈ?
		String bizNumber = (String) session.getAttribute("biz_number");

		// orderNumberλ‘? ?? μ§??? μ‘°ν
		WorkOrderDTO workOrderDetails = workOrderService.getWorkOrderByOrderNumber(orderNumber);

		List<String> employeeNames = employeeProductionService.getEmployeeNamesByBizNumber(bizNumber);

		// μ‘°ν? ?? μ§???λ₯? λͺ¨λΈ? μΆκ???¬ ??  ??΄μ§?λ‘? ? ?¬
		model.addAttribute("workOrderDetails", workOrderDetails);
		model.addAttribute("employeeNames", employeeNames);

		// ??  ??΄μ§?λ‘? ?΄?
		return "productionStock/productionWorkOrderDetailUpdate";

	}

	// ?? μ§??? ??  ?λ£? μ²λ¦¬
	@RequestMapping(value = "/updateProductionWorkOrderUpdateDo.do", method = RequestMethod.POST)
	public String insertWorkOrder(@RequestParam("taskName") String taskName,
			@RequestParam("startDate") String startDateStr, // ? μ§λ§ ?? ₯λ°μ
			@RequestParam("endExDate") String endExDateStr, // ? μ§λ§ ?? ₯λ°μ
			@RequestParam("qty") int qty, @RequestParam("progressStatus") String progressStatus,
			@RequestParam("workerTeam") String workerTeam, @RequestParam("worker") List<String> workerList,
			@RequestParam("workPlace") String workPlace, @RequestParam("orderNumber") String orderNumber,
			HttpSession session) throws Exception {

		// startDateλ₯? Timestampλ‘? λ³???λ©? ??¬ ?κ°μ μΆκ?
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date parsedStartDate = dateFormat.parse(startDateStr);

		// ??¬ ?κ°? ?€? 
		Calendar startCalendar = Calendar.getInstance();
		startCalendar.setTime(parsedStartDate);
		startCalendar.set(Calendar.HOUR_OF_DAY, Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
		startCalendar.set(Calendar.MINUTE, Calendar.getInstance().get(Calendar.MINUTE));
		startCalendar.set(Calendar.SECOND, Calendar.getInstance().get(Calendar.SECOND));

		Timestamp startDate = new Timestamp(startCalendar.getTimeInMillis());

		SimpleDateFormat enddateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date parsedEndExDate = enddateFormat.parse(endExDateStr);

		// WorkOrderDTO κ°μ²΄ ??± λ°? ?°?΄?° ?€? 
		WorkOrderDTO workOrderDTO = new WorkOrderDTO();
		workOrderDTO.setOrderNumber(orderNumber);
		workOrderDTO.setTaskName(taskName);
		workOrderDTO.setStartDate(startDate); // λ³??? Timestamp ?€? 
		workOrderDTO.setEndExDate(parsedEndExDate);
		workOrderDTO.setQty(qty);
		workOrderDTO.setProgressStatus(progressStatus);
		workOrderDTO.setWorkerTeam(workerTeam);

		String worker = String.join(",", workerList);
		workOrderDTO.setWorker(worker);

		workOrderDTO.setWorkPlace(workPlace);

		// ??¬ ?κ°μ ????€?¬?λ‘? ?€? 
		Timestamp updateDate = new Timestamp(System.currentTimeMillis());
		workOrderDTO.setUpdateDate(updateDate);

		// progressStatusκ°? "?? ?λ£?"?΄λ©? endDate? ??¬ ?κ°μ ?£κ³?, κ·Έλ μ§? ??Όλ©? nullλ‘? ?€? 
		if ("?? ?λ£?".equals(progressStatus)) {
			Timestamp endDate = new Timestamp(System.currentTimeMillis());
			workOrderDTO.setEndDate(endDate);
		} else {
			workOrderDTO.setEndDate(null);
		}

		// ??μ§??? ?°?΄?° ??°?΄?Έ
		workOrderService.updateWorkOrder(workOrderDTO);

		// ???₯ ? ??μ§??? λ¦¬μ€?Έ ??΄μ§?λ‘? λ¦¬λ€?΄? ?Έ
		return "redirect:/productionWorkorder.do";
	}

	// ??μ§??? ?­?  μ²λ¦¬ λ©μ?
	@PostMapping("/deleteProductionWorkOrder.do")
	public String deleteProductionWorkOrder(@RequestParam("orderNumber") String orderNumber, HttpSession session) {

		// ?΄?Ή orderNumber? ?΄?Ή?? ??μ§???λ₯? ?­? 
		workOrderService.deleteWorkOrder(orderNumber);

		// ?­?  ? ??μ§??? λ¦¬μ€?Έ ??΄μ§?λ‘? λ¦¬λ€?΄? ?Έ
		return "redirect:/productionWorkorder.do";
	}
}
