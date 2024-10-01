package com.e3i3.moduerp.workorder.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.e3i3.moduerp.workorder.model.dto.WorkOrderDTO;
import com.e3i3.moduerp.workorder.model.service.WorkOrderService;

@Controller
@RequestMapping("/")
public class WorkorderController {

    @Autowired
    private WorkOrderService workOrderService;

    @RequestMapping(value = "/productionWorkorder.do", method = RequestMethod.GET)
    public String showWorkOrders(Model model) {
        List<WorkOrderDTO> workOrderList = workOrderService.getAllWorkOrders();
        model.addAttribute("workOrderList", workOrderList);
        return "productionStock/productionWorkorder";  // JSP ���� ���
    }
}
