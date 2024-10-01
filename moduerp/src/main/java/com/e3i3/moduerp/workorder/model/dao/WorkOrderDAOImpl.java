package com.e3i3.moduerp.workorder.model.dao;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.e3i3.moduerp.workorder.model.dto.WorkOrderDTO;

@Repository
public class WorkOrderDAOImpl implements WorkOrderDAO {

    @Autowired
    private SqlSession sqlSession;

    private static final String namespace = "WorkOrderMapper.";

    @Override
    public List<WorkOrderDTO> getAllWorkOrders() {
        return sqlSession.selectList(namespace + "getAllWorkOrders");
    }
}
