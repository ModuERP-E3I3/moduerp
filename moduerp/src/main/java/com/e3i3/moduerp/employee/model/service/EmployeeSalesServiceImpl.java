package com.e3i3.moduerp.employee.model.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.e3i3.moduerp.employee.model.dao.EmployeeSalesDAO;

@Service
public class EmployeeSalesServiceImpl implements EmployeeSalesService {

    @Autowired
    private EmployeeSalesDAO employeeSalesDAO;

    @Override
    public List<String> getEmployeeNamesByBizNumber(String bizNumber) {
        return employeeSalesDAO.selectEmployeeNamesByBizNumber(bizNumber);
    }

    @Override
    public String getEmployeeNameByUuid(String uuid) {
        return employeeSalesDAO.selectEmployeeNameByUuid(uuid);
    }
}
