package com.e3i3.moduerp.attendance.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.e3i3.moduerp.attendance.model.dto.Attendance;
import com.e3i3.moduerp.attendance.model.service.AttendanceService;
import com.e3i3.moduerp.employee.model.service.EmployeeService;

@Controller
public class AttendanceController {
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private AttendanceService attendanceService;

	@RequestMapping(value = "/attendance.do", method = RequestMethod.GET)
	public String forwardAttendance(HttpSession session, Model model) {
		String loginUUID = (String) session.getAttribute("uuid");
		if (loginUUID == null) {
			return "redirect:/";
		}
		String userName = (String) session.getAttribute("name");
		model.addAttribute("userName", userName);
		return "attendance/attendance";
	}

	/*
	목록 보기 (/attendance/list): 모든 출퇴근 기록을 조회하여 attendance/list.jsp로 전달합니다.
	상세 보기 (/attendance/view/{attendanceId}): 특정 출퇴근 기록을 조회하여 attendance/view.jsp로 전달합니다.
	추가 폼 보기 (/attendance/add): 새로운 출퇴근 기록을 추가하기 위한 폼을 attendance/add.jsp로 전달합니다.
	추가 처리 (/attendance/add POST): 폼에서 입력한 데이터를 받아 출퇴근 기록을 추가하고 목록 페이지로 리다이렉트합니다.
	수정 폼 보기 (/attendance/edit/{attendanceId}): 특정 출퇴근 기록을 수정하기 위한 폼을 attendance/edit.jsp로 전달합니다.
	수정 처리 (/attendance/edit POST): 수정된 데이터를 받아 출퇴근 기록을 업데이트하고 목록 페이지로 리다이렉트합니다.
	삭제 처리 (/attendance/delete/{attendanceId}): 특정 출퇴근 기록을 삭제하고 목록 페이지로 리다이렉트합니다.
	 */
	// 모든 출퇴근 기록 목록 보기
	@GetMapping("/list")
	public String listAttendances(Model model) {
		List<Attendance> attendances = attendanceService.getAllAttendances();
		model.addAttribute("attendances", attendances);
		return "attendance/list"; // JSP 파일 경로
	}

	// 특정 출퇴근 기록 보기
	@GetMapping("/view/{attendanceId}")
	public String viewAttendance(@PathVariable("attendanceId") String attendanceId, Model model) {
		Attendance attendance = attendanceService.getAttendanceById(attendanceId);
		model.addAttribute("attendance", attendance);
		return "attendance/view"; // JSP 파일 경로
	}

	// 출퇴근 기록 추가 폼 보기
	@GetMapping("/add")
	public String showAddForm(Model model) {
		model.addAttribute("attendance", new Attendance());
		return "attendance/add"; // JSP 파일 경로
	}

	// 출퇴근 기록 추가 처리
	@PostMapping("/add")
	public String addAttendance(@ModelAttribute("attendance") Attendance attendance) {
		attendanceService.addAttendance(attendance);
		return "redirect:/attendance/list";
	}

	// 출퇴근 기록 수정 폼 보기
	@GetMapping("/edit/{attendanceId}")
	public String showEditForm(@PathVariable("attendanceId") String attendanceId, Model model) {
		Attendance attendance = attendanceService.getAttendanceById(attendanceId);
		model.addAttribute("attendance", attendance);
		return "attendance/edit"; // JSP 파일 경로
	}

	// 출퇴근 기록 수정 처리
	@PostMapping("/edit")
	public String updateAttendance(@ModelAttribute("attendance") Attendance attendance) {
		attendanceService.updateAttendance(attendance);
		return "redirect:/attendance/list";
	}

	// 출퇴근 기록 삭제 처리
	@GetMapping("/delete/{attendanceId}")
	public String deleteAttendance(@PathVariable("attendanceId") String attendanceId) {
		attendanceService.deleteAttendance(attendanceId);
		return "redirect:/attendance/list";
	}

//<<<<<<< HEAD
	/*
	 * // attendance.jsp ��û ó��
	 * 
	 * @RequestMapping(value = "/attendance.do", method = RequestMethod.GET) public
	 * String forwardAttendance() { return "attendance/attendance"; // JSP ���� ���
	 * }
	 */
//=======
//>>>>>>> 7276e8c1dcae2edc6ee7ce84c3d08a306ee6c588
}