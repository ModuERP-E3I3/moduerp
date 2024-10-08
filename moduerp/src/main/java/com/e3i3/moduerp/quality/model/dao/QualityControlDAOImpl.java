package com.e3i3.moduerp.quality.model.dao;

import java.util.List;
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
}
