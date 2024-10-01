package com.e3i3.moduerp.company.model.service;

import java.util.List;

import com.e3i3.moduerp.company.model.dto.Company;

public interface CompanyService {
	// ȸ�� ���
	public void insertCompany(Company company);


	// ȸ�� ����
	public void updateCompany(Company company);

	// ȸ�� ����
	public void deleteCompany(String bizNumber);
	

	// ȸ�� ��ȸ
	public Company selectCompanyByBizNumber(String bizNumber);
	

	// ȸ�翡 ���� �μ��� ��ȸ
	public Company selectCompanyWithDepartments(String bizNumber);
	
	// ��ü ȸ�� ��ȸ
	public List<Company> selectAllCompanies();
}