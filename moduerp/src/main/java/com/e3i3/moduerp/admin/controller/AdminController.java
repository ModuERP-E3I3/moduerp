package com.e3i3.moduerp.admin.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.e3i3.moduerp.admin.model.dto.AdminDTO;
import com.e3i3.moduerp.admin.service.AdminService;

@Controller
@RequestMapping("/")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // admin.jsp 요청 처리
    @RequestMapping("/admin.do")
    public String showAdmin(Model model) {
        List<AdminDTO> adminList = adminService.getAllAdmins();
        model.addAttribute("adminList", adminList);
        return "admin/admin";
    }

}
