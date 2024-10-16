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
	public void updateQStatus(QnaDto qnaDto) {
		QnaDao.updateQStatus(qnaDto);
	}

	@Override
	public void updateQStatusN(QnaDto qnaDto) {
		QnaDao.updateQStatusN(qnaDto);
	}

	@Override
	public void deleteQna(int qSeq) {
		QnaDao.deleteQna(qSeq);
	}

	@Override
	public List<QnaDto> getQnaByFilterDate(String option, String filterText, String startDate, String endDate) {
		if (option.equals("empName")) {
			return QnaDao.getQnaByEmpNameDate(filterText, startDate, endDate);
		} else if (option.equals("qTitle")) {
			return QnaDao.getQnaByQTitleDate(filterText, startDate, endDate);
		}
		return null;
	}

	@Override
	public List<QnaDto> getQnaByFilter(String option, String filterText) {
		if (option.equals("empName")) {
			return QnaDao.getQnaByEmpName(filterText);
		} else if (option.equals("qTitle")) {
			return QnaDao.getQnaByQTitle(filterText);
		}
		return null;
	}

	@Override
	public List<QnaDto> getQnaByFilterOnlyDate(String startDate, String endDate) {
		return QnaDao.getQnaByFilterOnlyDate(startDate, endDate);
	}

	@Override
	public List<QnaDto> getQnaByFilterStartDate(String startDate) {
		return QnaDao.getQnaByFilterStartDate(startDate);
	}

	@Override
	public List<QnaDto> getQnaByFilterEndDate(String endDate) {
		return QnaDao.getQnaByFilterEndDate(endDate);
	}

}
