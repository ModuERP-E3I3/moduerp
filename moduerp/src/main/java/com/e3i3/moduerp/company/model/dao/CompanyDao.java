package com.e3i3.moduerp.company.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.e3i3.moduerp.company.model.dto.Company;

@Repository
public class CompanyDao {
	// root-context.xml에 설정된 마이바티스 객체를 연결 사용함
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	// 회사 등록
	public void insertCompany(Company company) {
		sqlSessionTemplate.insert("CompanyMapper.insertCompany", company);
	}

	// 회사 수정
	public void updateCompany(Company company) {
		sqlSessionTemplate.update("CompanyMapper.updateCompany", company);
	}

	// 회사 삭제
	public void deleteCompany(String bizNumber) {
		sqlSessionTemplate.delete("CompanyMapper.deleteCompany", bizNumber);
	}

	// 회사 조회
	public Company selectCompanyByBizNumber(String bizNumber) {
		return sqlSessionTemplate.selectOne("CompanyMapper.selectCompanyByBizNumber", bizNumber);
	}
	
	// 전체 회사 조회
	public List<Company> selectAllCompanies(){
		return sqlSessionTemplate.selectList("CompanyMapper.selectAllCompanies");
	}
}
