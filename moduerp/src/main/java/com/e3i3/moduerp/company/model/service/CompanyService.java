package com.e3i3.moduerp.company.model.service;

import java.util.List;

import com.e3i3.moduerp.company.model.dto.Company;

public interface CompanyService {
	// 회사 등록
	public void insertCompany(Company company);

	// 회사 수정
	public void updateCompany(Company company);

	// 회사 삭제
	public void deleteCompany(String bizNumber);

	// 회사 조회
	public Company selectCompanyByBizNumber(String bizNumber);

	// 회사에 속한 부서들 조회
	public Company selectCompanyWithDepartments(String bizNumber);

	// 전체 회사 조회
	public List<Company> selectAllCompanies();

	// 회사 카드 존재 여부
	public String selectComplayCardExitence(String bizNumber);

	void updateCompanyCardExistence(String cardBillingId, String bizNumber);

	public void insertModuleGradesOfCompany(String moduleGradesStr, String bizNumber);

	public String selectPurchasedModule(String bizNumber);

	public void updateModuleGrades(String combinedModuleGrades, String bizNumber);
}