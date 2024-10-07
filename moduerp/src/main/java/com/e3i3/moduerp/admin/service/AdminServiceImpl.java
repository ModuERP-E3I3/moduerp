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
}
