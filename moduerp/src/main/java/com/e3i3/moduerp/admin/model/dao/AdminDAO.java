package com.e3i3.moduerp.admin.model.dao;

import java.util.List;
import com.e3i3.moduerp.admin.model.dto.AdminDTO;

public interface AdminDAO {
    List<AdminDTO> selectAllAdmins();
}
