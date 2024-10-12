package com.e3i3.moduerp.purchaseorders.service;

import java.util.List;

import com.e3i3.moduerp.purchaseorders.model.dto.PurchaseOrdersDTO;
import com.e3i3.moduerp.employee.model.dto.Employee;

public interface PurchaseOrdersService {
    List<PurchaseOrdersDTO> getAllPurchaseOrders();
    void purchaseOrderCreate(PurchaseOrdersDTO purchaseOrderDto);
    List<String> getEmpNamesByBizNumber(String bizNumber);
    List<String> getDepartmentIdsByBizNumber(String bizNumber);
    List<PurchaseOrdersDTO> getPurchaseOrdersByBizNumber(String bizNumber);
    List<Employee> getEmpNameDepart(String bizNumber);
    
    // OrderId
    PurchaseOrdersDTO getPurchaseOrderDetail(String orderId);
    void updatePurchaseOrder(PurchaseOrdersDTO purchaseOrderDto);
    void deletePurchaseOrderByOrderId(String orderId);
}
