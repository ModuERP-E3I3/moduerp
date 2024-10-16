package com.e3i3.moduerp.qna.model.service;

import java.util.List;

import com.e3i3.moduerp.employee.model.dto.Employee;
import com.e3i3.moduerp.qna.model.dto.QnaDto;


public interface QnaService {

	List<QnaDto> getAllQna();

	List<Employee> getEmpNameDepart(String uuid);

	void insertQna(QnaDto qnaDto);

	QnaDto getQnaDetail(String qSeq);

	void updateQuestion(QnaDto qnaDto);


	void updateQStatus(QnaDto qnaDto);

	void updateQStatusN(QnaDto qnaDto);

	void deleteQna(int qSeq);

	List<QnaDto> getQnaByFilterDate(String option, String filterText, String startDate, String endDate);

	List<QnaDto> getQnaByFilter(String option, String filterText);

	List<QnaDto> getQnaByFilterOnlyDate(String startDate, String endDate);

	List<QnaDto> getQnaByFilterStartDate(String startDate);

	List<QnaDto> getQnaByFilterEndDate(String endDate);

	

}
