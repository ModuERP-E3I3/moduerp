package com.e3i3.moduerp.salesstock.model.dao;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.e3i3.moduerp.salesstock.model.dto.SalesStockInDTO;

@Repository
public class SalesStockInDAOImpl implements SalesStockInDAO {

    @Autowired
    private SqlSession sqlSession;

    private static final String namespace = "SalesStockMapper";

    @Override
    public List<SalesStockInDTO> getAllSalesStockIn() {
        return sqlSession.selectList(namespace + ".getAllSalesStockIn");
    }

    public SalesStockInDTO getSalesStockInById(String sStockInId) {
        return sqlSession.selectOne(namespace + ".getSalesStockInById", sStockInId);
    }
}
