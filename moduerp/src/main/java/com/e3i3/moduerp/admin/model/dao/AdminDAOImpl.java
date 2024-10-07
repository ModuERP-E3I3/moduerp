package com.e3i3.moduerp.admin.model.dao;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.e3i3.moduerp.admin.model.dto.AdminDTO;

@Repository
public class AdminDAOImpl implements AdminDAO {

    @Autowired
    private SqlSession sqlSession;

    private static final String namespace = "AdminMapper";

    @Override
    public List<AdminDTO> selectAllAdmins() {
        return sqlSession.selectList(namespace + ".selectAllAdmins");
    }
}
