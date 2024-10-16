package com.e3i3.moduerp.admin.service;

import java.util.List;
import com.e3i3.moduerp.admin.model.dto.AdminDTO;

public interface AdminService {
    List<AdminDTO> getAllAdmins();

	List<AdminDTO> getAdminByFilterDate(String option, String filterText, String startDate, String endDate);

	List<AdminDTO> getAdminByFilter(String option, String filterText);

	List<AdminDTO> getAdminByFilterOnlyDate(String startDate, String endDate);

	List<AdminDTO> getAdminByFilterStartDate(String startDate);

	List<AdminDTO> getAdminByFilterEndDate(String endDate);

	
}
