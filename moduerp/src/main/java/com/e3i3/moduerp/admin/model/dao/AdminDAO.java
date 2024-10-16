package com.e3i3.moduerp.admin.model.dao;

import java.util.List;
import com.e3i3.moduerp.admin.model.dto.AdminDTO;

public interface AdminDAO {
    List<AdminDTO> selectAllAdmins();

	List<AdminDTO> getAdminByBizNumberDate(String filterText, String startDate, String endDate);

	List<AdminDTO> getAdminByEmpNameDate(String filterText, String startDate, String endDate);

	List<AdminDTO> getAdminByEmpEmailDate(String filterText, String startDate, String endDate);

	List<AdminDTO> getAdminByUuidDate(String filterText, String startDate, String endDate);

	List<AdminDTO> getAdminByBizNumber(String filterText);

	List<AdminDTO> getAdminByEmpName(String filterText);

	List<AdminDTO> getAdminByEmpEmail(String filterText);

	List<AdminDTO> getAdminByUuid(String filterText);

	List<AdminDTO> getAdminByFilterOnlyDate(String startDate, String endDate);

	List<AdminDTO> getAdminByFilterStartDate(String startDate);

	List<AdminDTO> getAdminByFilterEndDate(String endDate);
}
