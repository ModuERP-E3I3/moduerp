package com.e3i3.moduerp.company.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e3i3.moduerp.company.model.dao.CompanyDao;
import com.e3i3.moduerp.company.model.dto.Company;

@Service
public class ComapnyServiceImpl implements CompanyService {

	@Autowired
	private CompanyDao companyDao;
	
	@Override
	public void insertCompany(Company company) {
		companyDao.insertCompany(company);
		
	}

	@Override
	public void updateCompany(Company company) {
		companyDao.updateCompany(company);
		
	}

	@Override
	public void deleteCompany(String bizNumber) {
		companyDao.deleteCompany(bizNumber);
		
	}

	@Override
	public Company selectCompanyByBizNumber(String bizNumber) {
		return companyDao.selectCompanyByBizNumber(bizNumber);
	}

	@Override
	public Company selectCompanyWithDepartments(String bizNumber) {
		return companyDao.selectCompanyWithDepartments(bizNumber);
	}

	@Override
	public List<Company> selectAllCompanies() {
		return companyDao.selectAllCompanies();
	}

}
