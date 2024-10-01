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

	private static final String namespace = "QualityControlMapper.";

	@Override
	public List<QualityControlDTO> selectAllQualityControls() {
		return sqlSession.selectList(namespace + "selectAllQualityControls");

	}

}
