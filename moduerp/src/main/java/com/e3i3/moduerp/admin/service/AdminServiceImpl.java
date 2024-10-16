package com.e3i3.moduerp.admin.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.e3i3.moduerp.admin.model.dao.AdminDAO;
import com.e3i3.moduerp.admin.model.dto.AdminDTO;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDAO adminDAO;

    @Override
    public List<AdminDTO> getAllAdmins() {
        return adminDAO.selectAllAdmins();
    }

	@Override
	public List<AdminDTO> getAdminByFilterDate(String option, String filterText, String startDate, String endDate) {
		if (option.equals("bizNumber")) {
			return adminDAO.getAdminByBizNumberDate(filterText, startDate, endDate);
		} else if (option.equals("empName")) {
			return adminDAO.getAdminByEmpNameDate(filterText, startDate, endDate);
		} else if (option.equals("empEmail")) {
			return adminDAO.getAdminByEmpEmailDate(filterText, startDate, endDate);
		} else if (option.equals("uuid")) {
			return adminDAO.getAdminByUuidDate(filterText, startDate, endDate);
		}
		return null;
	}

	@Override
	public List<AdminDTO> getAdminByFilter(String option, String filterText) {
		if (option.equals("bizNumber")) {
			return adminDAO.getAdminByBizNumber(filterText);
		} else if (option.equals("empName")) {
			return adminDAO.getAdminByEmpName(filterText);
		} else if (option.equals("empEmail")) {
			return adminDAO.getAdminByEmpEmail(filterText);
		} else if (option.equals("uuid")) {
			return adminDAO.getAdminByUuid(filterText);
		}
		return null;
	}

	@Override
	public List<AdminDTO> getAdminByFilterOnlyDate(String startDate, String endDate) {
		return adminDAO.getAdminByFilterOnlyDate(startDate, endDate);
	}

	@Override
	public List<AdminDTO> getAdminByFilterStartDate(String startDate) {
		return adminDAO.getAdminByFilterStartDate(startDate);
	}

	@Override
	public List<AdminDTO> getAdminByFilterEndDate(String endDate) {
		return adminDAO.getAdminByFilterEndDate(endDate);
	}
}
