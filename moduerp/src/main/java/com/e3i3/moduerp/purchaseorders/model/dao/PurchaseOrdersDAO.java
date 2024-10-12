package com.e3i3.moduerp.purchaseorders.model.dao;

import java.util.List;
import com.e3i3.moduerp.purchaseorders.model.dto.PurchaseOrdersDTO;
import com.e3i3.moduerp.employee.model.dto.Employee;

public interface PurchaseOrdersDAO {
    List<PurchaseOrdersDTO> getAllPurchaseOrders();
    void purchaseOrderCreate(PurchaseOrdersDTO purchaseOrderDto);

    List<String> getEmpNamesByBizNumber(String bizNumber);
    List<String> getDepartmentIdsByBizNumber(String bizNumber);
    List<PurchaseOrdersDTO> getPurchaseOrdersByBizNumber(String bizNumber);
    
    List<Employee> getEmpNameDepart(String bizNumber);
    
    // accountNo 리스트 가져오기 !!!
    List<String> getAllAccountNames();
    
    // OrderId
    PurchaseOrdersDTO selectPurchaseOrderByOrderId(String orderId);
    void updatePurchaseOrder(PurchaseOrdersDTO purchaseOrderDto);
    void deletePurchaseOrderByOrderId(String orderId);
}
