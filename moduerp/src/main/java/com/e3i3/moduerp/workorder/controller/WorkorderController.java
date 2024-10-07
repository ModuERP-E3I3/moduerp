package com.e3i3.moduerp.workorder.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
public class WorkorderController {

	@Autowired
	private WorkOrderService workOrderService;

	@Autowired
	private ItemProductionstockService itemProductionstockService;

	@Autowired
	private EmployeeProductionService employeeProductionService;

	@RequestMapping(value = "/productionWorkorder.do", method = RequestMethod.GET)
	public String showWorkOrders(@RequestParam(value = "page", defaultValue = "1") int page, Model model,
			HttpSession session) {
		// �꽭�뀡�뿉�꽌 biz_number 爰쇰궡湲�
		String bizNumber = (String) session.getAttribute("biz_number");

		// DB�뿉�꽌 biz_number�뿉 �빐�떦�븯�뒗 WorkOrder 紐⑸줉 媛��졇�삤湲�
		List<WorkOrderDTO> workOrderList = workOrderService.getWorkOrdersByBizNumber(bizNumber);

		// �럹�씠吏��떦 �빆紐� �닔 �꽕�젙
		int itemsPerPage = 10;

		// 珥� �빆紐� �닔 怨꾩궛
		int totalItems = workOrderList.size();

		// 珥� �럹�씠吏� �닔 怨꾩궛
		int totalPages = (int) Math.ceil((double) totalItems / itemsPerPage);

		// �떆�옉 �씤�뜳�뒪 怨꾩궛
		int startIndex = (page - 1) * itemsPerPage;
		int endIndex = Math.min(startIndex + itemsPerPage, totalItems);

		// �꽌釉뚮━�뒪�듃 �깮�꽦 (�빐�떦 �럹�씠吏��쓽 �빆紐⑸뱾留� 異붿텧)
		List<WorkOrderDTO> paginatedList = workOrderList.subList(startIndex, endIndex);

		// 紐⑤뜽�뿉 WorkOrder 紐⑸줉, 珥� �럹�씠吏� �닔, �쁽�옱 �럹�씠吏� 異붽�
		model.addAttribute("workOrderList", paginatedList);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("currentPage", page);

		return "productionStock/productionWorkorder"; // JSP �뙆�씪 寃쎈줈 諛섑솚
	}

	@RequestMapping(value = "/productionWorkOrderCreate.do", method = RequestMethod.GET)
	public String showWorkOrderCreatePage(Model model, HttpSession session) {
		String bizNumber = (String) session.getAttribute("biz_number");
		String uuid = (String) session.getAttribute("uuid");

		// ITEM_CODE媛� biz_number + "P"濡� �떆�옉�븯�뒗 �븘�씠�뀥 紐⑸줉怨� stock 而щ읆 媛��졇�삤湲�
		List<ItemDTO> itemList = itemProductionstockService.getItemNamesAndStockByBizNumberStartingWith(bizNumber);

		// WORKER_TEAM 紐⑸줉 媛��졇�삤湲�
		List<String> workerTeams = workOrderService.getWorkerTeamsByBizNumber(bizNumber);

		// EMP_NAME 紐⑸줉 媛��졇�삤湲�
		List<String> employeeNames = employeeProductionService.getEmployeeNamesByBizNumber(bizNumber);

		// WORK_PLACE 紐⑸줉 媛��졇�삤湲�
		List<String> workPlaces = workOrderService.getWorkPlacesByBizNumber(bizNumber);

		// �쁽�옱 濡쒓렇�씤�븳 �궗�슜�옄�쓽 EMP_NAME 媛��졇�삤湲�
		String directorName = employeeProductionService.getEmployeeNameByUuid(uuid);

		model.addAttribute("itemList", itemList); // stock 而щ읆�쓣 �룷�븿�븳 �븘�씠�뀥 紐⑸줉
		model.addAttribute("workerTeams", workerTeams);
		model.addAttribute("employeeNames", employeeNames);
		model.addAttribute("workPlaces", workPlaces);
		model.addAttribute("directorName", directorName);

		return "productionStock/productionWorkOrderCreate"; // JSP �뙆�씪 寃쎈줈
	}

	@RequestMapping(value = "/productionWorkOrderInsert.do", method = RequestMethod.POST)
	public String insertWorkOrder(@RequestParam("itemName") String itemName, @RequestParam("itemCode") String itemCode,
			@RequestParam("taskName") String taskName, @RequestParam("startDate") String startDateStr, // �궇吏쒕쭔 �엯�젰諛쏆쓬
			@RequestParam("endExDate") String endExDateStr, // �궇吏쒕쭔 �엯�젰諛쏆쓬
			@RequestParam("qty") int qty, @RequestParam("progressStatus") String progressStatus,
			@RequestParam("workerTeam") String workerTeam, @RequestParam("worker") String worker,
			@RequestParam("workPlace") String workPlace, @RequestParam("wDirector") String wDirector,
			HttpSession session) throws Exception {

		// �꽭�뀡�뿉�꽌 biz_number�� uuid 媛��졇�삤湲�
		String bizNumber = (String) session.getAttribute("biz_number");
		String uuid = (String) session.getAttribute("uuid");

		// ORDER_NUMBER �깮�꽦: biz_number + 'W' + �쁽�옱 ���엫�뒪�꺃�봽
		String orderNumber = bizNumber + "W" + System.currentTimeMillis();

		// startDate�� endExDate瑜� Timestamp濡� 蹂��솚�븯硫� �쁽�옱 �떆媛꾩쓣 異붽�
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date parsedStartDate = dateFormat.parse(startDateStr);

		// �쁽�옱 �떆媛꾩쓣 媛��졇���꽌 �꽕�젙
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(parsedStartDate);
		calendar.set(Calendar.HOUR_OF_DAY, Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
		calendar.set(Calendar.MINUTE, Calendar.getInstance().get(Calendar.MINUTE));
		calendar.set(Calendar.SECOND, Calendar.getInstance().get(Calendar.SECOND));

		Timestamp startDate = new Timestamp(calendar.getTimeInMillis());

		// endExDate 泥섎━ (�쁽�옱 �떆媛꾩쓣 異붽��븯吏� �븡怨� 湲곕낯 �떆媛� 00:00:00�쑝濡� �꽕�젙)
		Date parsedEndExDate = dateFormat.parse(endExDateStr);
		Timestamp endExDate = new Timestamp(parsedEndExDate.getTime());

		// WorkOrderDTO 媛앹껜 �깮�꽦 諛� �뜲�씠�꽣 �꽕�젙
		WorkOrderDTO workOrderDTO = new WorkOrderDTO();
		workOrderDTO.setOrderNumber(orderNumber);
		workOrderDTO.setItemName(itemName);
		workOrderDTO.setItemCode(itemCode);
		workOrderDTO.setTaskName(taskName);
		workOrderDTO.setStartDate(startDate); // 蹂��솚�맂 Timestamp �꽕�젙
		workOrderDTO.setEndExDate(endExDate); // 蹂��솚�맂 Timestamp �꽕�젙
		workOrderDTO.setQty(qty);
		workOrderDTO.setProgressStatus(progressStatus);
		workOrderDTO.setWorkerTeam(workerTeam);
		workOrderDTO.setWorker(worker);
		workOrderDTO.setWorkPlace(workPlace);
		workOrderDTO.setwDirector(wDirector);
		workOrderDTO.setBizNumber(bizNumber);
		workOrderDTO.setUuid(uuid);

		// �옉�뾽吏��떆�꽌 �뜲�씠�꽣 ���옣
		workOrderService.insertWorkOrder(workOrderDTO);

		// ���옣 �썑 �옉�뾽吏��떆�꽌 由ъ뒪�듃 �럹�씠吏�濡� 由щ떎�씠�젆�듃
		return "redirect:/productionWorkorder.do";
	}

}
