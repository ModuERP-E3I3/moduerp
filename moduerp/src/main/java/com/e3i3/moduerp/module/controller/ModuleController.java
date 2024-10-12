package com.e3i3.moduerp.module.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
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

import com.e3i3.moduerp.cart.model.dto.CartDTO;
import com.e3i3.moduerp.cart.model.service.CartService;
import com.e3i3.moduerp.employee.model.service.EmployeeProductionService;
import com.e3i3.moduerp.module.model.dto.ModuleDTO;
import com.e3i3.moduerp.module.model.service.ModuleService;

@Controller
@RequestMapping("/")
public class ModuleController {

	@Autowired
	private ModuleService moduleService;

	@Autowired
	private EmployeeProductionService employeeProductionService;

	@Autowired
	private CartService cartService;

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

	// 장바구니 가기
	@RequestMapping(value = "/insertCart.do", method = RequestMethod.POST)
	public String forwardCart(@RequestParam("moduleIds") List<String> moduleIds,
			@RequestParam("buttonClicked") String buttonClicked, Model model, HttpSession session) {
		String bizNumber = (String) session.getAttribute("biz_number");
		String uuid = (String) session.getAttribute("uuid");

		// CART 테이블에 데이터가 존재하는지 확인
		boolean isCartExist = cartService.isCartExistByBizNumber(bizNumber);

		// ModuleService를 통해 MODULE_GRADE를 가져옴
		List<String> moduleGrades = moduleService.getModuleGradesByIds(moduleIds);

		// 현재 로그인한 사용자의 EMP_NAME 가져오기
		String loginUserName = employeeProductionService.getEmployeeNameByUuid(uuid);

		// MODULE_GRADE 리스트를 ','로 구분된 문자열로 변환
		String moduleGradesString = String.join(",", moduleGrades);
		System.out.println("-------------------------------------");
		System.out.println(moduleGradesString);
		System.out.println("-------------------------------------");

		if (isCartExist == false) {
			// CartID 생성
			String cartId = uuid + "CT" + System.currentTimeMillis();

			CartDTO cartDTO = new CartDTO();
			cartDTO.setCartId(cartId);
			cartDTO.setBizNumber(bizNumber);
			cartDTO.setCartList(moduleGradesString); // 변환된 문자열을 cartList에 설정
			cartDTO.setUuid(uuid);
			cartDTO.setName(loginUserName);

			cartService.insertCart(cartDTO);
		} else if (isCartExist) {
			// bizNumber로 CART_LIST 조회
			String existingCartList = cartService.getCartListByBizNumber(bizNumber);

			// 기존 목록을 ','로 분리하여 리스트로 변환 (null 처리)
			List<String> existingModules = new ArrayList<>();
			if (existingCartList != null && !existingCartList.trim().isEmpty()) {
				existingModules = new ArrayList<>(Arrays.asList(existingCartList.split(",")));
			}

			// 새 목록을 ','로 분리하여 리스트로 변환
			List<String> newModules = Arrays.asList(moduleGradesString.split(","));

			// 기존 목록에 없는 모듈만 필터링하여 추가
			for (String module : newModules) {
				if (!existingModules.contains(module)) {
					existingModules.add(module); // 중복되지 않는 항목만 추가
				}
			}

			// 최종적으로 업데이트할 목록을 ','로 다시 결합
			String updatedCartList = String.join(",", existingModules);

			// CartDTO 객체 생성 및 설정
			CartDTO cartDTO = new CartDTO();
			cartDTO.setBizNumber(bizNumber);
			cartDTO.setUuid(uuid);
			cartDTO.setCartList(updatedCartList); // 중복 제거 후 업데이트할 목록 설정

			// CART 업데이트 실행
			cartService.updateCart(cartDTO);
		}

		if ("go-cart".equals(buttonClicked)) {
			return "redirect:/forwardCart.do";
		} else if ("continue-shopping".equals(buttonClicked)) {
			return "redirect:/buyModule.do";
		}
		return "redirect:/forwardCart.do";
	}

}
