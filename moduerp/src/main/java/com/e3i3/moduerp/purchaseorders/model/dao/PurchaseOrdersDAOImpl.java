package com.e3i3.moduerp.purchaseorders.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.e3i3.moduerp.employee.model.dto.Employee;
import com.e3i3.moduerp.purchaseorders.model.dto.PurchaseOrdersDTO;

@Repository
public class PurchaseOrdersDAOImpl implements PurchaseOrdersDAO {
    @Autowired
    private SqlSession sqlSession;
    
    private static final String namespace = "PurchaseOrdersMapper";
    
    @Override
    public List<PurchaseOrdersDTO> getAllPurchaseOrders() {
        return sqlSession.selectList(namespace + ".getAllPurchaseOrders");
    }
    
    @Override
    public void purchaseOrderCreate(PurchaseOrdersDTO purchaseOrderDto) {
        sqlSession.insert(namespace + ".purchaseOrderCreate", purchaseOrderDto);
    }
    
    public PurchaseOrdersDTO getPurchaseOrderById(String orderId) {
        return sqlSession.selectOne(namespace + ".getPurchaseOrderById", orderId);
    }

    @Override
    public List<String> getEmpNamesByBizNumber(String bizNumber) {
        return sqlSession.selectList(namespace + ".getEmpNamesByBizNumber", bizNumber);
    }

    @Override
    public List<String> getDepartmentIdsByBizNumber(String bizNumber) {
        return sqlSession.selectList(namespace + ".getDepartmentIdsByBizNumber", bizNumber);
    }

    @Override
    public List<PurchaseOrdersDTO> getPurchaseOrdersByBizNumber(String bizNumber) {
        return sqlSession.selectList(namespace + ".getPurchaseOrdersByBizNumber", bizNumber);
    }

    @Override
    public List<Employee> getEmpNameDepart(String bizNumber) {
        return sqlSession.selectList(namespace + ".getEmpNameDepart", bizNumber);
    }

    @Override
    public PurchaseOrdersDTO selectPurchaseOrderByOrderId(String orderId) {
        return sqlSession.selectOne(namespace + ".selectPurchaseOrderByOrderId", orderId);
    }

    @Override
    public void updatePurchaseOrder(PurchaseOrdersDTO purchaseOrderDto) {
        sqlSession.update(namespace + ".updatePurchaseOrder", purchaseOrderDto);    
    }

    @Override
    public void deletePurchaseOrderByOrderId(String orderId) {
        sqlSession.delete(namespace + ".deletePurchaseOrderByOrderId", orderId);
    }

 // accountNo와 accountName 리스트 가져오기
    @Override
    public List<Map<String, Object>> getAllAccountNames() {
        return sqlSession.selectList(namespace + ".getAllAccountNames");
    }

    
    
    
}
