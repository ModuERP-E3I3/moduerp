package com.e3i3.moduerp.purchaseorders.model.dao;

import java.util.List;
import java.util.Map;

import com.e3i3.moduerp.employee.model.dto.Employee;
import com.e3i3.moduerp.purchaseorders.model.dto.PurchaseOrdersDTO;

public interface PurchaseOrdersDAO {

	void purchaseOrderCreate(PurchaseOrdersDTO purchaseOrderDto);

	List<String> getEmpNamesByBizNumber(String bizNumber);

	List<String> getDepartmentIdsByBizNumber(String bizNumber);

	List<PurchaseOrdersDTO> getPurchaseOrdersByBizNumber(String bizNumber);

	List<Employee> getEmpNameDepart(String bizNumber);

	// accountNo 리스트 가져오기 !!!
	List<Map<String, Object>> getAllAccountNames();

	// !! 발주서 코드 !!
	PurchaseOrdersDTO selectPurchaseOrderByOrderId(String orderId);

	void updatePurchaseOrder(PurchaseOrdersDTO purchaseOrderDto);

	void deletePurchaseOrderByOrderId(String orderId);

	// -----------------------------------------------
	// Purchase Orders filter   !!!!
	List<PurchaseOrdersDTO> getPurchaseOrdersByAccountNameDate(String bizNumber, String filterText, String startDate, String endDate);

	List<PurchaseOrdersDTO> getPurchaseOrdersByItemNameDate(String bizNumber, String filterText, String startDate, String endDate);

	List<PurchaseOrdersDTO> getPurchaseOrdersByODirectorDate(String bizNumber, String filterText, String startDate, String endDate);

	List<PurchaseOrdersDTO> getPurchaseOrdersByAccountName(String bizNumber, String filterText);

	List<PurchaseOrdersDTO> getPurchaseOrdersByItemName(String bizNumber, String filterText);

	List<PurchaseOrdersDTO> getPurchaseOrdersByODirector(String bizNumber, String filterText);

	
	// 날짜 데이터만 조회할 경우!
	List<PurchaseOrdersDTO> getPurchaseOrdersByFilterOnlyDate(String bizNumber, String startDate, String endDate);

	List<PurchaseOrdersDTO> getPurchaseOrdersByFilterStartDate(String bizNumber, String startDate);

	List<PurchaseOrdersDTO> getPurchaseOrdersByFilterEndDate(String bizNumber, String endDate);

	List<PurchaseOrdersDTO> getAllPurchaseOrders(String bizNumber);

	
	
	
	
}



