package com.e3i3.moduerp.employee.model.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.e3i3.moduerp.employee.model.dao.EmployeeProductionDAO;

@Service
public class EmployeeProductionServiceImpl implements EmployeeProductionService {

    @Autowired
    private EmployeeProductionDAO employeeProductionDAO;

    @Override
    public List<String> getEmployeeNamesByBizNumber(String bizNumber) {
        return employeeProductionDAO.selectEmployeeNamesByBizNumber(bizNumber);
    }

    @Override
    public String getEmployeeNameByUuid(String uuid) {
        return employeeProductionDAO.selectEmployeeNameByUuid(uuid);
    }
}
