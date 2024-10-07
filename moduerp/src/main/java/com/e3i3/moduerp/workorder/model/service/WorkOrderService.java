package com.e3i3.moduerp.workorder.model.service;

import java.util.List;
import com.e3i3.moduerp.workorder.model.dto.WorkOrderDTO;

public interface WorkOrderService {
	List<WorkOrderDTO> getWorkOrdersByBizNumber(String bizNumber);

	WorkOrderDTO getWorkOrderByOrderNumber(String orderNumber);

	List<String> getWorkerTeamsByBizNumber(String bizNumber);

	List<String> getWorkPlacesByBizNumber(String bizNumber);

	void insertWorkOrder(WorkOrderDTO workOrderDTO);

	void updateWorkOrder(WorkOrderDTO workOrderDTO);

	void deleteWorkOrder(String orderNumber);

	int getTotalQtyByItemCode(String itemCode);
}
