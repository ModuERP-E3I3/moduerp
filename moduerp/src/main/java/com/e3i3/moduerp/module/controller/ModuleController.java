package com.e3i3.moduerp.module.controller;

import java.sql.Timestamp;
import java.util.Calendar;
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
import com.e3i3.moduerp.module.model.dto.ModuleDTO;
import com.e3i3.moduerp.module.model.service.ModuleService;

@Controller
@RequestMapping("/")
public class ModuleController {

	@Autowired
	private ModuleService moduleService;

	// 모듈 구매 페이지 이동
	@RequestMapping(value = "/buyModule.do", method = RequestMethod.GET)
	public String buyModule(HttpSession session, Model model) {
		// 사용중인 모듈 List
		// 그룹웨어, 생산, 구매, 영업, 차량, 회계
		List<ModuleDTO> moduleListGroup = moduleService.getModuleListGroup();
		List<ModuleDTO> moduleListProduction = moduleService.getModuleListProduction();
		List<ModuleDTO> moduleListBuy = moduleService.getModuleListBuy();
		List<ModuleDTO> moduleListSales = moduleService.getModuleListSales();
		List<ModuleDTO> moduleListCar = moduleService.getModuleListCar();
		List<ModuleDTO> moduleListAccount = moduleService.getModuleListAccount();

		String bizNumber = (String) session.getAttribute("biz_number");

		model.addAttribute("bizNumber", bizNumber);

		model.addAttribute("moduleListGroup", moduleListGroup);
		model.addAttribute("moduleListProduction", moduleListProduction);
		model.addAttribute("moduleListBuy", moduleListBuy);
		model.addAttribute("moduleListSales", moduleListSales);
		model.addAttribute("moduleListCar", moduleListCar);
		model.addAttribute("moduleListAccount", moduleListAccount);

		return "module/buyModule";
	}

	// 모듈 리스트 (관리자)
	@RequestMapping(value = "/moduleList.do", method = RequestMethod.GET)
	public String moduleList(Model model) {
		// 사용중인 모듈 List
		List<ModuleDTO> moduleList = moduleService.getActiveModules();

		// 비 사용중인 모듈 List
		List<ModuleDTO> moduleListN = moduleService.getModulesN();

		model.addAttribute("moduleList", moduleList);
		model.addAttribute("moduleListN", moduleListN);

		return "module/moduleList";
	}

	// 모듈 등록 페이지 이동(관리자)
	@RequestMapping(value = "/moduleCreate.do", method = RequestMethod.GET)
	public String moduleCreate() {

		return "module/moduleCreate";
	}

	// 모듈 등록 insert
	@PostMapping("/moduleInsert.do")
	public String moduleInsert(@RequestParam("moduleName") String moduleName,
			@RequestParam("moduleGrade") String moduleGrade, @RequestParam("modulePrice") int modulePrice,
			@RequestParam("moduleDesc") String moduleDesc, @RequestParam("moduleVer") String moduleVer,
			@RequestParam("moduleType") String moduleType, HttpSession session) {

		String uuid = (String) session.getAttribute("uuid");

		String moduleId = uuid + "M" + System.currentTimeMillis();

		// 현재 연월일 시분초를 타임스탬프로 설정
		Calendar calendar = Calendar.getInstance();
		Timestamp currentTimestamp = new Timestamp(calendar.getTimeInMillis());

		char moduleUsage = 'Y';

		// ModuleDTO 객체 생성
		ModuleDTO module = new ModuleDTO();
		module.setModuleId(moduleId);
		module.setModuleName(moduleName);
		module.setModuleGrade(moduleGrade);
		module.setModulePrice(modulePrice);
		module.setModuleDesc(moduleDesc);
		module.setModuleVer(moduleVer);
		module.setModuleUsage(moduleUsage);
		module.setCreateDate(currentTimestamp);
		module.setModuleType(moduleType);

		// service를 통해 DB에 저장
		moduleService.insertModule(module);

		// 등록 후 목록 페이지로 리다이렉트
		return "redirect:/moduleList.do";
	}

	@GetMapping("/getModuleDetails.do")
	public String getModuleDetails(@RequestParam("moduleId") String moduleId, Model model) {

		// Module 테이블에서 Detail 정보 조회
		ModuleDTO moduleDetails = moduleService.getModuleDetailByModuleId(moduleId);

		model.addAttribute("moduleDetails", moduleDetails);

		return "module/moduleListDetail";
	}

	@GetMapping("/moduleDetailUpdate.do")
	public String moduleDetailUpdate(@RequestParam("moduleId") String moduleId, Model model) {
		// Module 테이블에서 Detail 정보 조회
		ModuleDTO moduleDetails = moduleService.getModuleDetailByModuleId(moduleId);

		model.addAttribute("moduleDetails", moduleDetails);

		return "module/moduleListDetailUpdate";
	}

	@PostMapping("/moduleUpdate.do")
	public String moduleUpdate(@RequestParam("moduleId") String moduleId, @RequestParam("modulePrice") int modulePrice,
			@RequestParam("moduleDesc") String moduleDesc, @RequestParam("moduleVer") String moduleVer,
			@RequestParam("moduleUsage") char moduleUsage) {

		// 현재 연월일 시분초를 타임스탬프로 설정
		Calendar calendar = Calendar.getInstance();
		Timestamp currentTimestamp = new Timestamp(calendar.getTimeInMillis());

		// ModuleDTO 객체 생성
		ModuleDTO module = new ModuleDTO();
		module.setModuleId(moduleId);
		module.setModulePrice(modulePrice);
		module.setModuleDesc(moduleDesc);
		module.setModuleVer(moduleVer);
		module.setUpdateDate(currentTimestamp);
		module.setModuleUsage(moduleUsage);

		// service를 통해 DB에 저장
		moduleService.updateModule(module);

		// 수정 후 목록 페이지로 리다이렉트
		return "redirect:/moduleList.do";
	}

	@PostMapping("/deleteModule.do")
	public String deleteModule(@RequestParam("moduleId") String moduleId) {

		moduleService.deleteModule(moduleId);

		return "redirect:/moduleList.do";
	}

}
