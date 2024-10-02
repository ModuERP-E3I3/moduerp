package com.e3i3.moduerp.item.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemDAOImpl implements ItemDAO {

    @Autowired
    private SqlSessionTemplate sqlSession;

    private static final String NAMESPACE = "ItemMapper.";

    @Override
    public List<String> selectItemNamesByBizNumber(String bizNumber) {
        return sqlSession.selectList(NAMESPACE + "selectItemNamesByBizNumber", bizNumber);
    }
}
