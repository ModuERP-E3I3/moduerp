package com.e3i3.moduerp.department.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.e3i3.moduerp.department.model.dto.Department;
import com.e3i3.moduerp.department.model.service.DepartmentService;

@Controller
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    // 1. 특정 사업자번호로 부서 조회
    @RequestMapping(value = "departmentListByBizNumber.do", method = RequestMethod.GET)
    public String getDepartmentsByBizNumber(@RequestParam("bizNumber") String bizNumber, Model model) {
        List<Department> departments = departmentService.selectDepartmentsByBizNumber(bizNumber);
        model.addAttribute("departments", departments);
        return "department/departmentList";
    }

    // 2. 전체 부서 조회
    @RequestMapping(value = "departmentAll.do", method = RequestMethod.GET)
    public String getAllDepartments(Model model) {
        List<Department> departments = departmentService.selectAllDepartments();
        model.addAttribute("departments", departments);
        return "department/departmentList";
    }

    // 3. 부서 등록 페이지 이동
    @RequestMapping(value = "departmentRegisterPage.do", method = RequestMethod.GET)
    public String showDepartmentForm() {
        return "department/registerDepartment";
    }

    // 4. 부서 등록 요청 처리
    @RequestMapping(value = "departmentRegister.do", method = RequestMethod.POST)
    public String registerDepartment(Department department, Model model) {
        departmentService.insertDepartment(department);
        model.addAttribute("message", "부서가 성공적으로 등록되었습니다.");
        return "redirect:departmentAll.do";
    }

    // 5. 특정 부서 삭제
    @RequestMapping(value = "departmentDelete.do/{departmentId}", method = RequestMethod.POST)
    @ResponseBody
    public String deleteDepartment(@PathVariable("departmentId") String departmentId) {
        int result = departmentService.deleteDepartmentById(departmentId);
        if (result > 0) {
            return "부서가 성공적으로 삭제되었습니다.";
        } else {
            return "삭제할 부서가 존재하지 않습니다.";
        }
    }

    // 6. 특정 부서 정보 업데이트
    @RequestMapping(value = "departmentUpdate.do", method = RequestMethod.POST)
    public String updateDepartment(Department department, Model model) {
        int result = departmentService.updateDepartment(department);
        if (result > 0) {
            model.addAttribute("message", "부서 정보가 성공적으로 수정되었습니다.");
        } else {
            model.addAttribute("message", "부서 정보 수정에 실패했습니다.");
        }
        return "redirect:departmentAll.do";
    }
}