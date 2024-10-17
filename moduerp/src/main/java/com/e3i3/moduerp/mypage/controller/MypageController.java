package com.e3i3.moduerp.mypage.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.e3i3.moduerp.employee.model.dto.Employee;
import com.e3i3.moduerp.employee.model.service.EmployeeService;

@Controller
public class MypageController {
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;

	@GetMapping("/updatePassword.do")
	public String updatePassword(Model model) {
		return "empmypage/password";
	}

	@GetMapping("/updatePhone.do")
	public String updatePhone(HttpSession session, Model model) {
		String uuid = (String) session.getAttribute("uuid");
		Employee user = employeeService.selectEmployeeByUuid(uuid);
		
		model.addAttribute("phone", user.getUserPhone());
		return "empmypage/phone";
	}

	@GetMapping("/updateAddress.do")
	public String updateAddress(HttpSession session,Model model) {
		String uuid = (String) session.getAttribute("uuid");
		Employee user = employeeService.selectEmployeeByUuid(uuid);
		
		model.addAttribute("address", user.getAddress());
		return "empmypage/address";
	}

	@PostMapping("/complete/updatePassword.do")
	public String updatePassword(@RequestParam("currentPassword") String currentPassword,
			@RequestParam("newPassword") String newPassword, @RequestParam("confirmPassword") String confirmPassword,
			RedirectAttributes redirectAttributes, // 모델을 RedirectAttributes로 변경
			HttpSession session) {

		// 1. 현재 인증된 사용자 정보 가져오기
		String uuid = (String) session.getAttribute("uuid");
		Employee currentCeo = employeeService.selectEmployeeByUuid(uuid);

		if (currentCeo == null) {
			redirectAttributes.addFlashAttribute("errorMessage", "사용자를 찾을 수 없습니다.");
			return "redirect:/updatePassword.do";
		}

		// 3. 현재 비밀번호 확인
		if (!bcryptPasswordEncoder.matches(currentPassword, currentCeo.getPassword())) {
			redirectAttributes.addFlashAttribute("errorMessage", "현재 비밀번호가 일치하지 않습니다.");
			return "redirect:/updatePassword.do";
		}

		// 4. 새 비밀번호 유효성 검사
		if (newPassword.length() < 8) {
			redirectAttributes.addFlashAttribute("errorMessage", "새 비밀번호는 최소 8자리 이상이어야 합니다.");
			return "redirect:/updatePassword.do";
		}

		// 5. 새 비밀번호와 확인 비밀번호 일치 여부 확인
		if (!newPassword.equals(confirmPassword)) {
			redirectAttributes.addFlashAttribute("errorMessage", "비밀번호 확인이 일치하지 않습니다.");
			return "redirect:/updatePassword.do";
		}

		// 6. 새 비밀번호 암호화
		String encodedNewPassword = bcryptPasswordEncoder.encode(newPassword);

		// 7. 비밀번호 업데이트 (Service 또는 Repository를 통해)
		currentCeo.setPassword(encodedNewPassword);
		employeeService.updatePassword(uuid, encodedNewPassword);

		// 8. 성공 메시지 전달
		redirectAttributes.addFlashAttribute("successMessage", "비밀번호가 성공적으로 변경되었습니다.");
		return "redirect:/updatePassword.do";
	}

	@PostMapping("/complete/addressUpdate.do")
	public String completeAddressUpdate(HttpSession session, String address, RedirectAttributes  model) {
		// 현재 로그인한 사용자의 UUID 가져오기
		String uuid = (String) session.getAttribute("uuid");

		// 주소 업데이트 서비스 호출
		int result = employeeService.updateAddressByUuid(uuid, address);
		
		if (result>0) {
			// 업데이트 성공 시 성공 메시지 추가
			model.addFlashAttribute("successMessage", "주소가 성공적으로 수정되었습니다.");
		} else {
			// 업데이트 실패 시 오류 메시지 추가
			model.addFlashAttribute("errorMessage", "주소 수정에 실패했습니다. 다시 시도해주세요.");
		}

		// 업데이트된 직원 정보 조회
		Employee updatedEmployee = employeeService.selectEmployeeByUuid(uuid);
		//model.addFlashAttribute("employee", updatedEmployee);

		// 주소 관리 페이지로 포워딩
		return "redirect:/updateAddress.do"; // 실제 JSP 파일 경로에 맞게 수정 필요
	}
	
	@PostMapping("/complete/phoneUpdate.do")
	public String completePhoneUpdate(HttpSession session, String userPhone, RedirectAttributes  model) {
		// 현재 로그인한 사용자의 UUID 가져오기
		String uuid = (String) session.getAttribute("uuid");
		
		// 번호 업데이트 서비스 호출
		int result = employeeService.updateUserPhoneByUuid(uuid, userPhone);
		
		if (result>0) {
			// 업데이트 성공 시 성공 메시지 추가
			model.addFlashAttribute("successMessage", "번호가 성공적으로 수정되었습니다.");
		} else {
			// 업데이트 실패 시 오류 메시지 추가
			model.addFlashAttribute("errorMessage", "번호 수정에 실패했습니다. 다시 시도해주세요.");
		}
		
		// 업데이트된 직원 정보 조회
		//Employee updatedEmployee = employeeService.selectEmployeeByUuid(uuid);
		//model.addFlashAttribute("employee", updatedEmployee);
		
		// 주소 관리 페이지로 포워딩
		return "redirect:/updatePhone.do"; // 실제 JSP 파일 경로에 맞게 수정 필요
	}

}
