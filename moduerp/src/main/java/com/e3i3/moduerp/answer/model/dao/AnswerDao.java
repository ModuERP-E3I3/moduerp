package com.e3i3.moduerp.answer.model.dao;

import com.e3i3.moduerp.answer.model.dto.AnswerDto;

public interface AnswerDao {

	void insertAnswer(AnswerDto answerDto);

	AnswerDto selectAnswerDetail(String qSeq);

	void updateAnswer(AnswerDto answerDto);

}
