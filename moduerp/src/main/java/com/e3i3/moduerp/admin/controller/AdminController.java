package com.e3i3.moduerp.admin.controller;

import java.time.LocalDateTime;
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

import com.e3i3.moduerp.admin.model.dto.AdminDTO;
import com.e3i3.moduerp.admin.service.AdminService;

@Controller
@RequestMapping("/")
public class AdminController {

	@Autowired
	private AdminService adminService;

	// admin.jsp ��û ó��
	@RequestMapping("/admin.do")
	public String showAdmin(@RequestParam(value = "page", defaultValue = "1") int page, Model model) {
		List<AdminDTO> adminList = adminService.getAllAdmins();

		// 페이징
		int adminPerPage = 10;

		int totalAdmin = adminList.size();

		int totalPages = (int) Math.ceil((double) totalAdmin / adminPerPage);

		int startIndex = (page - 1) * adminPerPage;
		int endIndex = Math.min(startIndex + adminPerPage, totalPages);

		List<AdminDTO> paginatedList = adminList.subList(startIndex, endIndex);

		model.addAttribute("adminList", paginatedList);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("currentPage", page);
		return "admin/admin";
	}

	@RequestMapping(value = "/adminFilter.do", method = RequestMethod.GET)
	public String adminFilter(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "filterOption", required = false) String option,
			@RequestParam(value = "filterText", required = false) String filterText,
			@RequestParam(value = "startDate", required = false) String startDate,
			@RequestParam(value = "endDate", required = false) String endDate, Model model, HttpSession session) {
		List<AdminDTO> adminList = adminService.getAllAdmins();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		// startDate의 시분초 기본값 설정 (00:00:00)
		if (startDate != null && !startDate.isEmpty()) {
			LocalDateTime startDateTime = LocalDateTime.parse(startDate + " 00:00:00", formatter);
			startDate = startDateTime.format(formatter); // 포맷된 문자열로 변환
		}

		// endDate의 시분초 기본값 설정 (23:59:59)
		if (endDate != null && !endDate.isEmpty()) {
			LocalDateTime endDateTime = LocalDateTime.parse(endDate + " 23:59:59", formatter);
			endDate = endDateTime.format(formatter); // 포맷된 문자열로 변환
		}
		// 필터링 로직
		if (option != null && filterText != null) {
			if (startDate != null && !startDate.isEmpty() && endDate != null && !endDate.isEmpty()) {
				System.out.println("날짜있는거 실행");
				adminList = adminService.getAdminByFilterDate(option, filterText, startDate, endDate);
			} else if (startDate == null || startDate.isEmpty()) {
				System.out.println("날짜없는거 실행");
				adminList = adminService.getAdminByFilter(option, filterText);
			} else {
				System.out.println("실행 못함");
				adminList = adminService.getAllAdmins();
			}
		} else if ((option == null || filterText == null || filterText.isEmpty()) && startDate != null
				&& !startDate.isEmpty() && endDate != null && !endDate.isEmpty()) {
			System.out.println("필터 텍스트 없이 날짜만 있는 경우 실행");
			adminList = adminService.getAdminByFilterOnlyDate(startDate, endDate);
		} else if ((option == null || filterText == null || filterText.isEmpty()) && startDate != null
				&& !startDate.isEmpty()) {
			System.out.println("startDate만 있는 경우 실행");
			adminList = adminService.getAdminByFilterStartDate(startDate);
		} else if ((option == null || filterText == null || filterText.isEmpty()) && endDate != null
				&& !endDate.isEmpty()) {
			System.out.println("endDate만 있는 경우 실행");
			adminList = adminService.getAdminByFilterEndDate(endDate);
		} else {
			System.out.println("기본 전체 조회 실행");
			adminList = adminService.getAllAdmins();
		}

		int adminPerPage = 10;
		int totalAdmin = adminList.size();
		int totalPages = (int) Math.ceil((double) totalAdmin / adminPerPage);
		int startIndex = (page - 1) * adminPerPage;
		int endIndex = Math.min(startIndex + adminPerPage, totalPages);

		List<AdminDTO> paginatedList = adminList.subList(startIndex, endIndex);

		model.addAttribute("adminList", paginatedList);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("currentPage", page);
		model.addAttribute("option", option);
		model.addAttribute("filterText", filterText);
		model.addAttribute("startDate", startDate);
		model.addAttribute("endDate", endDate);

		return "admin/adminFilter";
	}

	@GetMapping("/company.do")
	public String adminFilter() {
	
		return "common/companyIntroduction";
	}

}
