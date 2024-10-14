package com.e3i3.moduerp.qna.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.e3i3.moduerp.employee.model.dto.Employee;
import com.e3i3.moduerp.qna.model.dto.QnaDto;

@Repository
public class QnaDaoImpl implements QnaDao {

	@Autowired
	private SqlSession sqlSession;

	private static final String namespace = "QnaMapper";
	
	@Override
	public List<QnaDto> getAllQna() {
		return sqlSession.selectList(namespace + ".getAllQna");
	}

	@Override
	public List<Employee> getEmpNameDepart(String uuid) {
		return sqlSession.selectList(namespace + ".getEmpNameDepart", uuid);
	}

	@Override
	public void insertQna(QnaDto qnaDto) {
		sqlSession.insert(namespace + ".insertQna", qnaDto);
	}
}
