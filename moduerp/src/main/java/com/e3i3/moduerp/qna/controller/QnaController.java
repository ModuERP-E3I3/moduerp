package com.e3i3.moduerp.qna.controller;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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

import com.e3i3.moduerp.employee.model.dto.Employee;
import com.e3i3.moduerp.qna.model.dto.QnaDto;

@Controller
@RequestMapping("/")
public class QnaController {

	@Autowired
	private com.e3i3.moduerp.qna.model.service.QnaService QnaService;
	
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
	
	@RequestMapping(value = "/qnaCreate.do", method = RequestMethod.GET)
	public String showCreateQnaForm(Model model, HttpSession session) {
		String uuid = (String) session.getAttribute("uuid");
		
		List<Employee> empNameDepart = QnaService.getEmpNameDepart(uuid);
		
		if (!empNameDepart.isEmpty()) {
	        String departmentId = empNameDepart.get(0).getDepartmentId(); // 첫 번째 사원 정보에서 가져오기
	        model.addAttribute("departmentId", departmentId);
	    }
		if (!empNameDepart.isEmpty()) {
	        String empName = empNameDepart.get(0).getDepartmentId(); // 첫 번째 사원 정보에서 가져오기
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
	public String qnaDetail(@RequestParam("qSeq") String qSeq, Model model) {
		QnaDto qnaDetail = QnaService.getQnaDetail(qSeq);
		
		model.addAttribute("qnaDetail", qnaDetail);
		
		return "qna/qnaDetail";
	}
	
	
}
