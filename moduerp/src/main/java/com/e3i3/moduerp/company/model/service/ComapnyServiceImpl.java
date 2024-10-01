package com.e3i3.moduerp.company.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e3i3.moduerp.company.model.dao.CompanyDao;
import com.e3i3.moduerp.company.model.dto.Company;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ComapnyServiceImpl implements CompanyService {

	
	 private static final Logger logger = LoggerFactory.getLogger(ComapnyServiceImpl.class);
	@Autowired
	private CompanyDao companyDao;
	
	@Override
	public void insertCompany(Company company) {
		//이미 존재하는 사업자번호라면 예외처리 호출
		if (companyDao.selectCompanyByBizNumber(company.getBizNumber()) != null) {
			 throw new RuntimeException("Company with the same bizNumber already exists.");
		} else {
			  companyDao.insertCompany(company);
		}
		
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
		//---- 만경
		logger.info("Executing selectCompanyByBizNumber in CompanyServiceImpl with bizNumber: " + bizNumber);
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