package com.e3i3.moduerp.productionstock.dao;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.e3i3.moduerp.productionstock.dto.ProductionStockInDTO;

@Repository
public class ProductionStockInDAOImpl implements ProductionStockInDAO {

    @Autowired
    private SqlSession sqlSession;

    private static final String namespace = "ProductionStockMapper";

    @Override
    public List<ProductionStockInDTO> getAllProductionStockIn() {
        return sqlSession.selectList(namespace + ".getAllProductionStockIn");
    }

  
}
