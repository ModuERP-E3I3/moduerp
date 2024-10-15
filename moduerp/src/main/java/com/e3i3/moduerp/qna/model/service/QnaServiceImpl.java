package com.e3i3.moduerp.qna.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e3i3.moduerp.employee.model.dto.Employee;
import com.e3i3.moduerp.qna.model.dao.QnaDao;
import com.e3i3.moduerp.qna.model.dto.QnaDto;

@Service
public class QnaServiceImpl implements QnaService{

	@Autowired
	private QnaDao QnaDao;
	
	@Override
	public List<QnaDto> getAllQna() {
		return QnaDao.getAllQna();
	}

	@Override
	public List<Employee> getEmpNameDepart(String uuid) {
		return QnaDao.getEmpNameDepart(uuid);
	}

	@Override
	public void insertQna(QnaDto qnaDto) {
		QnaDao.insertQna(qnaDto);
	}

	@Override
	public QnaDto getQnaDetail(String qSeq) {
		return QnaDao.selectQnaDetail(qSeq);
	}

	@Override
	public void updateQuestion(QnaDto qnaDto) {
		QnaDao.updateQuestion(qnaDto);
	}

	@Override
	public void deleteQna(String qSeq) {
		QnaDao.deleteQna(qSeq);
	}

	@Override
	public void updateQStatus(QnaDto qnaDto) {
		QnaDao.updateQStatus(qnaDto);
	}

}
