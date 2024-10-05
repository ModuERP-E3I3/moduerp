package com.e3i3.moduerp.workorder.model.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.e3i3.moduerp.workorder.model.dao.WorkOrderDAO;
import com.e3i3.moduerp.workorder.model.dto.WorkOrderDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class WorkOrderServiceImpl implements WorkOrderService {

  
    
    private static final Logger logger = LoggerFactory.getLogger(WorkOrderServiceImpl.class);

    @Autowired
    private WorkOrderDAO workOrderDAO;

    @Override
    public List<WorkOrderDTO> getAllWorkOrders() {
        List<WorkOrderDTO> workOrderList = workOrderDAO.getAllWorkOrders();
        logger.info("Work orders retrieved: {}", workOrderList);
        return workOrderList;
    }
}
