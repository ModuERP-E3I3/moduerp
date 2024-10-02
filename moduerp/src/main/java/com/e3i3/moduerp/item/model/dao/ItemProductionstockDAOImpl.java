package com.e3i3.moduerp.item.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemProductionstockDAOImpl implements ItemProductionstockDAO {

    @Autowired
    private SqlSessionTemplate sqlSession;

    private static final String NAMESPACE = "ItemMapper.";

    @Override
    public List<String> selectItemNamesByBizNumber(String bizNumber) {
        return sqlSession.selectList(NAMESPACE + "selectItemNamesByBizNumber", bizNumber);
    }

    @Override
    public List<String> selectStockPlacesByBizNumber(String bizNumber) { // 추가된 메서드
        return sqlSession.selectList(NAMESPACE + "selectStockPlacesByBizNumber", bizNumber);
    }
}
