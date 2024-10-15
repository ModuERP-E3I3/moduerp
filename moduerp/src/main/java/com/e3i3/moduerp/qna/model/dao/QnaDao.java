package com.e3i3.moduerp.qna.model.dao;

import java.util.List;

import com.e3i3.moduerp.employee.model.dto.Employee;
import com.e3i3.moduerp.qna.model.dto.QnaDto;

public interface QnaDao {

	List<QnaDto> getAllQna();

	List<Employee> getEmpNameDepart(String uuid);

	void insertQna(QnaDto qnaDto);

	QnaDto selectQnaDetail(String qSeq);

}
