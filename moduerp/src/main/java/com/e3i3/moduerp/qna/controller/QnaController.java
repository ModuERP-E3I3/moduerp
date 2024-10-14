package com.e3i3.moduerp.qna.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
}
