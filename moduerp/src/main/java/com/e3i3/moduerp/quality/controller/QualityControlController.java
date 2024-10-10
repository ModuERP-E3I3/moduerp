package com.e3i3.moduerp.quality.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.e3i3.moduerp.employee.model.service.EmployeeProductionService;
import com.e3i3.moduerp.quality.model.dto.QualityControlDTO;
import com.e3i3.moduerp.quality.model.service.QualityControlService;
import com.e3i3.moduerp.workorder.model.dto.WorkOrderDTO;
import com.e3i3.moduerp.workorder.model.service.WorkOrderService;

@Controller
@RequestMapping("/")
public class QualityControlController {

	@Autowired
	private QualityControlService qualityControlService;

	@Autowired
	private WorkOrderService workOrderService;

	@Autowired
	private EmployeeProductionService employeeProductionService;

	@RequestMapping(value = "/productionQuality.do", method = RequestMethod.GET)
	public String getAllQualityControls(@RequestParam(value = "page", defaultValue = "1") int page, Model model,
			HttpSession session) {
		// 세션에서 biz_number 가져오기
		String bizNumber = (String) session.getAttribute("biz_number");

		// 만약 biz_number가 세션에 없다면 로그인 페이지로 리다이렉트
		if (bizNumber == null) {
			return "redirect:/login.do";
		}

		// 서비스 호출하여 biz_number에 해당하는 품질 관리 데이터 가져오기
		List<QualityControlDTO> qualityControlList = qualityControlService.getQualityControlsByBizNumber(bizNumber);

		// 페이지당 항목 수
		int itemsPerPage = 10;

		// 총 항목 수
		int totalItems = qualityControlList.size();

		// 총 페이지 수
		int totalPages = (int) Math.ceil((double) totalItems / itemsPerPage);

		// 시작 인덱스 계산
		int startIndex = (page - 1) * itemsPerPage;
		int endIndex = Math.min(startIndex + itemsPerPage, totalItems);

		// 서브리스트 생성
		List<QualityControlDTO> paginatedList = qualityControlList.subList(startIndex, endIndex);

		// 모델에 추가
		model.addAttribute("qualityControlList", paginatedList);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("currentPage", page);

		// 품질 관리 JSP 페이지로 이동
		return "productionStock/productionQuality";
	}

	@RequestMapping(value = "/productionQualityFilter.do", method = RequestMethod.GET)
	public String getAllQualityControlsFilter(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "filterOption", required = false) String option,
			@RequestParam(value = "filterText", required = false) String filterText,
			@RequestParam(value = "startDate", required = false) String startDate,
			@RequestParam(value = "endDate", required = false) String endDate, Model model, HttpSession session) {
		// 세션에서 biz_number 가져오기
		String bizNumber = (String) session.getAttribute("biz_number");

		// 만약 biz_number가 세션에 없다면 로그인 페이지로 리다이렉트
		if (bizNumber == null) {
			return "redirect:/login.do";
		}

		// 서비스 호출하여 biz_number에 해당하는 품질 관리 데이터 가져오기
		List<QualityControlDTO> qualityControlList = qualityControlService.getQualityControlsByBizNumber(bizNumber);

		// 필터링 로직 추가
		if (option != null && filterText != null) {
			if (startDate != null && !startDate.isEmpty() && endDate != null && !endDate.isEmpty()) {
				System.out.println("날짜있는거 실행");
				qualityControlList = qualityControlService.getQualityByFilterDate(bizNumber, option, filterText,
						startDate, endDate);
			} else if (startDate == null || startDate.isEmpty()) {
				System.out.println("날짜없는거 실행");
				qualityControlList = qualityControlService.getQualityByFilter(bizNumber, option, filterText);
			} else {
				System.out.println("실행 못함");
				qualityControlList = qualityControlService.getQualityControlsByBizNumber(bizNumber);
			}
		} else {
			qualityControlList = qualityControlService.getQualityControlsByBizNumber(bizNumber);
		}

		// 페이지당 아이템 수 설정
		int itemsPerPage = 10;

		// 총 아이템 수 계산
		int totalItems = qualityControlList.size();

		// 총 페이지 수 계산
		int totalPages = (int) Math.ceil((double) totalItems / itemsPerPage);

		// 시작 인덱스와 종료 인덱스 계산
		int startIndex = (page - 1) * itemsPerPage;
		int endIndex = Math.min(startIndex + itemsPerPage, totalItems);

		// 서브리스트로 페이지네이션된 항목들 추출

		// 서브리스트 생성
		List<QualityControlDTO> paginatedList = qualityControlList.subList(startIndex, endIndex);

		// 모델에 추가
		model.addAttribute("qualityControlList", paginatedList);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("currentPage", page);
		model.addAttribute("option", option);
		model.addAttribute("filterText", filterText);
		model.addAttribute("startDate", startDate);
		model.addAttribute("endDate", endDate);

		// 품질 관리 JSP 페이지로 이동
		return "productionStock/productionQualityFilter";
	}

	// 품질 관리 등록 페이지로 이동하는 메서드
	@RequestMapping(value = "/productionQqualityCreate.do", method = RequestMethod.GET)
	public String showQualityControlCreatePage(Model model, HttpSession session) {

		String bizNumber = (String) session.getAttribute("biz_number");

		// '작업 완료' 상태의 작업지시서 데이터 가져오기
		List<WorkOrderDTO> completedWorkOrders = workOrderService.getCompletedWorkOrders();

		// EMP_NAME 목록 가져오기
		List<String> employeeNames = employeeProductionService.getEmployeeNamesByBizNumber(bizNumber);

		// 각 작업지시서에 대해 INSPEC_QTY의 합계를 계산하고 추가
		Map<String, Integer> inspecQtyMap = new HashMap<>();
		for (WorkOrderDTO workOrder : completedWorkOrders) {
			int totalInspecQty = qualityControlService.getTotalInspecQtyByOrderNumber(workOrder.getOrderNumber());
			inspecQtyMap.put(workOrder.getOrderNumber(), totalInspecQty);
		}

		// 모델에 추가
		model.addAttribute("completedWorkOrders", completedWorkOrders);
		model.addAttribute("employeeNames", employeeNames);
		model.addAttribute("inspecQtyMap", inspecQtyMap); // 합계 정보를 추가

		return "productionStock/productionQualityCreate"; // JSP 파일 경로 반환
	}

	@RequestMapping(value = "/productionQualityInsert.do", method = RequestMethod.POST)
	public String insertProductionQuality(@RequestParam("workOrderName") String workOrderName,
			@RequestParam("orderNumber") String orderNumber, @RequestParam("qcStartDate") String qcStartDate,
			@RequestParam("qcEndExDate") String qcEndExDate, @RequestParam("inspectionType") String inspectionType,
			@RequestParam("progressStatus") String progressStatus, @RequestParam("qcQty") int qcQty,
			@RequestParam("worker") List<String> workers, // List<String>으로 받기
			Model model, HttpSession session) throws ParseException {

		// List<String> workers를 String으로 변환 (쉼표로 구분)
		String workersAsString = String.join(", ", workers);

		String bizNumber = (String) session.getAttribute("biz_number");
		String userUuid = (String) session.getAttribute("uuid");

		// workOrderName에서 ' | 작업수량' 이전의 텍스트 추출
		String taskName = workOrderName.split("\\|")[0].trim(); // '|'를 기준으로 앞부분만 추출하고 공백 제거

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date parsedStartDate = dateFormat.parse(qcStartDate);

		// 현재 시간 설정
		Calendar startCalendar = Calendar.getInstance();
		startCalendar.setTime(parsedStartDate);
		startCalendar.set(Calendar.HOUR_OF_DAY, Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
		startCalendar.set(Calendar.MINUTE, Calendar.getInstance().get(Calendar.MINUTE));
		startCalendar.set(Calendar.SECOND, Calendar.getInstance().get(Calendar.SECOND));

		Timestamp startDate = new Timestamp(startCalendar.getTimeInMillis());

		// endExDate 처리 (현재 시간을 추가하지 않고 기본 시간 00:00:00으로 설정)
		Date parsedEndExDate = dateFormat.parse(qcEndExDate);
		Timestamp endExDate = new Timestamp(parsedEndExDate.getTime());

		String inspecCode = bizNumber + "Q" + System.currentTimeMillis();

		QualityControlDTO qulityDTO = new QualityControlDTO();
		qulityDTO.setBizNumber(bizNumber);
		qulityDTO.setInspecCode(inspecCode);
		qulityDTO.setOrderNumber(orderNumber);
		qulityDTO.setUuid(userUuid);
		qulityDTO.setStartDate(startDate);
		qulityDTO.setEndExDate(endExDate);
		qulityDTO.setInspecType(inspectionType);
		qulityDTO.setProgressStatus(progressStatus);
		qulityDTO.setInspecQty(qcQty);
		qulityDTO.setqDirector(workersAsString);
		qulityDTO.setTaskName(taskName);

		qualityControlService.insertQulityControl(qulityDTO);

		// 처리 완료 후 리다이렉트
		return "redirect:/productionQuality.do"; // 품질관리 리스트 페이지로 리다이렉트
	}

	@RequestMapping(value = "/getProductionQualityDetails.do", method = RequestMethod.GET)
	public String getProductionQualityDetails(@RequestParam("inspecCode") String inspecCode, Model model) {

		// inspecCode로 품질 관리 데이터를 가져옴
		QualityControlDTO qualityControl = qualityControlService.getQualityControlByInspecCode(inspecCode);

		// 모델에 품질 관리 데이터를 추가
		model.addAttribute("qualityControl", qualityControl);

		// 세부 정보를 표시할 JSP 페이지로 이동
		return "productionStock/productionQualityDetail"; // JSP 파일 경로
	}

	@RequestMapping(value = "/productionQulityDetailUpdate.do", method = RequestMethod.GET)
	public String showQualityControlUpdatePage(@RequestParam("inspecCode") String inspecCode, Model model,
			HttpSession session) {
		// 세션에서 biz_number와 uuid 가져오기
		String bizNumber = (String) session.getAttribute("biz_number");

		// inspecCode로 품질 검사 데이터를 조회
		QualityControlDTO qualityControl = qualityControlService.getQualityControlByInspecCode(inspecCode);

		List<String> employeeNames = employeeProductionService.getEmployeeNamesByBizNumber(bizNumber);

		// 조회된 데이터를 모델에 추가하여 JSP 페이지로 전달
		model.addAttribute("qualityControl", qualityControl);
		model.addAttribute("employeeNames", employeeNames);

		// 품질 검사 수정 페이지로 이동
		return "productionStock/productionQualityDetailUpdate"; // JSP 파일 경로
	}

	@RequestMapping(value = "/updateProductionQulityUpdateDo.do", method = RequestMethod.POST)
	public String updateQualityControl(@RequestParam("inspecCode") String inspecCode,
			@RequestParam("startDate") String startDate, @RequestParam("endExDate") String endExDate,
			@RequestParam("inspecType") String inspecType, @RequestParam("progressStatus") String progressStatus,
			@RequestParam("inspecQty") int inspecQty, @RequestParam("qDirector") String qDirector)
			throws ParseException {

		// startDate를 Timestamp로 변환하며 현재 시간을 추가
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date parsedStartDate = dateFormat.parse(startDate);

		// 현재 시간 설정
		Calendar startCalendar = Calendar.getInstance();
		startCalendar.setTime(parsedStartDate);
		startCalendar.set(Calendar.HOUR_OF_DAY, Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
		startCalendar.set(Calendar.MINUTE, Calendar.getInstance().get(Calendar.MINUTE));
		startCalendar.set(Calendar.SECOND, Calendar.getInstance().get(Calendar.SECOND));

		Timestamp qcStartDate = new Timestamp(startCalendar.getTimeInMillis());

		SimpleDateFormat endexdateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date parsedEndExDate = endexdateFormat.parse(endExDate);

		// 현재 시간(업데이트 시간)을 Timestamp로 설정
		Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());

		// QualityControlDTO에 폼 데이터를 담음
		QualityControlDTO qualityControlDTO = new QualityControlDTO();
		qualityControlDTO.setInspecCode(inspecCode);
		qualityControlDTO.setStartDate(qcStartDate);
		qualityControlDTO.setEndExDate(parsedEndExDate);
		qualityControlDTO.setUpdateDate(currentTimestamp);
		qualityControlDTO.setInspecType(inspecType);
		qualityControlDTO.setProgressStatus(progressStatus);
		qualityControlDTO.setInspecQty(inspecQty);
		qualityControlDTO.setqDirector(qDirector);

		// progressStatus가 '검사 완료'인 경우, endDate에 현재 시간 설정, 그렇지 않으면 null 설정
		if ("검사 완료".equals(progressStatus)) {
			qualityControlDTO.setEndDate(currentTimestamp); // 검사 완료 시 현재 시간 설정
		} else {
			qualityControlDTO.setEndDate(null); // 검사 완료가 아닐 경우 null 설정
		}

		// 서비스 메서드를 호출하여 품질 관리 데이터 업데이트
		qualityControlService.updateQualityControl(qualityControlDTO);

		// 업데이트 후 다시 품질 관리 목록 페이지로 리다이렉트
		return "redirect:/productionQuality.do";
	}

	@RequestMapping(value = "/deleteProductionQuality.do", method = RequestMethod.POST)
	public String deleteProductionQuality(@RequestParam("orderNumber") String inspecCode) {

		// 품질관리 데이터 삭제
		qualityControlService.deleteQualityControlByInspecCode(inspecCode);

		// 삭제 후 다시 품질관리 목록 페이지로 리다이렉트
		return "redirect:/productionQuality.do";
	}
}
