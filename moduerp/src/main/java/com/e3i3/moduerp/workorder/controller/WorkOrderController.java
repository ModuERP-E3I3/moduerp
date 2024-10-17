package com.e3i3.moduerp.workorder.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
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

import com.e3i3.moduerp.company.model.service.CompanyService;
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

	@Autowired
	private CompanyService companyService;

	@RequestMapping(value = "/productionWorkorder.do", method = RequestMethod.GET)
	public String showWorkOrders(@RequestParam(value = "page", defaultValue = "1") int page, Model model,
			HttpSession session) {
		// 세션에서 biz_number 가져오기
		String bizNumber = (String) session.getAttribute("biz_number");

		// DB에서 biz_number로 해당하는 WorkOrder 목록 가져오기
		List<WorkOrderDTO> workOrderList = workOrderService.getWorkOrdersByBizNumber(bizNumber);

		// 모듈 등급 검사
		String moduleGrades = companyService.selectCompanyModuleGradesByBizNumber(bizNumber);

		if (moduleGrades != null) {
			// 쉼표(,)로 문자열을 분리하여 배열로 반환
			String[] gradesArray = moduleGrades.split(",");
			// 배열을 List로 변환
			List<String> gradesList = Arrays.asList(gradesArray);
			// P_IN이 리스트에 있는지 검사
			if (gradesList.contains("WO")) {
				System.out.println("WO이 리스트에 포함되어 있습니다.");
			} else {
				System.out.println("WO이 리스트에 없습니다.");
				return "common/moduleGradesError";
			}
		} else {
			return "common/moduleGradesError";

		}

		// 페이지당 아이템 수 설정
		int itemsPerPage = 10;

		// 총 아이템 수 계산
		int totalItems = workOrderList.size();

		// 총 페이지 수 계산
		int totalPages = (int) Math.ceil((double) totalItems / itemsPerPage);

		// 시작 인덱스와 종료 인덱스 계산
		int startIndex = (page - 1) * itemsPerPage;
		int endIndex = Math.min(startIndex + itemsPerPage, totalItems);

		// 서브리스트로 페이지네이션된 항목들 추출
		List<WorkOrderDTO> paginatedList = workOrderList.subList(startIndex, endIndex);

		// 모델에 WorkOrder 목록, 총 페이지 수, 현재 페이지 추가
		model.addAttribute("workOrderList", paginatedList);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("currentPage", page);

		return "productionStock/productionWorkorder"; // JSP 파일 경로 반환
	}

	@RequestMapping(value = "/productionWorkOrderFilter.do", method = RequestMethod.GET)
	public String showWorkOrdersFilter(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "filterOption", required = false) String option,
			@RequestParam(value = "filterText", required = false) String filterText,
			@RequestParam(value = "startDate", required = false) String startDate,
			@RequestParam(value = "endDate", required = false) String endDate, Model model, HttpSession session) {
		// 세션에서 biz_number 가져오기
		String bizNumber = (String) session.getAttribute("biz_number");

		// DB에서 biz_number로 해당하는 WorkOrder 목록 가져오기
		List<WorkOrderDTO> workOrderList = workOrderService.getWorkOrdersByBizNumber(bizNumber);

		// 필터링 로직 추가
		if (option != null && filterText != null) {
			if (startDate != null && !startDate.isEmpty() && endDate != null && !endDate.isEmpty()) {
				System.out.println("날짜있는거 실행");
				workOrderList = workOrderService.getWorkOrderByFilterDate(bizNumber, option, filterText, startDate,
						endDate);
			} else if (startDate == null || startDate.isEmpty()) {
				System.out.println("날짜없는거 실행");
				workOrderList = workOrderService.getWorkOrderByFilter(bizNumber, option, filterText);
			} else {
				System.out.println("실행 못함");
				workOrderList = workOrderService.getWorkOrdersByBizNumber(bizNumber);
			}
		} else {
			workOrderList = workOrderService.getWorkOrdersByBizNumber(bizNumber);
		}

		// 페이지당 아이템 수 설정
		int itemsPerPage = 10;

		// 총 아이템 수 계산
		int totalItems = workOrderList.size();

		// 총 페이지 수 계산
		int totalPages = (int) Math.ceil((double) totalItems / itemsPerPage);

		// 시작 인덱스와 종료 인덱스 계산
		int startIndex = (page - 1) * itemsPerPage;
		int endIndex = Math.min(startIndex + itemsPerPage, totalItems);

		// 서브리스트로 페이지네이션된 항목들 추출
		List<WorkOrderDTO> paginatedList = workOrderList.subList(startIndex, endIndex);

		// 모델에 WorkOrder 목록, 총 페이지 수, 현재 페이지 추가
		model.addAttribute("workOrderList", paginatedList);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("currentPage", page);
		model.addAttribute("option", option);
		model.addAttribute("filterText", filterText);
		model.addAttribute("startDate", startDate);
		model.addAttribute("endDate", endDate);

		return "productionStock/productionWorkorderFilter"; // JSP 파일 경로 반환
	}

	@RequestMapping(value = "/productionWorkOrderCreate.do", method = RequestMethod.GET)
	public String showWorkOrderCreatePage(Model model, HttpSession session) {
		String bizNumber = (String) session.getAttribute("biz_number");
		String uuid = (String) session.getAttribute("uuid");

		// ITEM_CODE가 biz_number + "P"로 시작하는 항목 목록을 stock 컬럼과 함께 가져오기
		List<ItemDTO> itemList = itemProductionstockService.getItemNamesAndStockByBizNumberStartingWith(bizNumber);

		// 각 item의 총 QTY를 계산하여 저장
		Map<String, Integer> itemQtyMap = new HashMap<>();
		for (ItemDTO item : itemList) {
			int totalQty = workOrderService.getTotalQtyByItemCode(item.getItemCode());
			itemQtyMap.put(item.getItemCode(), totalQty);
		}

		// WORKER_TEAM 목록 가져오기
		List<String> workerTeams = workOrderService.getWorkerTeamsByBizNumber(bizNumber);

		// EMP_NAME 목록 가져오기
		List<String> employeeNames = employeeProductionService.getEmployeeNamesByBizNumber(bizNumber);

		// WORK_PLACE 목록 가져오기
		List<String> workPlaces = workOrderService.getWorkPlacesByBizNumber(bizNumber);

		// 현재 로그인한 사용자의 EMP_NAME 가져오기
		String directorName = employeeProductionService.getEmployeeNameByUuid(uuid);

		model.addAttribute("itemList", itemList); // stock 컬럼과 함께 가져온 항목 목록
		model.addAttribute("itemQtyMap", itemQtyMap);
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

		if (itemName.contains(" | 재고 :")) {
			itemName = itemName.split(" | 재고 :")[0];
		}

		// 세션에서 biz_number와 uuid 가져오기
		String bizNumber = (String) session.getAttribute("biz_number");
		String uuid = (String) session.getAttribute("uuid");

		// ORDER_NUMBER 생성: biz_number + 'W' + 현재 타임스탬프
		String orderNumber = bizNumber + "W" + System.currentTimeMillis();

		// startDate와 endExDate를 Timestamp로 변환하여 시간 정보를 추출
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date parsedStartDate = dateFormat.parse(startDateStr);

		// 현재 시간을 추가하여 Timestamp로 변환
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(parsedStartDate);
		calendar.set(Calendar.HOUR_OF_DAY, Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
		calendar.set(Calendar.MINUTE, Calendar.getInstance().get(Calendar.MINUTE));
		calendar.set(Calendar.SECOND, Calendar.getInstance().get(Calendar.SECOND));

		Timestamp startDate = new Timestamp(calendar.getTimeInMillis());

		// endExDate 처리 (기본 시간은 00:00:00으로 설정)
		Date parsedEndExDate = dateFormat.parse(endExDateStr);
		Timestamp endExDate = new Timestamp(parsedEndExDate.getTime());

		// itemName에서 세부 정보 앞부분만 추출
		String SitemName = itemName.split("\\|")[0].trim();
		System.out.println(SitemName);

		// WorkOrderDTO 객체 생성 및 데이터 설정
		WorkOrderDTO workOrderDTO = new WorkOrderDTO();
		workOrderDTO.setOrderNumber(orderNumber);
		workOrderDTO.setItemName(SitemName);
		workOrderDTO.setItemCode(itemCode);
		workOrderDTO.setTaskName(taskName);
		workOrderDTO.setStartDate(startDate); // 시작일을 Timestamp로 설정
		workOrderDTO.setEndExDate(endExDate); // 종료 예상일을 Timestamp로 설정
		workOrderDTO.setQty(qty);
		workOrderDTO.setProgressStatus(progressStatus);
		workOrderDTO.setWorkerTeam(workerTeam);
		workOrderDTO.setWorker(worker);
		workOrderDTO.setWorkPlace(workPlace);
		workOrderDTO.setwDirector(wDirector);
		workOrderDTO.setBizNumber(bizNumber);
		workOrderDTO.setUuid(uuid);

		// 작업 지시서 DB에 삽입
		workOrderService.insertWorkOrder(workOrderDTO);

		// 작업 지시서 목록 페이지로 리다이렉트
		return "redirect:/productionWorkorder.do";
	}

	// 작업 지시서 상세 보기 메서드
	@RequestMapping(value = "/getProductionWorkOrderDetails.do", method = RequestMethod.GET)
	public String getProductionWorkOrderDetails(@RequestParam("orderNumber") String orderNumber, Model model) {

		// orderNumber로 작업 지시서 조회
		WorkOrderDTO workOrderDetails = workOrderService.getWorkOrderByOrderNumber(orderNumber);

		// 조회한 작업 지시서를 모델에 추가하여 JSP로 전달
		model.addAttribute("workOrderDetails", workOrderDetails);

		// 작업 지시서 상세 보기 페이지로 이동
		return "productionStock/productionWorkOrderDetail";
	}

	// 작업 지시서 상세 정보 업데이트 페이지로 이동
	@RequestMapping(value = "/productionWorkOrderDetailUpdate.do", method = RequestMethod.GET)
	public String updateProductionWorkOrderDetails(@RequestParam("orderNumber") String orderNumber, Model model,
			HttpSession session) {
		// 세션에서 biz_number와 uuid 가져오기
		String bizNumber = (String) session.getAttribute("biz_number");

		// orderNumber로 작업 지시서 조회
		WorkOrderDTO workOrderDetails = workOrderService.getWorkOrderByOrderNumber(orderNumber);

		// biz_number로 직원 이름 목록 가져오기
		List<String> employeeNames = employeeProductionService.getEmployeeNamesByBizNumber(bizNumber);

		// 조회한 작업 지시서 및 직원 이름 목록을 모델에 추가하여 JSP로 전달
		model.addAttribute("workOrderDetails", workOrderDetails);
		model.addAttribute("employeeNames", employeeNames);

		// 작업 지시서 상세 정보 업데이트 페이지로 이동
		return "productionStock/productionWorkOrderDetailUpdate";
	}

	// 작업 지시서 업데이트 처리
	@RequestMapping(value = "/updateProductionWorkOrderUpdateDo.do", method = RequestMethod.POST)
	public String insertWorkOrder(@RequestParam("taskName") String taskName,
			@RequestParam("startDate") String startDateStr, // 날짜만 입력받음
			@RequestParam("endExDate") String endExDateStr, // 날짜만 입력받음
			@RequestParam("qty") int qty, @RequestParam("progressStatus") String progressStatus,
			@RequestParam("workerTeam") String workerTeam, @RequestParam("worker") List<String> workerList,
			@RequestParam("workPlace") String workPlace, @RequestParam("orderNumber") String orderNumber,
			HttpSession session) throws Exception {

		// startDate를 Timestamp로 변환하여 시간 정보를 추가
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date parsedStartDate = dateFormat.parse(startDateStr);

		// 시작 시간 설정
		Calendar startCalendar = Calendar.getInstance();
		startCalendar.setTime(parsedStartDate);
		startCalendar.set(Calendar.HOUR_OF_DAY, Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
		startCalendar.set(Calendar.MINUTE, Calendar.getInstance().get(Calendar.MINUTE));
		startCalendar.set(Calendar.SECOND, Calendar.getInstance().get(Calendar.SECOND));

		Timestamp startDate = new Timestamp(startCalendar.getTimeInMillis());

		SimpleDateFormat enddateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date parsedEndExDate = enddateFormat.parse(endExDateStr);

		// WorkOrderDTO 객체 생성 및 데이터 설정
		WorkOrderDTO workOrderDTO = new WorkOrderDTO();
		workOrderDTO.setOrderNumber(orderNumber);
		workOrderDTO.setTaskName(taskName);
		workOrderDTO.setStartDate(startDate);
		workOrderDTO.setEndExDate(parsedEndExDate);
		workOrderDTO.setQty(qty);
		workOrderDTO.setProgressStatus(progressStatus);
		workOrderDTO.setWorkerTeam(workerTeam);

		// 여러 작업자를 하나의 문자열로 결합
		String worker = String.join(",", workerList);
		workOrderDTO.setWorker(worker);

		workOrderDTO.setWorkPlace(workPlace);

		// 업데이트 시간을 현재 시간으로 설정
		Timestamp updateDate = new Timestamp(System.currentTimeMillis());
		workOrderDTO.setUpdateDate(updateDate);

		// progressStatus가 "작업 완료"일 경우 endDate를 현재 시간으로 설정, 그렇지 않으면 null로 설정
		if ("작업 완료".equals(progressStatus)) {
			Timestamp endDate = new Timestamp(System.currentTimeMillis());
			workOrderDTO.setEndDate(endDate);
		} else {
			workOrderDTO.setEndDate(null);
		}

		// 작업 지시서 업데이트 처리
		workOrderService.updateWorkOrder(workOrderDTO);

		// 작업 지시서 목록 페이지로 리다이렉트
		return "redirect:/productionWorkorder.do";
	}

	// 작업 지시서 삭제 처리 메서드
	@PostMapping("/deleteProductionWorkOrder.do")
	public String deleteProductionWorkOrder(@RequestParam("orderNumber") String orderNumber, HttpSession session) {

		// 전달된 orderNumber로 작업 지시서를 삭제 처리
		workOrderService.deleteWorkOrder(orderNumber);

		// 작업 지시서 목록 페이지로 리다이렉트
		return "redirect:/productionWorkorder.do";
	}

}
