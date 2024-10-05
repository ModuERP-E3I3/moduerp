package com.e3i3.moduerp.workorder.model.dao;

import java.util.List;
import com.e3i3.moduerp.workorder.model.dto.WorkOrderDTO;

public interface WorkOrderDAO {
    List<WorkOrderDTO> getAllWorkOrders();
}
