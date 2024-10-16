package com.e3i3.moduerp.qna.model.dao;

import java.util.List;

import com.e3i3.moduerp.employee.model.dto.Employee;
import com.e3i3.moduerp.qna.model.dto.QnaDto;

public interface QnaDao {

	List<QnaDto> getAllQna();

	List<Employee> getEmpNameDepart(String uuid);

	void insertQna(QnaDto qnaDto);

	QnaDto selectQnaDetail(String qSeq);

	void updateQuestion(QnaDto qnaDto);

	void updateQStatus(QnaDto qnaDto);

	void updateQStatusN(QnaDto qnaDto);

	void deleteQna(int qSeq);

	List<QnaDto> getQnaByEmpNameDate(String filterText, String startDate, String endDate);

	List<QnaDto> getQnaByQTitleDate(String filterText, String startDate, String endDate);

	List<QnaDto> getQnaByEmpName(String filterText);

	List<QnaDto> getQnaByQTitle(String filterText);

	List<QnaDto> getQnaByFilterOnlyDate(String startDate, String endDate);

	List<QnaDto> getQnaByFilterStartDate(String startDate);

	List<QnaDto> getQnaByFilterEndDate(String endDate);

}
