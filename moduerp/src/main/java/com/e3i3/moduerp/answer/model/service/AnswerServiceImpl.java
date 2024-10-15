package com.e3i3.moduerp.answer.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e3i3.moduerp.answer.model.dao.AnswerDao;
import com.e3i3.moduerp.answer.model.dto.AnswerDto;

@Service
public class AnswerServiceImpl implements AnswerService {
	
	@Autowired
	private AnswerDao AnswerDao;

	@Override
	public void insertAnswer(AnswerDto answerDto) {
		AnswerDao.insertAnswer(answerDto);
	}

	@Override
	public AnswerDto getAnswerDetail(String qSeq) {
		return AnswerDao.selectAnswerDetail(qSeq);
	}
}
