package com.e3i3.moduerp.qna.controller;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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

import com.e3i3.moduerp.answer.model.dto.AnswerDto;
import com.e3i3.moduerp.employee.model.dto.Employee;
import com.e3i3.moduerp.qna.model.dto.QnaDto;

@Controller
@RequestMapping("/")
public class QnaController {

	@Autowired
	private com.e3i3.moduerp.qna.model.service.QnaService QnaService;
	
	@Autowired
	private com.e3i3.moduerp.answer.model.service.AnswerService AnswerService;
	
	@RequestMapping(value = "/qna.do", method = RequestMethod.GET)
	public String qnaView(@RequestParam(value = "page", defaultValue = "1") int page, Model model,
			HttpSession session) {
		String uuid = (String) session.getAttribute("uuid");
		
		List<QnaDto> qnaList = QnaService.getAllQna();
		
		int qnaPerPage = 10;
		int totalQna = qnaList.size();
		int totalPages = (int) Math.ceil((double) totalQna / qnaPerPage);
		int startIndex = (page - 1) * qnaPerPage;
		int endIndex = Math.min(startIndex + qnaPerPage, totalQna);
		
		List<QnaDto> paginatedList = qnaList.subList(startIndex, endIndex);
		List<Employee> empNameDepart = QnaService.getEmpNameDepart(uuid);
		
		model.addAttribute("empNameDepart", empNameDepart);
		model.addAttribute("qnaList", paginatedList);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("currentPage", page);
		
		return "qna/qna";
	}
	
	@RequestMapping(value = "/qnaFilter.do", method = RequestMethod.GET)
	public String forwardQnaFilter(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "filterOption", required = false) String option,
			@RequestParam(value = "filterText", required = false) String filterText,
			@RequestParam(value = "startDate", required = false) String startDate,
			@RequestParam(value = "endDate", required = false) String endDate, Model model, HttpSession session) {

		List<QnaDto> qnaList = QnaService.getAllQna();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		// startDate의 시분초 기본값 설정 (00:00:00)
	    if (startDate != null && !startDate.isEmpty()) {
	        LocalDateTime startDateTime = LocalDateTime.parse(startDate + " 00:00:00", formatter);
	        startDate = startDateTime.format(formatter);  // 포맷된 문자열로 변환
	    }

	    // endDate의 시분초 기본값 설정 (23:59:59)
	    if (endDate != null && !endDate.isEmpty()) {
	        LocalDateTime endDateTime = LocalDateTime.parse(endDate + " 23:59:59", formatter);
	        endDate = endDateTime.format(formatter);  // 포맷된 문자열로 변환
	    }
	    
	    if (option != null && filterText != null) {
			if (startDate != null && !startDate.isEmpty() && endDate != null && !endDate.isEmpty()) {
				System.out.println("날짜있는거 실행");
				qnaList = QnaService.getQnaByFilterDate(option, filterText, startDate, endDate);
			} else if (startDate == null || startDate.isEmpty()) {
				System.out.println("날짜없는거 실행");
				qnaList = QnaService.getQnaByFilter(option, filterText);
			} else {
				System.out.println("실행 못함");
				qnaList = QnaService.getAllQna();
			}
		} else if ((option == null || filterText == null || filterText.isEmpty()) 
		        && startDate != null && !startDate.isEmpty() && endDate != null && !endDate.isEmpty()) {
		    System.out.println("필터 텍스트 없이 날짜만 있는 경우 실행");
		    qnaList = QnaService.getQnaByFilterOnlyDate(startDate, endDate);
		} else if ((option == null || filterText == null || filterText.isEmpty()) 
		        && startDate != null && !startDate.isEmpty()) {
		    System.out.println("startDate만 있는 경우 실행");
		    qnaList = QnaService.getQnaByFilterStartDate(startDate);
		} else if ((option == null || filterText == null || filterText.isEmpty()) 
		        && endDate != null && !endDate.isEmpty()) {
		    System.out.println("endDate만 있는 경우 실행");
		    qnaList = QnaService.getQnaByFilterEndDate(endDate);
		} else {
		    System.out.println("기본 전체 조회 실행");
		    qnaList = QnaService.getAllQna();
		}
	    
	    int qnaPerPage = 10;
		int totalQna = qnaList.size();
		int totalPages = (int) Math.ceil((double) totalQna / qnaPerPage);
		int startIndex = (page - 1) * qnaPerPage;
		int endIndex = Math.min(startIndex + qnaPerPage, totalQna);
		
		List<QnaDto> paginatedList = qnaList.subList(startIndex, endIndex);
		
		model.addAttribute("qnaList", paginatedList);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("currentPage", page);
		model.addAttribute("option", option);
		model.addAttribute("filterText", filterText);
		model.addAttribute("startDate", startDate);
		model.addAttribute("endDate", endDate);
		
		return "qna/qnaFilter";
	}
	
	@RequestMapping(value = "/qnaCreate.do", method = RequestMethod.GET)
	public String showCreateQnaForm(Model model, HttpSession session) {
		String uuid = (String) session.getAttribute("uuid");
		
		List<Employee> empNameDepart = QnaService.getEmpNameDepart(uuid);
		
		if (!empNameDepart.isEmpty()) {
	        String departmentId = empNameDepart.get(0).getDepartmentId(); // 첫 번째 사원 정보에서 가져오기
	        model.addAttribute("departmentId", departmentId);
	    }
		if (!empNameDepart.isEmpty()) {
	        String empName = empNameDepart.get(0).getEmpName(); // 첫 번째 사원 정보에서 가져오기
	        model.addAttribute("empName", empName);
	    }
		
		System.out.println("uuid: " + uuid);
		System.out.println("로그인 한 유저 정보" + empNameDepart);
		
		model.addAttribute("empNameDepart", empNameDepart);
		
		
		
		return "qna/qnaCreate";
	}
	
	@PostMapping("/insertQna.do")
	public String inserQna(@RequestParam("empName") String empName,
			@RequestParam("departmentId") String departmentId,
			@RequestParam("qTitle") String qTitle,
			@RequestParam("qContents") String qContents,
			@RequestParam("qStatus") String qStatus,
			@RequestParam("isPublic") String isPublic,
			Model model, HttpSession session) {
		String uuid = (String) session.getAttribute("uuid");
		
		System.out.println("empName: " + empName);
		System.out.println("uuid: " + uuid);
		System.out.println("departmentId: " + departmentId);
		System.out.println("qTitle: " + qTitle);
		System.out.println("qContents: " + qContents);
		System.out.println("qStatus: " + qStatus);
		System.out.println("isPublic: " + isPublic);
		
		LocalDate currentDate = LocalDate.now();
		LocalTime currentTime = LocalTime.now();
		LocalDateTime combinedDateTime = LocalDateTime.of(currentDate, currentTime);
		Timestamp qDate = Timestamp.valueOf(combinedDateTime);
		
		
		if (departmentId == null || departmentId.trim().isEmpty()) {
	        // 잘못된 값 처리 (예: 기본값 설정 또는 에러 메시지 추가)
	        return "redirect:/errorPage"; // 에러 페이지로 리다이렉트
	    }
		
		
		QnaDto qnaDto = new QnaDto();
	    qnaDto.setUuid(uuid);
	    qnaDto.setEmpName(empName);
	    qnaDto.setDepartmentId(departmentId);
	    qnaDto.setqTitle(qTitle);
	    qnaDto.setqContents(qContents);
	    qnaDto.setqDate(qDate);
	    qnaDto.setqStatus(qStatus);
	    qnaDto.setIsPublic(isPublic);

	    // QnaService 호출
	    QnaService.insertQna(qnaDto);

	    return "redirect:/qna.do";
	}
	
	// qna 상세 페이지
	@GetMapping("qnaDetail.do")
	public String qnaDetail(@RequestParam("qSeq") String qSeq, Model model, HttpSession session) {
		String uuid = (String) session.getAttribute("uuid");
		System.out.println("uuid : " + uuid);
		QnaDto qnaDetail = QnaService.getQnaDetail(qSeq);
		AnswerDto answerDetail = AnswerService.getAnswerDetail(qSeq);
		
		model.addAttribute("uuid", uuid);
		model.addAttribute("qnaDetail", qnaDetail);
		model.addAttribute("answerDetail", answerDetail);
		
		return "qna/qnaDetail";
	}
	
	@GetMapping("questionUpdateForm.do")
	public String questionUpdateForm(@RequestParam("qSeq") String qSeq, Model model) {
		QnaDto qnaDetail = QnaService.getQnaDetail(qSeq);
		
		model.addAttribute("qnaDetail", qnaDetail);
		
		return "qna/qnaUpdate";
	}
	
	@PostMapping("/updateQuestion.do")
	public String updateQuestion(@RequestParam("qTitle") String qTitle,
			@RequestParam("qContents") String qContents,
			@RequestParam("qSeq") int qSeq) {
		LocalDate currentDate = LocalDate.now();
		LocalTime currentTime = LocalTime.now();
		LocalDateTime combinedDateTime = LocalDateTime.of(currentDate, currentTime);
		Timestamp qDate = Timestamp.valueOf(combinedDateTime);
		
		QnaDto qnaDto = new QnaDto();
		qnaDto.setqTitle(qTitle);
		qnaDto.setqContents(qContents);
		qnaDto.setqDate(qDate);
		qnaDto.setqSeq(qSeq);
		
		QnaService.updateQuestion(qnaDto);
		
		return "redirect:/qna.do";
	}
	
	@PostMapping("/deleteQna.do")
	public String deleteQna(@RequestParam("qSeq") int qSeq, 
			@RequestParam("aSeq") String aSeq,
			HttpSession session) {
		AnswerService.deleteAnswer(aSeq);
		QnaService.deleteQna(qSeq);
		
		return "redirect:/qna.do";
	}
	
	@RequestMapping(value = "/qnaAnswerCreate.do", method = RequestMethod.GET)
	public String showCreateAnswerForm(@RequestParam("qSeq") String qSeq, Model model, HttpSession session) {
		String uuid = (String) session.getAttribute("uuid");
		QnaDto qnaDetail = QnaService.getQnaDetail(qSeq);
		
		System.out.println("uuid: " + uuid);
		
		model.addAttribute("qnaDetail", qnaDetail);
		model.addAttribute("uuid", uuid);
		
		return "qna/qnaAnswerCreate";
	}
	
	@PostMapping("/insertAnswer.do")
	public String insertAnswer(
	        @RequestParam("aTitle") String aTitle,
	        @RequestParam("aContents") String aContents,
	        @RequestParam("qSeq") int qSeq,  // 현재 질문의 qSeq 값을 받아옴
	        @RequestParam("qStatus") String qStatus,
	        Model model, HttpSession session) {
	    
	    String uuid = (String) session.getAttribute("uuid");

	    System.out.println("aTitle: " + aTitle);
	    System.out.println("aContents: " + aContents);
	    System.out.println("qSeq: " + qSeq);
	    System.out.println("uuid: " + uuid);
	    
	    if (aContents == null || aContents.trim().isEmpty()) {
	        model.addAttribute("errorMessage", "답변 내용을 입력하세요.");
	        return "qna/qnaAnswerCreate"; // 오류 메시지를 포함한 답변 작성 페이지로 이동
	    }
	    
	    String aSeq = qSeq + "QNAAnswer" + System.currentTimeMillis();
	    
	    // 현재 날짜와 시간을 aDate로 설정
	    LocalDate currentDate = LocalDate.now();
		LocalTime currentTime = LocalTime.now();
		LocalDateTime combinedDateTime = LocalDateTime.of(currentDate, currentTime);
	    Timestamp aDate = Timestamp.valueOf(combinedDateTime);

	    // Answer DTO에 값 설정
	    AnswerDto answerDto = new AnswerDto();
	    answerDto.setaTitle(aTitle);
	    answerDto.setaSeq(aSeq);
	    answerDto.setaContents(aContents);
	    answerDto.setaDate(aDate);
	    answerDto.setqSeq(qSeq);  // 답변이 연결될 질문의 qSeq
	    answerDto.setUuid(uuid);  // 답변 작성자의 uuid
	    
	    
	 // 질문 상태 업데이트를 위한 DTO 설정
	    QnaDto qnaDto = new QnaDto();
	    qnaDto.setqSeq(qSeq);
	    qnaDto.setqStatus(qStatus);
	    
	    
	    AnswerService.insertAnswer(answerDto);
	    
	    QnaService.updateQStatus(qnaDto);
	    
	    // 답변 추가 후 질문 상세 페이지로 리다이렉트
	    return "redirect:/qnaDetail.do?qSeq=" + qSeq;
	}
	@GetMapping("answerUpdateForm.do")
	public String answerUpdateForm(@RequestParam("qSeq") String qSeq, Model model) {
		AnswerDto answerDetail = AnswerService.getAnswerDetail(qSeq);
		
		model.addAttribute("answerDetail", answerDetail);
		
		return "qna/qnaAnswerUpdate";
	}
	
	@PostMapping("/updateAnswer.do")
	public String updateAnswer(@RequestParam("aTitle") String aTitle,
			@RequestParam("aContents") String aContents,
			@RequestParam("aSeq") String aSeq,
			@RequestParam("qSeq") String qSeq) {
		LocalDate currentDate = LocalDate.now();
		LocalTime currentTime = LocalTime.now();
		LocalDateTime combinedDateTime = LocalDateTime.of(currentDate, currentTime);
		Timestamp aDate = Timestamp.valueOf(combinedDateTime);
		
		AnswerDto answerDto = new AnswerDto();
		answerDto.setaTitle(aTitle);
		answerDto.setaContents(aContents);
		answerDto.setaDate(aDate);
		answerDto.setaSeq(aSeq);
		
		AnswerService.updateAnswer(answerDto);

	    return "redirect:/qnaDetail.do?qSeq=" + qSeq;
	}
	@PostMapping("/deleteAnswer.do")
	public String deleteAnswer(@RequestParam("aSeq") String aSeq, 
			@RequestParam("qSeq") int qSeq,
			@RequestParam("qStatus") String qStatus,
			HttpSession session) {
		
		QnaDto qnaDto = new QnaDto();
	    qnaDto.setqSeq(qSeq);
	    qnaDto.setqStatus(qStatus);
	    
	    AnswerService.deleteAnswer(aSeq);
	    QnaService.updateQStatusN(qnaDto);
	    
		return "redirect:/qnaDetail.do?qSeq=" + qSeq;
	}
}
