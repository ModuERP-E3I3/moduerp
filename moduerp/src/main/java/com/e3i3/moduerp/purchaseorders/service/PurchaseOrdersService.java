package com.e3i3.moduerp.purchaseorders.service;

import java.util.List;
import java.util.Map;

import com.e3i3.moduerp.employee.model.dto.Employee;
import com.e3i3.moduerp.purchaseorders.model.dto.PurchaseOrdersDTO;

public interface PurchaseOrdersService {
	List<PurchaseOrdersDTO> getAllPurchaseOrders();

	void purchaseOrderCreate(PurchaseOrdersDTO purchaseOrderDto);

	List<String> getEmpNamesByBizNumber(String bizNumber);

	List<String> getDepartmentIdsByBizNumber(String bizNumber);

	List<PurchaseOrdersDTO> getPurchaseOrdersByBizNumber(String bizNumber);

	List<Employee> getEmpNameDepart(String bizNumber);

	// accountNo 리스트 가져오기 !!! - - - - - - - - - -
	List<Map<String, Object>> getAllAccountNames();

	List<String> getAllItemNames();

	// OrderId
	PurchaseOrdersDTO getPurchaseOrderDetail(String orderId);

	void updatePurchaseOrder(PurchaseOrdersDTO purchaseOrderDto);

	void deletePurchaseOrderByOrderId(String orderId);

	// -----------------------------------------------
	// Purchase Orders filter
	List<PurchaseOrdersDTO> getPurchaseOrdersByFilterDate(String bizNumber, String option, String filterText,
			String startDate, String endDate);

	List<PurchaseOrdersDTO> getPurchaseOrdersByFilter(String bizNumber, String option, String filterText);

	
	
	
}
