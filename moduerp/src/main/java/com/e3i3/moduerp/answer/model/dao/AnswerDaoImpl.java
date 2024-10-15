package com.e3i3.moduerp.answer.model.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.e3i3.moduerp.answer.model.dto.AnswerDto;

@Repository
public class AnswerDaoImpl implements AnswerDao {

	@Autowired
	private SqlSession sqlSession;
	
	private static final String namespace = "AnswerMapper";

	@Override
	public void insertAnswer(AnswerDto answerDto) {
		sqlSession.insert(namespace + ".insertAnswer", answerDto);
	}

	@Override
	public AnswerDto selectAnswerDetail(String qSeq) {
		return sqlSession.selectOne(namespace + ".selectAnswerDetail", qSeq);
	}
}
