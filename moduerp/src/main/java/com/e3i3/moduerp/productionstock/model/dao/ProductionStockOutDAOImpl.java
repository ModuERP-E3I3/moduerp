package com.e3i3.moduerp.productionstock.model.dao;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.e3i3.moduerp.productionstock.model.dto.ProductionStockOutDTO;

@Repository
public class ProductionStockOutDAOImpl implements ProductionStockOutDAO {

    @Autowired
    private SqlSession sqlSession;

    private static final String namespace = "ProductionStockOutMapper";

    @Override
    public List<ProductionStockOutDTO> selectAllProductionStockOuts() {
        return sqlSession.selectList(namespace + ".selectAllProductionStockOuts");
    }
}
