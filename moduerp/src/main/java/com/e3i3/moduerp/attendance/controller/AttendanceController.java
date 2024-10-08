package com.e3i3.moduerp.attendance.controller;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.e3i3.moduerp.attendance.model.dto.Attendance;
import com.e3i3.moduerp.attendance.model.service.AttendanceService;

@Controller
public class AttendanceController {
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

		// 오늘 날짜의 출퇴근 기록 가져오기
		Date today = truncateTime(new Date());
		Attendance todayAttendance = attendanceService.selectAttendanceByUUIDAndDate(loginUUID, today);
		/*오늘의 출퇴근 기록을 가져와 attendace라는 이름으로 모델에 추가했음*/
		model.addAttribute("attendance", todayAttendance);

		return "attendance/attendance";
	}

	/*
	 * 목록 보기 (/attendance/list): 모든 출퇴근 기록을 조회하여 attendance/list.jsp로 전달합니다. 
	 * 상세 보기(/attendance/view/{attendanceId}): 특정 출퇴근 기록을 조회하여 attendance/view.jsp로 전달합니다. 
	 * 추가 폼 보기 (/attendance/add): 새로운 출퇴근 기록을 추가하기 위한 폼을 attendance/add.jsp로 전달합니다. 
	 * 추가 처리 (/attendance/add POST): 폼에서 입력한 데이터를 받아 출퇴근 기록을 추가하고 목록 페이지로 리다이렉트합니다. 
	 * 수정 폼 보기 (/attendance/edit/{attendanceId}): 특정 출퇴근 기록을 수정하기 위한 폼을 attendance/edit.jsp로 전달합니다. 
	 * 수정 처리 (/attendance/edit POST): 수정된 데이터를 받아 출퇴근 기록을 업데이트하고 목록 페이지로 리다이렉트합니다. 
	 * 삭제 처리 (/attendance/delete/{attendanceId}): 특정 출퇴근 기록을 삭제하고 목록 페이지로 리다이렉트합니다.
	 */
	// 모든 출퇴근 기록 목록 보기
	@GetMapping("/list")
	public String listAttendances(Model model) {
		List<Attendance> attendances = attendanceService.selectAllAttendances();
		model.addAttribute("attendances", attendances);
		return "attendance/list"; // JSP 파일 경로
	}

	// 특정 출퇴근 기록 보기
	@GetMapping("/view/{attendanceId}")
	public String viewAttendance(@PathVariable("attendanceId") String attendanceId, Model model) {
		Attendance attendance = attendanceService.selectAttendanceById(attendanceId);
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
		Attendance attendance = attendanceService.selectAttendanceById(attendanceId);
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

	// ======= 출근 처리 메소드 =======

	/* RedirectAttributes:
	 * attendance.do로 리다이렉트 시 플래시 속성(flash attributes)를 통해 성공 및 오류 메시지를 전달할 수 있음
	 * 플래시 속성은 리다이렉트된 요청에서 한 번만 사용할 수 있는 속성으로, 메시지를 사용자에게 전달하는데 유용함*/
	/**
	 * 출근 버튼 클릭 시 호출되는 메소드 URL: /attendance/clockIn.do HTTP Method: POST
	 */
	@PostMapping("/attendance/clockIn.do")
	public String clockIn(HttpSession session, RedirectAttributes redirectAttributes) {
		String loginUUID = (String) session.getAttribute("uuid");
		if (loginUUID == null) {
			// 로그인되지 않은 사용자 처리
			redirectAttributes.addFlashAttribute("error", "로그인이 필요합니다.");
			return "redirect:/"; // 로그인 페이지로 리다이렉트
		}

		// 현재 날짜 가져오기 (시간은 제외)
		Date today = truncateTime(new Date());

		// 이미 출근 기록이 있는지 확인
		Attendance existingAttendance = attendanceService.selectAttendanceByUUIDAndDate(loginUUID, today);
		if (existingAttendance != null && existingAttendance.getClockInTime() != null
				&& existingAttendance.getClockOutTime() == null) {
			// 이미 출근한 상태
			redirectAttributes.addFlashAttribute("error", "이미 출근하셨습니다.");
			return "redirect:/attendance.do"; // 출퇴근 페이지로 리다이렉트
		}
		
		if (existingAttendance != null && existingAttendance.getClockInTime() != null
				&& existingAttendance.getClockOutTime() != null) {
			// 이미 출근한 상태
			redirectAttributes.addFlashAttribute("error", "이미 퇴근하셨습니다.");
			return "redirect:/attendance.do"; // 출퇴근 페이지로 리다이렉트
		}

		// 비즈니스 번호 가져오기 (예시: EmployeeService를 통해 가져오기)
		String bizNumber = (String) session.getAttribute("biz_number");

		// 출근 기록 생성
		Attendance attendance = new Attendance()
				.setAttendanceId(generateUniqueId()) // 유니크한 ID 생성 (예: UUID)
				.setUuid(loginUUID)
				.setBizNumber(bizNumber)
				.setAttDate(today)
				.setClockInTime(new Timestamp(System.currentTimeMillis()));
		// totWorkHrs과 overtime은 출근 시점에는 설정하지 않음

		// 출근 기록 삽입
		attendanceService.addAttendance(attendance);

		redirectAttributes.addFlashAttribute("success", "출근이 정상적으로 등록되었습니다.");
		return "redirect:/attendance.do"; // 출퇴근 페이지로 리다이렉트
	}

	// ======= 퇴근 처리 메소드 =======

	/**
	 * 퇴근 버튼 클릭 시 호출되는 메소드 URL: /attendance/clockOut.do HTTP Method: POST
	 */
	@PostMapping("/attendance/clockOut.do")
	public String clockOut(HttpSession session, RedirectAttributes redirectAttributes) {
		String loginUUID = (String) session.getAttribute("uuid");
		if (loginUUID == null) {
			// 로그인되지 않은 사용자 처리
			redirectAttributes.addFlashAttribute("error", "로그인이 필요합니다.");
			return "redirect:/"; // 로그인 페이지로 리다이렉트
		}

		// 현재 날짜 가져오기 (시간은 제외)
		Date today = truncateTime(new Date());

		// 오늘 출근 기록 중 퇴근 시간이 없는 기록 찾기
		Attendance existingAttendance = attendanceService.selectAttendanceByUUIDAndDateAndClockOutNull(loginUUID,
				today);
		if (existingAttendance == null) {
			// 출근 기록이 없거나 이미 퇴근한 상태
			redirectAttributes.addFlashAttribute("error", "출근 기록이 없거나 이미 퇴근하셨습니다.");
			return "redirect:/attendance.do"; // 출퇴근 페이지로 리다이렉트
		}

		// 퇴근 시간 설정
		existingAttendance.setClockOutTime(new Timestamp(System.currentTimeMillis()));

		// 근무 시간 계산 (단순히 시차 계산)
		long diffMs = existingAttendance.getClockOutTime().getTime() - existingAttendance.getClockInTime().getTime();
		double diffHrs = (double) diffMs / (1000 * 60 * 60);
		existingAttendance.setTotWorkHrs(diffHrs);

		// 초과 근무 시간 계산 (예시: 8시간 초과 시)
		double overtime = 0.0;
		if (diffHrs > 8.0) {
			overtime = diffHrs - 8.0;
		}
		existingAttendance.setOvertime(overtime);

		// 퇴근 기록 업데이트
		attendanceService.updateAttendance(existingAttendance);

		redirectAttributes.addFlashAttribute("success", "퇴근이 정상적으로 등록되었습니다.");
		return "redirect:/attendance.do"; // 출퇴근 페이지로 리다이렉트
	}

	/**
	 * 현재 날짜의 시간 정보를 제거하는 메소드
	 * 
	 * @param date
	 * @return
	 */
	private Date truncateTime(Date date) {
		// java.util.Date는 시간 정보를 포함하므로, 시간을 제거하기 위해 Calendar를 사용
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * 유니크한 Attendance ID를 생성하는 메소드 (예: UUID 사용)
	 * 
	 * @return
	 */
	private String generateUniqueId() {
		return UUID.randomUUID().toString();
	}

}