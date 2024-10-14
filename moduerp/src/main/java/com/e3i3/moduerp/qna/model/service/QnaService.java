package com.e3i3.moduerp.qna.model.service;

import java.util.List;

import com.e3i3.moduerp.employee.model.dto.Employee;
import com.e3i3.moduerp.qna.model.dto.QnaDto;


public interface QnaService {

	List<QnaDto> getAllQna();

	List<Employee> getEmpNameDepart(String uuid);

	

}
