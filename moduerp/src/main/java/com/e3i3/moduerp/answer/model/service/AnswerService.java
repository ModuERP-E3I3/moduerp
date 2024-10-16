package com.e3i3.moduerp.answer.model.service;

import com.e3i3.moduerp.answer.model.dto.AnswerDto;

public interface AnswerService {

	void insertAnswer(AnswerDto answerDto);

	AnswerDto getAnswerDetail(String qSeq);

	void updateAnswer(AnswerDto answerDto);

	void deleteAnswer(String aSeq);

}
