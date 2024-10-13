package com.e3i3.moduerp.account.controller;

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
import com.e3i3.moduerp.account.model.dto.AccountDTO;
import com.e3i3.moduerp.employee.model.dto.Employee;

@Controller
@RequestMapping("/")
public class AccountController {

	@Autowired
	private com.e3i3.moduerp.account.service.AccountService accountService;

	@RequestMapping(value = "/account.do", method = RequestMethod.GET)
	public String forwardAccount(@RequestParam(value = "page", defaultValue = "1") int page, Model model,
			HttpSession session) {
		String bizNumber = (String) session.getAttribute("biz_number");

		// Fetch all accounts
		List<AccountDTO> accountList = accountService.getAllAccounts();

		// Pagination logic
		int accountsPerPage = 10;
		int totalAccounts = accountList.size();
		int totalPages = (int) Math.ceil((double) totalAccounts / accountsPerPage);
		int startIndex = (page - 1) * accountsPerPage;
		int endIndex = Math.min(startIndex + accountsPerPage, totalAccounts);
		List<AccountDTO> paginatedList = accountList.subList(startIndex, endIndex);

		model.addAttribute("accountList", paginatedList);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("currentPage", page);

		return "account/account";
	}

	@RequestMapping(value = "/accountCreate.do", method = RequestMethod.GET)
	public String showCreateAccountForm(Model model, HttpSession session) {
		String bizNumber = (String) session.getAttribute("biz_number");

		// 사원명과 부서명 조회
		List<String> empNames = accountService.getEmpNamesByBizNumber(bizNumber);
		List<String> departmentIds = accountService.getDepartmentIdsByBizNumber(bizNumber);
		List<Employee> empNameDepart = accountService.getEmpNameDepart(bizNumber);

		// 계정 정보 조회
		List<AccountDTO> accounts = accountService.getAccountsByBizNumber(bizNumber);

		model.addAttribute("empNames", empNames);
		model.addAttribute("departmentIds", departmentIds);
		model.addAttribute("accounts", accounts);
		model.addAttribute("empNameDepart", empNameDepart);

		return "account/accountCreate";
	}

	@RequestMapping(value = "/accountFilter.do", method = RequestMethod.GET)
	public String forwardAccountFilter(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "filterOption", required = false) String option,
			@RequestParam(value = "filterText", required = false) String filterText, Model model, HttpSession session) {

		String bizNumber = (String) session.getAttribute("biz_number");
		List<AccountDTO> accountList;

		// 필터링 로직 추가 (날짜 필터 제거)
		if (option != null && filterText != null) {
			System.out.println("텍스트 필터링 실행");
			accountList = accountService.getAccountsByFilter(bizNumber, option, filterText);
		} else {
			System.out.println("필터링 실패 또는 기본 조회");
			accountList = accountService.getAccountsByBizNumber(bizNumber);
		}

		// 페이지네이션 처리
		int itemsPerPage = 10;
		int totalItems = accountList.size();
		int totalPages = (int) Math.ceil((double) totalItems / itemsPerPage);
		int startIndex = (page - 1) * itemsPerPage;
		int endIndex = Math.min(startIndex + itemsPerPage, totalItems);

		List<AccountDTO> paginatedList = accountList.subList(startIndex, endIndex);

		// 모델에 추가
		model.addAttribute("accountList", paginatedList);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("currentPage", page);
		model.addAttribute("option", option);
		model.addAttribute("filterText", filterText);

		return "account/accountFilter"; // JSP 파일 경로 반환
	}

	@PostMapping("/accountCreate.do")
	public String accountCreate(@RequestParam("accountName") String accountName,
			@RequestParam("businessType") String businessType, @RequestParam("bossName") String bossName,
			@RequestParam("businessNumber") String businessNumber,
			@RequestParam("accountAddress") String accountAddress, @RequestParam("accountPhone") String accountPhone,
			@RequestParam("email") String email, Model model, HttpSession session) {

		AccountDTO accountDto = new AccountDTO();
		accountDto.setAccountName(accountName);
		accountDto.setBusinessType(businessType);
		accountDto.setBossName(bossName);
		accountDto.setBusinessNumber(businessNumber);
		accountDto.setAccountAddress(accountAddress);
		accountDto.setAccountPhone(accountPhone);
		accountDto.setEmail(email);

		accountService.accountCreate(accountDto);

		return "redirect:/account.do";
	}

	
	
	@GetMapping("getAccountDetails.do")
	public String getAccountDetail(@RequestParam("accountNo") String accountNo, Model model) {
		AccountDTO accountDetail = accountService.getAccountListDetail(accountNo);
		model.addAttribute("accountDetail", accountDetail);
		return "account/accountDetail";
	}

	@GetMapping("accountDetailUpdate.do")
	public String accountDetailUpdate(@RequestParam("accountNo") String accountNo, Model model, HttpSession session) {
		AccountDTO accountDetail = accountService.getAccountListDetail(accountNo);
		String bizNumber = (String) session.getAttribute("biz_number");

		List<Employee> empNameDepart = accountService.getEmpNameDepart(bizNumber);
		List<AccountDTO> accounts = accountService.getAccountsByBizNumber(bizNumber);

		model.addAttribute("accounts", accounts);
		model.addAttribute("empNameDepart", empNameDepart);
		model.addAttribute("accountDetail", accountDetail);

		return "account/accountDetailUpdate";
	}

	@PostMapping("/updateAccount.do")
	public String updateAccount(@RequestParam("accountNo") String accountNo,
			@RequestParam("accountName") String accountName, @RequestParam("businessType") String businessType,
			@RequestParam("bossName") String bossName, @RequestParam("businessNumber") String businessNumber,
			@RequestParam("accountAddress") String accountAddress, @RequestParam("accountPhone") String accountPhone,
			@RequestParam("email") String email) {

		AccountDTO accountDto = new AccountDTO();
		accountDto.setAccountNo(accountNo);
		accountDto.setAccountName(accountName);
		accountDto.setBusinessType(businessType);
		accountDto.setBossName(bossName);
		accountDto.setBusinessNumber(businessNumber);
		accountDto.setAccountAddress(accountAddress);
		accountDto.setAccountPhone(accountPhone);
		accountDto.setEmail(email);

		accountService.updateAccount(accountDto);

		return "redirect:/account.do";
	}

	@PostMapping("/deleteAccount.do")
	public String deleteAccount(@RequestParam("accountNo") String accountNo, HttpSession session) {
		accountService.deleteAccountByAccountNo(accountNo);
		return "redirect:/account.do";
	}
}
