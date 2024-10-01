package com.e3i3.moduerp.workorder.model.service;

import java.util.List;
import com.e3i3.moduerp.workorder.model.dto.WorkOrderDTO;

public interface WorkOrderService {
    List<WorkOrderDTO> getAllWorkOrders();
}
