package com.e3i3.moduerp.workorder.model.dao;

import java.util.List;
import java.util.Map;

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

	// -------------------------------
	// QC
	@Override
	public List<WorkOrderDTO> findCompletedWorkOrders() {
		return sqlSession.selectList(namespace + ".findCompletedWorkOrders");
	}
	// -------------------------------

	@Override
	public List<WorkOrderDTO> getWorkOrderItemNameByFilterDate(String bizNumber, String filterText, String startDate,
			String endDate) {
		return sqlSession.selectList(namespace + ".selectWorkOrderItemNameByFilterDate",
				Map.of("bizNumber", bizNumber, "filterText", filterText, "startDate", startDate, "endDate", endDate));
	}

	@Override
	public List<WorkOrderDTO> getWorkOrdertaskNameByFilterDate(String bizNumber, String filterText, String startDate,
			String endDate) {
		return sqlSession.selectList(namespace + ".selectWorkOrdertaskNameByFilterDate",
				Map.of("bizNumber", bizNumber, "filterText", filterText, "startDate", startDate, "endDate", endDate));
	}

	@Override
	public List<WorkOrderDTO> getWorkOrderworkerByFilterDate(String bizNumber, String filterText, String startDate,
			String endDate) {
		return sqlSession.selectList(namespace + ".selectWorkOrderworkerByFilterDate",
				Map.of("bizNumber", bizNumber, "filterText", filterText, "startDate", startDate, "endDate", endDate));
	}

	@Override
	public List<WorkOrderDTO> getWorkOrderwDirectorByFilterDate(String bizNumber, String filterText, String startDate,
			String endDate) {
		return sqlSession.selectList(namespace + ".selectWorkOrderwDirectorByFilterDate",
				Map.of("bizNumber", bizNumber, "filterText", filterText, "startDate", startDate, "endDate", endDate));
	}

	@Override
	public List<WorkOrderDTO> getWorkOrderItemNameByFilter(String bizNumber, String filterText) {
		return sqlSession.selectList(namespace + ".selectWorkOrderItemNameByFilter",
				Map.of("bizNumber", bizNumber, "filterText", filterText));
	}

	@Override
	public List<WorkOrderDTO> getWorkOrdertaskNameByFilter(String bizNumber, String filterText) {
		return sqlSession.selectList(namespace + ".selectWorkOrdertaskNameByFilter",
				Map.of("bizNumber", bizNumber, "filterText", filterText));
	}

	@Override
	public List<WorkOrderDTO> getWorkOrderworkerByFilter(String bizNumber, String filterText) {
		return sqlSession.selectList(namespace + ".selectWorkOrderworkerByFilter",
				Map.of("bizNumber", bizNumber, "filterText", filterText));
	}

	@Override
	public List<WorkOrderDTO> getWorkOrderwDirectorByFilter(String bizNumber, String filterText) {
		return sqlSession.selectList(namespace + ".selectWorkOrderwDirectorByFilter",
				Map.of("bizNumber", bizNumber, "filterText", filterText));
	}
}
