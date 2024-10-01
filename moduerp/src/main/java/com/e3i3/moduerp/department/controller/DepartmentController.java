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

    // 1. Ư�� ����ڹ�ȣ�� �μ� ��ȸ
    @RequestMapping(value = "departmentListByBizNumber.do", method = RequestMethod.GET)
    public String getDepartmentsByBizNumber(@RequestParam("bizNumber") String bizNumber, Model model) {
        List<Department> departments = departmentService.selectDepartmentsByBizNumber(bizNumber);
        model.addAttribute("departments", departments);
        return "department/departmentList";
    }

    // 2. ��ü �μ� ��ȸ
    @RequestMapping(value = "departmentAll.do", method = RequestMethod.GET)
    public String getAllDepartments(Model model) {
        List<Department> departments = departmentService.selectAllDepartments();
        model.addAttribute("departments", departments);
        return "department/departmentList";
    }

    // 3. �μ� ��� ������ �̵�
    @RequestMapping(value = "departmentRegisterPage.do", method = RequestMethod.GET)
    public String showDepartmentForm() {
        return "department/registerDepartment";
    }

    // 4. �μ� ��� ��û ó��
    @RequestMapping(value = "departmentRegister.do", method = RequestMethod.POST)
    public String registerDepartment(Department department, Model model) {
        departmentService.insertDepartment(department);
        model.addAttribute("message", "�μ��� ���������� ��ϵǾ����ϴ�.");
        return "redirect:departmentAll.do";
    }

    // 5. Ư�� �μ� ����
    @RequestMapping(value = "departmentDelete.do/{departmentId}", method = RequestMethod.POST)
    @ResponseBody
    public String deleteDepartment(@PathVariable("departmentId") String departmentId) {
        int result = departmentService.deleteDepartmentById(departmentId);
        if (result > 0) {
            return "�μ��� ���������� �����Ǿ����ϴ�.";
        } else {
            return "������ �μ��� �������� �ʽ��ϴ�.";
        }
    }

    // 6. Ư�� �μ� ���� ������Ʈ
    @RequestMapping(value = "departmentUpdate.do", method = RequestMethod.POST)
    public String updateDepartment(Department department, Model model) {
        int result = departmentService.updateDepartment(department);
        if (result > 0) {
            model.addAttribute("message", "�μ� ������ ���������� �����Ǿ����ϴ�.");
        } else {
            model.addAttribute("message", "�μ� ���� ������ �����߽��ϴ�.");
        }
        return "redirect:departmentAll.do";
    }
}