package com.e3i3.moduerp.qna.model.dao;

import java.util.List;
import java.util.Map;

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

	@Override
	public QnaDto selectQnaDetail(String qSeq) {
		return sqlSession.selectOne(namespace + ".selectQnaDetail", qSeq);
	}

	@Override
	public void updateQuestion(QnaDto qnaDto) {
		sqlSession.update(namespace + ".updateQuestion", qnaDto);
	}


	@Override
	public void updateQStatus(QnaDto qnaDto) {
		sqlSession.update(namespace + ".updateQStatus", qnaDto);
	}

	@Override
	public void updateQStatusN(QnaDto qnaDto) {
		sqlSession.update(namespace + ".updateQStatusN", qnaDto);
	}

	@Override
	public void deleteQna(int qSeq) {
		sqlSession.delete(namespace + ".deleteQna", qSeq);
	}

	@Override
	public List<QnaDto> getQnaByEmpNameDate(String filterText, String startDate, String endDate) {
		return sqlSession.selectList(namespace + ".selectQnaByEmpNameDate",
				Map.of("filterText", filterText, "startDate", startDate, "endDate", endDate));
	}

	@Override
	public List<QnaDto> getQnaByQTitleDate(String filterText, String startDate, String endDate) {
		return sqlSession.selectList(namespace + ".selectQnaByQTitleDate",
				Map.of("filterText", filterText, "startDate", startDate, "endDate", endDate));
	}

	@Override
	public List<QnaDto> getQnaByEmpName(String filterText) {
		return sqlSession.selectList(namespace + ".selectQnaByEmpName",
				Map.of("filterText", filterText));
	}

	@Override
	public List<QnaDto> getQnaByQTitle(String filterText) {
		return sqlSession.selectList(namespace + ".selectQnaByQTitle",
				Map.of("filterText", filterText));
	}

	@Override
	public List<QnaDto> getQnaByFilterOnlyDate(String startDate, String endDate) {
		return sqlSession.selectList(namespace + ".selectQnaByFilterOnlyDate",
				Map.of("startDate", startDate, "endDate", endDate));
	}

	@Override
	public List<QnaDto> getQnaByFilterStartDate(String startDate) {
		return sqlSession.selectList(namespace + ".selectQnaByFilterStartDate",
				Map.of("startDate", startDate));
	}

	@Override
	public List<QnaDto> getQnaByFilterEndDate(String endDate) {
		return sqlSession.selectList(namespace + ".selectQnaByFilterEndDate",
				Map.of("endDate", endDate));
	}

}
