package com.e3i3.moduerp.employee.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e3i3.moduerp.employee.model.dao.EmployeeBuyDAO;



@Service
public class EmployeeBuyServiceImpl implements EmployeeBuyService{
	
	@Autowired
    private EmployeeBuyDAO employeeBuyDAO;

	@Override
	public List<String> getEmployeeNamesByBizNumber(String bizNumber) {
		return employeeBuyDAO.selectEmployeeNamesByBizNumber(bizNumber);
	}

	@Override
	public String getEmployeeNameByUuid(String uuid) {
		return employeeBuyDAO.selectEmployeeNameByUuid(uuid);
	}

}
