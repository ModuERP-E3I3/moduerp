package com.e3i3.moduerp.workorder.model.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.e3i3.moduerp.workorder.model.dao.WorkOrderDAO;
import com.e3i3.moduerp.workorder.model.dto.WorkOrderDTO;

@Service
public class WorkOrderServiceImpl implements WorkOrderService {

	@Autowired
	private WorkOrderDAO workOrderDAO;

	@Override
	public List<WorkOrderDTO> getWorkOrdersByBizNumber(String bizNumber) {
		return workOrderDAO.selectWorkOrdersByBizNumber(bizNumber);
	}

	@Override
	public WorkOrderDTO getWorkOrderByOrderNumber(String orderNumber) {
		return workOrderDAO.getWorkOrderByOrderNumber(orderNumber);
	}

	@Override
	public List<String> getWorkerTeamsByBizNumber(String bizNumber) {
		return workOrderDAO.getWorkerTeamsByBizNumber(bizNumber);
	}

	@Override
	public List<String> getWorkPlacesByBizNumber(String bizNumber) {
		return workOrderDAO.getWorkPlacesByBizNumber(bizNumber);
	}

	@Override
	public void insertWorkOrder(WorkOrderDTO workOrderDTO) {
		workOrderDAO.insertWorkOrder(workOrderDTO);
	}

	@Override
	public void updateWorkOrder(WorkOrderDTO workOrderDTO) {
		workOrderDAO.updateWorkOrder(workOrderDTO);
	}

	@Override
	public void deleteWorkOrder(String orderNumber) {
		// DAO를 통해 orderNumber에 해당하는 작업지시서 삭제
		workOrderDAO.deleteWorkOrder(orderNumber);
	}

	@Override
	public int getTotalQtyByItemCode(String itemCode) {
		return workOrderDAO.getTotalQtyByItemCode(itemCode);
	}

	// -------------------------------
	// QC
	@Override
	public List<WorkOrderDTO> getCompletedWorkOrders() {
		return workOrderDAO.findCompletedWorkOrders();
	}

	// -------------------------------

	@Override
	public List<WorkOrderDTO> getWorkOrderByFilterDate(String bizNumber, String option, String filterText,
			String startDate, String endDate) {
		if (option.equals("itemName")) {
			return workOrderDAO.getWorkOrderItemNameByFilterDate(bizNumber, filterText, startDate, endDate);
		} else if (option.equals("taskName")) {
			return workOrderDAO.getWorkOrdertaskNameByFilterDate(bizNumber, filterText, startDate, endDate);
		} else if (option.equals("worker")) {
			return workOrderDAO.getWorkOrderworkerByFilterDate(bizNumber, filterText, startDate, endDate);
		} else if (option.equals("wDirector")) {
			return workOrderDAO.getWorkOrderwDirectorByFilterDate(bizNumber, filterText, startDate, endDate);
		}

		return null;
	}

	@Override
	public List<WorkOrderDTO> getWorkOrderByFilter(String bizNumber, String option, String filterText) {
		if (option.equals("itemName")) {
			return workOrderDAO.getWorkOrderItemNameByFilter(bizNumber, filterText);
		} else if (option.equals("taskName")) {
			return workOrderDAO.getWorkOrdertaskNameByFilter(bizNumber, filterText);
		} else if (option.equals("worker")) {
			return workOrderDAO.getWorkOrderworkerByFilter(bizNumber, filterText);
		}else if (option.equals("wDirector")) {
			return workOrderDAO.getWorkOrderwDirectorByFilter(bizNumber, filterText);
		}
		return null;
	}
}
