package com.e3i3.moduerp.company.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.e3i3.moduerp.company.model.dto.Company;

@Repository
public class CompanyDao {
	// root-context.xml�� ������ ���̹�Ƽ�� ��ü�� ���� �����
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	// ȸ�� ���
	public void insertCompany(Company company) {
		sqlSessionTemplate.insert("CompanyMapper.insertCompany", company);
	}


	// ȸ�� ����
	public void updateCompany(Company company) {
		sqlSessionTemplate.update("CompanyMapper.updateCompany", company);
	}

	// ȸ�� ����
	public void deleteCompany(String bizNumber) {
		sqlSessionTemplate.delete("CompanyMapper.deleteCompany", bizNumber);
	}

	// ȸ�� ��ȸ
	public Company selectCompanyByBizNumber(String bizNumber) {
		return sqlSessionTemplate.selectOne("CompanyMapper.selectCompanyByBizNumber", bizNumber);
	}

	// ȸ�翡 ���� �μ��� ��ȸ
	public Company selectCompanyWithDepartments(String bizNumber) {
		return sqlSessionTemplate.selectOne("CompanyMapper.selectCompanyWithDepartments", bizNumber);
	}

	// ��ü ȸ�� ��ȸ
	public List<Company> selectAllCompanies() {
		return sqlSessionTemplate.selectList("CompanyMapper.selectAllCompanies");
	}
}