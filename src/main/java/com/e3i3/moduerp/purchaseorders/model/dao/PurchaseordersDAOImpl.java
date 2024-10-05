package com.e3i3.moduerp.purchaseorders.model.dao;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.e3i3.moduerp.purchaseorders.model.dto.PurchaseordersDTO;

@Repository
public class PurchaseordersDAOImpl implements PurchaseordersDAO {

    @Autowired
    private SqlSession sqlSession;

    private static final String namespace = "PurchaseordersMapper";

    @Override
    public List<PurchaseordersDTO> selectAllPurchaseorders() {
        return sqlSession.selectList(namespace + ".selectAllPurchaseorders");
    }
}
