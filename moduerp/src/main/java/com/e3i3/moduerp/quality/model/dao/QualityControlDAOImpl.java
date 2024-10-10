package com.e3i3.moduerp.quality.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.e3i3.moduerp.quality.model.dto.QualityControlDTO;

@Repository
public class QualityControlDAOImpl implements QualityControlDAO {

	@Autowired
	private SqlSession sqlSession;

	private static final String namespace = "QualityControlMapper";

	@Override
	public List<QualityControlDTO> findByBizNumber(String bizNumber) {
		return sqlSession.selectList(namespace + ".findByBizNumber", bizNumber);
	}

	@Override
	public void insertQulityControl(QualityControlDTO qulityDTO) {
		sqlSession.insert(namespace + ".insertQulityControl", qulityDTO);
	}

	@Override
	public int getTotalInspecQtyByOrderNumber(String orderNumber) {
		Integer totalQty = sqlSession.selectOne(namespace + ".getTotalInspecQtyByOrderNumber", orderNumber);
		return (totalQty != null) ? totalQty : 0; // null일 경우 0 반환
	}

	@Override
	public QualityControlDTO getQualityControlByInspecCode(String inspecCode) {
		return sqlSession.selectOne(namespace + ".getQualityControlByInspecCode", inspecCode);
	}

	@Override
	public void updateQualityControl(QualityControlDTO qualityControlDTO) {
		sqlSession.update(namespace + ".updateQualityControl", qualityControlDTO);
	}

	@Override
	public void deleteQualityControlByInspecCode(String inspecCode) {
		// MyBatis 쿼리를 호출하여 데이터 삭제
		sqlSession.delete(namespace + ".deleteQualityControlByInspecCode", inspecCode);
	}

	@Override
	public List<QualityControlDTO> getQualityItemNameByFilterDate(String bizNumber, String filterText, String startDate,
			String endDate) {
		return sqlSession.selectList(namespace + ".selectQualityItemNameByFilterDate",
				Map.of("bizNumber", bizNumber, "filterText", filterText, "startDate", startDate, "endDate", endDate));
	}

	@Override
	public List<QualityControlDTO> getQualityinspecTypeByFilterDate(String bizNumber, String filterText,
			String startDate, String endDate) {
		return sqlSession.selectList(namespace + ".selectQualityinspecTypeByFilterDate",
				Map.of("bizNumber", bizNumber, "filterText", filterText, "startDate", startDate, "endDate", endDate));
	}

	@Override
	public List<QualityControlDTO> getQualityrprogressStatusByFilterDate(String bizNumber, String filterText,
			String startDate, String endDate) {
		return sqlSession.selectList(namespace + ".selectQualityprogressStatusByFilterDate",
				Map.of("bizNumber", bizNumber, "filterText", filterText, "startDate", startDate, "endDate", endDate));
	}

	@Override
	public List<QualityControlDTO> getQualityqDirectorByFilterDate(String bizNumber, String filterText,
			String startDate, String endDate) {
		return sqlSession.selectList(namespace + ".selectQualityqDirectorByFilterDate",
				Map.of("bizNumber", bizNumber, "filterText", filterText, "startDate", startDate, "endDate", endDate));
	}

	@Override
	public List<QualityControlDTO> getQualityItemNameByFilter(String bizNumber, String filterText) {
		return sqlSession.selectList(namespace + ".selectQualityItemNameByFilter",
				Map.of("bizNumber", bizNumber, "filterText", filterText));

	}

	@Override
	public List<QualityControlDTO> getQualityinspecTypeByFilter(String bizNumber, String filterText) {
		return sqlSession.selectList(namespace + ".selectQualityinspecTypeByFilter",
				Map.of("bizNumber", bizNumber, "filterText", filterText));
	}

	@Override
	public List<QualityControlDTO> getQualityrprogressStatusByFilter(String bizNumber, String filterText) {
		return sqlSession.selectList(namespace + ".selectQualityprogressStatusByFilter",
				Map.of("bizNumber", bizNumber, "filterText", filterText));
	}

	@Override
	public List<QualityControlDTO> getQualityqDirectorByFilter(String bizNumber, String filterText) {
		return sqlSession.selectList(namespace + ".selectQualityqDirectorByFilter",
				Map.of("bizNumber", bizNumber, "filterText", filterText));
	}
}
