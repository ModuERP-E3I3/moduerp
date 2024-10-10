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

	private static final String namespace = "WorkOrderMapper";

	@Override
	public List<WorkOrderDTO> selectWorkOrdersByBizNumber(String bizNumber) {
		return sqlSession.selectList(namespace + ".selectWorkOrdersByBizNumber", bizNumber);
	}

	@Override
	public WorkOrderDTO getWorkOrderByOrderNumber(String orderNumber) {
		return sqlSession.selectOne(namespace + ".getWorkOrderByOrderNumber", orderNumber);
	}

	@Override
	public List<String> getWorkerTeamsByBizNumber(String bizNumber) {
		return sqlSession.selectList(namespace + ".getWorkerTeamsByBizNumber", bizNumber);
	}

	@Override
	public List<String> getWorkPlacesByBizNumber(String bizNumber) {
		return sqlSession.selectList(namespace + ".getWorkPlacesByBizNumber", bizNumber);
	}

	@Override
	public void insertWorkOrder(WorkOrderDTO workOrderDTO) {
		sqlSession.insert(namespace + ".insertWorkOrder", workOrderDTO);
	}

	@Override
	public void updateWorkOrder(WorkOrderDTO workOrderDTO) {
		sqlSession.update(namespace + ".updateWorkOrder", workOrderDTO);
	}

	@Override
	public void deleteWorkOrder(String orderNumber) {
		// MyBatis를 통해 SQL 쿼리 호출
		sqlSession.delete(namespace + ".deleteWorkOrder", orderNumber);
	}

	@Override
	public int getTotalQtyByItemCode(String itemCode) {
		return sqlSession.selectOne(namespace + ".getTotalQtyByItemCode", itemCode);
	}
}
