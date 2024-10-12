package com.e3i3.moduerp.purchaseorders.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e3i3.moduerp.purchaseorders.model.dao.PurchaseOrdersDAO;
import com.e3i3.moduerp.purchaseorders.model.dto.PurchaseOrdersDTO;
import com.e3i3.moduerp.employee.model.dto.Employee;

@Service
public class PurchaseOrdersServiceImpl implements PurchaseOrdersService {
    @Autowired
    private PurchaseOrdersDAO purchaseOrdersDao;
   
    @Override
    public List<PurchaseOrdersDTO> getAllPurchaseOrders() {
        return purchaseOrdersDao.getAllPurchaseOrders();
    }

    @Override
    public void purchaseOrderCreate(PurchaseOrdersDTO purchaseOrderDto) {
        purchaseOrdersDao.purchaseOrderCreate(purchaseOrderDto);
    }

    @Override
    public List<String> getEmpNamesByBizNumber(String bizNumber) {
        return purchaseOrdersDao.getEmpNamesByBizNumber(bizNumber);
    }

    @Override
    public List<String> getDepartmentIdsByBizNumber(String bizNumber) {
        return purchaseOrdersDao.getDepartmentIdsByBizNumber(bizNumber);
    }

    @Override
    public List<PurchaseOrdersDTO> getPurchaseOrdersByBizNumber(String bizNumber) {
        return purchaseOrdersDao.getPurchaseOrdersByBizNumber(bizNumber);
    }

    @Override
    public List<Employee> getEmpNameDepart(String bizNumber) {
        return purchaseOrdersDao.getEmpNameDepart(bizNumber);
    }

    @Override
    public PurchaseOrdersDTO getPurchaseOrderDetail(String orderId) {
        return purchaseOrdersDao.selectPurchaseOrderByOrderId(orderId);
    }

    @Override
    public void updatePurchaseOrder(PurchaseOrdersDTO purchaseOrderDto) {
        purchaseOrdersDao.updatePurchaseOrder(purchaseOrderDto);
    }

    @Override
    public void deletePurchaseOrderByOrderId(String orderId) {
        purchaseOrdersDao.deletePurchaseOrderByOrderId(orderId);
    }
}
