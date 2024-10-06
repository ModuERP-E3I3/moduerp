package com.e3i3.moduerp.workorder.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
		// 세션에서 biz_number 꺼내기
		String bizNumber = (String) session.getAttribute("biz_number");

		// DB에서 biz_number에 해당하는 WorkOrder 목록 가져오기
		List<WorkOrderDTO> workOrderList = workOrderService.getWorkOrdersByBizNumber(bizNumber);

		// 페이지당 항목 수 설정
		int itemsPerPage = 10;

		// 총 항목 수 계산
		int totalItems = workOrderList.size();

		// 총 페이지 수 계산
		int totalPages = (int) Math.ceil((double) totalItems / itemsPerPage);

		// 시작 인덱스 계산
		int startIndex = (page - 1) * itemsPerPage;
		int endIndex = Math.min(startIndex + itemsPerPage, totalItems);

		// 서브리스트 생성 (해당 페이지의 항목들만 추출)
		List<WorkOrderDTO> paginatedList = workOrderList.subList(startIndex, endIndex);

		// 모델에 WorkOrder 목록, 총 페이지 수, 현재 페이지 추가
		model.addAttribute("workOrderList", paginatedList);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("currentPage", page);

		return "productionStock/productionWorkorder"; // JSP 파일 경로 반환
	}

	@RequestMapping(value = "/productionWorkOrderCreate.do", method = RequestMethod.GET)
	public String showWorkOrderCreatePage(Model model, HttpSession session) {
		String bizNumber = (String) session.getAttribute("biz_number");
		String uuid = (String) session.getAttribute("uuid");

		// ITEM_CODE가 biz_number + "P"로 시작하는 아이템 목록과 stock 컬럼 가져오기
		List<ItemDTO> itemList = itemProductionstockService.getItemNamesAndStockByBizNumberStartingWith(bizNumber);

		// WORKER_TEAM 목록 가져오기
		List<String> workerTeams = workOrderService.getWorkerTeamsByBizNumber(bizNumber);

		// EMP_NAME 목록 가져오기
		List<String> employeeNames = employeeProductionService.getEmployeeNamesByBizNumber(bizNumber);

		// WORK_PLACE 목록 가져오기
		List<String> workPlaces = workOrderService.getWorkPlacesByBizNumber(bizNumber);

		// 현재 로그인한 사용자의 EMP_NAME 가져오기
		String directorName = employeeProductionService.getEmployeeNameByUuid(uuid);

		model.addAttribute("itemList", itemList); // stock 컬럼을 포함한 아이템 목록
		model.addAttribute("workerTeams", workerTeams);
		model.addAttribute("employeeNames", employeeNames);
		model.addAttribute("workPlaces", workPlaces);
		model.addAttribute("directorName", directorName);

		return "productionStock/productionWorkOrderCreate"; // JSP 파일 경로
	}

	@RequestMapping(value = "/productionWorkOrderInsert.do", method = RequestMethod.POST)
	public String insertWorkOrder(@RequestParam("itemName") String itemName, @RequestParam("itemCode") String itemCode,
			@RequestParam("taskName") String taskName, @RequestParam("startDate") String startDateStr, // 날짜만 입력받음
			@RequestParam("endExDate") String endExDateStr, // 날짜만 입력받음
			@RequestParam("qty") int qty, @RequestParam("progressStatus") String progressStatus,
			@RequestParam("workerTeam") String workerTeam, @RequestParam("worker") String worker,
			@RequestParam("workPlace") String workPlace, @RequestParam("wDirector") String wDirector,
			HttpSession session) throws Exception {

		// 세션에서 biz_number와 uuid 가져오기
		String bizNumber = (String) session.getAttribute("biz_number");
		String uuid = (String) session.getAttribute("uuid");

		// ORDER_NUMBER 생성: biz_number + 'W' + 현재 타임스탬프
		String orderNumber = bizNumber + "W" + System.currentTimeMillis();

		// startDate와 endExDate를 Timestamp로 변환하며 현재 시간을 추가
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date parsedStartDate = dateFormat.parse(startDateStr);

		// 현재 시간을 가져와서 설정
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(parsedStartDate);
		calendar.set(Calendar.HOUR_OF_DAY, Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
		calendar.set(Calendar.MINUTE, Calendar.getInstance().get(Calendar.MINUTE));
		calendar.set(Calendar.SECOND, Calendar.getInstance().get(Calendar.SECOND));

		Timestamp startDate = new Timestamp(calendar.getTimeInMillis());

		// endExDate 처리 (현재 시간을 추가하지 않고 기본 시간 00:00:00으로 설정)
		Date parsedEndExDate = dateFormat.parse(endExDateStr);
		Timestamp endExDate = new Timestamp(parsedEndExDate.getTime());

		// WorkOrderDTO 객체 생성 및 데이터 설정
		WorkOrderDTO workOrderDTO = new WorkOrderDTO();
		workOrderDTO.setOrderNumber(orderNumber);
		workOrderDTO.setItemName(itemName);
		workOrderDTO.setItemCode(itemCode);
		workOrderDTO.setTaskName(taskName);
		workOrderDTO.setStartDate(startDate); // 변환된 Timestamp 설정
		workOrderDTO.setEndExDate(endExDate); // 변환된 Timestamp 설정
		workOrderDTO.setQty(qty);
		workOrderDTO.setProgressStatus(progressStatus);
		workOrderDTO.setWorkerTeam(workerTeam);
		workOrderDTO.setWorker(worker);
		workOrderDTO.setWorkPlace(workPlace);
		workOrderDTO.setwDirector(wDirector);
		workOrderDTO.setBizNumber(bizNumber);
		workOrderDTO.setUuid(uuid);

		// 작업지시서 데이터 저장
		workOrderService.insertWorkOrder(workOrderDTO);

		// 저장 후 작업지시서 리스트 페이지로 리다이렉트
		return "redirect:/productionWorkorder.do";
	}

}
