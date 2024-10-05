package com.e3i3.moduerp.salesstock.model.dao;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.e3i3.moduerp.salesstock.model.dto.SalesStockOutDTO;

@Repository
public class SalesStockOutDAOImpl implements SalesStockOutDAO {

    @Autowired
    private SqlSession sqlSession;

    private static final String namespace = "SalesStockOutMapper";

    @Override
    public List<SalesStockOutDTO> selectAllSalesStockOuts() {
        return sqlSession.selectList(namespace + ".selectAllSalesStockOuts");
    }
}
