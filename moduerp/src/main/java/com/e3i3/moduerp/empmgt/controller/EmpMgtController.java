package com.e3i3.moduerp.empmgt.controller;

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
import com.e3i3.moduerp.empmgt.model.dto.EmpMgtDTO;
import com.e3i3.moduerp.empmgt.service.EmpMgtService;


@Controller
@RequestMapping("/")
public class EmpMgtController {

    @Autowired
    private EmpMgtService empMgtService;

    @RequestMapping(value = "/empMgt.do", method = RequestMethod.GET)
    public String forwardEmpMgt(@RequestParam(value = "page", defaultValue = "1") int page, Model model, HttpSession session) {
        String bizNumber = (String) session.getAttribute("biz_number");

        // Fetch all employees
        List<EmpMgtDTO> employeeList = empMgtService.getAllEmployees();

        // Pagination logic
        int employeesPerPage = 10;
        int totalEmployees = employeeList.size();
        int totalPages = (int) Math.ceil((double) totalEmployees / employeesPerPage);
        int startIndex = (page - 1) * employeesPerPage;
        int endIndex = Math.min(startIndex + employeesPerPage, totalEmployees);
        List<EmpMgtDTO> paginatedList = employeeList.subList(startIndex, endIndex);

        model.addAttribute("employeeList", paginatedList);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", page);

        return "empMgt/empMgt";
    }

    @RequestMapping(value = "/empMgtFilter.do", method = RequestMethod.GET)
    public String forwardEmpMgtFilter(@RequestParam(value = "page", defaultValue = "1") int page,
                                      @RequestParam(value = "filterOption", required = false) String option,
                                      @RequestParam(value = "filterText", required = false) String filterText,
                                      @RequestParam(value = "startDate", required = false) String startDate,
                                      @RequestParam(value = "endDate", required = false) String endDate, 
                                      Model model, HttpSession session) {
        String bizNumber = (String) session.getAttribute("biz_number");
        List<EmpMgtDTO> employeeList;

        // 필터링 로직
        if (option != null && filterText != null) {
            if (startDate != null && !startDate.isEmpty() && endDate != null && !endDate.isEmpty()) {
                employeeList = empMgtService.getEmployeesByFilterDate(bizNumber, option, filterText, startDate, endDate);
            } else if (startDate != null && !startDate.isEmpty()) {
                employeeList = empMgtService.getEmployeesByFilterStartDate(bizNumber, startDate);
            } else if (endDate != null && !endDate.isEmpty()) {
                employeeList = empMgtService.getEmployeesByFilterEndDate(bizNumber, endDate);
            } else {
                employeeList = empMgtService.getEmployeesByFilter(bizNumber, option, filterText);
            }
        } else if (startDate != null && !startDate.isEmpty()) {
            employeeList = empMgtService.getEmployeesByFilterStartDate(bizNumber, startDate);
        } else if (endDate != null && !endDate.isEmpty()) {
            employeeList = empMgtService.getEmployeesByFilterEndDate(bizNumber, endDate);
        } else {
            employeeList = empMgtService.getEmployeesByBizNumber(bizNumber);
        }

        // 페이지네이션 처리
        int itemsPerPage = 10;
        int totalItems = employeeList.size();
        int totalPages = (int) Math.ceil((double) totalItems / itemsPerPage);
        int startIndex = (page - 1) * itemsPerPage;
        int endIndex = Math.min(startIndex + itemsPerPage, totalItems);
        List<EmpMgtDTO> paginatedList = employeeList.subList(startIndex, endIndex);

        model.addAttribute("employeeList", paginatedList);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", page);
        model.addAttribute("option", option);
        model.addAttribute("filterText", filterText);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);

        return "empMgt/empMgtFilter";
    }

    @RequestMapping(value = "/employeeCreate.do", method = RequestMethod.GET)
    public String showCreateEmployeeForm(Model model, HttpSession session) {
        String bizNumber = (String) session.getAttribute("biz_number");
        String uuid = (String) session.getAttribute("uuid");

        List<String> empNames = empMgtService.getEmpNamesByBizNumber(bizNumber);
        List<String> departmentIds = empMgtService.getDepartmentIdsByBizNumber(bizNumber);
        List<Employee> empNameDepart = empMgtService.getEmpNameDepart(bizNumber);
        List<EmpMgtDTO> employees = empMgtService.getEmployeesByBizNumber(bizNumber);
        
        model.addAttribute("empNames", empNames);
        model.addAttribute("departmentIds", departmentIds);
        model.addAttribute("employees", employees);
        model.addAttribute("empNameDepart", empNameDepart);

        return "empMgt/employeeCreate";
    }

    @PostMapping("/employeeCreate.do")
    public String employeeCreate(@RequestParam("empNo") String empNo, 
                                 @RequestParam("empName") String empName, 
                                 @RequestParam("departmentId") String departmentId, 
                                 @RequestParam("jobId") String jobId, 
                                 @RequestParam("email") String email, 
                                 @RequestParam("phone") String phone, 
                                 @RequestParam("address") String address, 
                                 Model model, HttpSession session) {

        String bizNumber = (String) session.getAttribute("biz_number");

        EmpMgtDTO empMgtDTO = new EmpMgtDTO();
        empMgtDTO.setEmpNo(empNo);
        empMgtDTO.setEmpName(empName);
        empMgtDTO.setDepartmentId(departmentId);
        empMgtDTO.setJobId(jobId);
        empMgtDTO.setEmail(email);
        empMgtDTO.setPhone(phone);
        empMgtDTO.setAddress(address);
        empMgtDTO.setBizNumber(bizNumber);

        empMgtService.createEmployee(empMgtDTO);

        return "redirect:/empMgt.do";
    }

    @GetMapping("getEmployeeDetails.do")
    public String getEmployeeDetail(@RequestParam("empNo") String empNo, Model model) {
        EmpMgtDTO employeeDetail = empMgtService.getEmployeeDetail(empNo);
        model.addAttribute("employeeDetail", employeeDetail);
        return "empmgt/employeeDetail";
    }

    @GetMapping("employeeDetailUpdate.do")
    public String employeeDetailUpdate(@RequestParam("empNo") String empNo, Model model, HttpSession session) {
        EmpMgtDTO employeeDetail = empMgtService.getEmployeeDetail(empNo);
        String bizNumber = (String) session.getAttribute("biz_number");

        List<Employee> empNameDepart = empMgtService.getEmpNameDepart(bizNumber);
        List<EmpMgtDTO> employees = empMgtService.getEmployeesByBizNumber(bizNumber);

        model.addAttribute("employees", employees);
        model.addAttribute("empNameDepart", empNameDepart);
        model.addAttribute("employeeDetail", employeeDetail);

        return "empmgt/employeeDetailUpdate";
    }

    @PostMapping("/updateEmployee.do")
    public String updateEmployee(@RequestParam("empNo") String empNo,
                                 @RequestParam("empName") String empName,
                                 @RequestParam("departmentId") String departmentId,
                                 @RequestParam("jobId") String jobId,
                                 @RequestParam("email") String email,
                                 @RequestParam("phone") String phone,
                                 @RequestParam("address") String address) {

        EmpMgtDTO empMgtDTO = new EmpMgtDTO();
        empMgtDTO.setEmpNo(empNo);
        empMgtDTO.setEmpName(empName);
        empMgtDTO.setDepartmentId(departmentId);
        empMgtDTO.setJobId(jobId);
        empMgtDTO.setEmail(email);
        empMgtDTO.setPhone(phone);
        empMgtDTO.setAddress(address);

        empMgtService.updateEmployee(empMgtDTO);

        return "redirect:/empMgt.do";
    }

    @PostMapping("/deleteEmployee.do")
    public String deleteEmployee(@RequestParam("empNo") String empNo, HttpSession session) {
        empMgtService.deleteEmployeeByEmpNo(empNo);
        return "redirect:/empMgt.do";
    }
}
