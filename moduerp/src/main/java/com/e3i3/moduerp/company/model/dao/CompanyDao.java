package com.e3i3.moduerp.company.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	// 회사에 속한 부서들 조회
	public Company selectCompanyWithDepartments(String bizNumber) {
		return sqlSessionTemplate.selectOne("CompanyMapper.selectCompanyWithDepartments", bizNumber);
	}

	// 전체 회사 조회
	public List<Company> selectAllCompanies() {
		return sqlSessionTemplate.selectList("CompanyMapper.selectAllCompanies");
	}

	// 회사 카드 조회
	public String selectComplayCardExitence(String bizNumber) {
		return sqlSessionTemplate.selectOne("CompanyMapper.selectComplayCardExitence", bizNumber);
	}

	public void updateCardExistence(String cardBillingId, String bizNumber) {
		 Map<String, Object> params = new HashMap<>();
	        params.put("cardBillingId", cardBillingId);
	        params.put("bizNumber", bizNumber);

	        sqlSessionTemplate.update( "CompanyMapper.updateCardExistence", params);
	    }

	public void updateModuleGrades(String moduleGrades, String bizNumber) {
		  Map<String, Object> paramMap = new HashMap<>();
	        paramMap.put("moduleGrades", moduleGrades);
	        paramMap.put("bizNumber", bizNumber);

	        sqlSessionTemplate.update("CompanyMapper.updateModuleGrades", paramMap);
		
	}

	public String selectPurchasedModule(String bizNumber) {
		  return sqlSessionTemplate.selectOne("CompanyMapper.selectPurchasedModule", bizNumber);
	}

	public void updateNewModuleGrades(String combinedModuleGrades, String bizNumber) {
		 // Map으로 파라미터 전달
        Map<String, Object> params = new HashMap<>();
        params.put("combinedModuleGrades", combinedModuleGrades);
        params.put("bizNumber", bizNumber);

        sqlSessionTemplate.update("CompanyMapper.updateNewModuleGrades", params);
		
	}

	public void deleteCardExistenceByBizNumber(String bizNumber) {
		 sqlSessionTemplate.update("CompanyMapper.deleteCardExistenceByBizNumber", bizNumber);
		
	}

}