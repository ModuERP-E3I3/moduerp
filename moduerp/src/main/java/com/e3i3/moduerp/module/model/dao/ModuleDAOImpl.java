package com.e3i3.moduerp.module.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.e3i3.moduerp.module.model.dto.ModuleDTO;

@Repository
public class ModuleDAOImpl implements ModuleDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;

	private static final String namespace = "ModuleMapper";

	@Override
	public List<ModuleDTO> selectActiveModules() {
		return sqlSession.selectList(namespace + ".selectActiveModules");
	}
	@Override
	public List<ModuleDTO> getModulesN() {
		return sqlSession.selectList(namespace + ".selectModulesN");
	}

	@Override
	public void insertModule(ModuleDTO module) {
		sqlSession.insert(namespace + ".insertModule", module);
	}
	@Override
	public ModuleDTO getModuleDetailByModuleId(String moduleId) {
		return sqlSession.selectOne(namespace + ".getModuleDetailByModuleId", moduleId);
	}
	@Override
	public void updateModule(ModuleDTO module) {
		sqlSession.update(namespace + ".updateModule", module);
		
	}
	@Override
	public void deleteModule(String moduleId) {
		sqlSession.delete(namespace + ".deleteModule", moduleId);
	}
	@Override
	public List<ModuleDTO> getModuleListGroup() {
		return sqlSession.selectList(namespace + ".getModuleListGroup");
	}
	@Override
	public List<ModuleDTO> getModuleListProduction() {
		return sqlSession.selectList(namespace + ".getModuleListProduction");
	}
	@Override
	public List<ModuleDTO> getModuleListBuy() {
		return sqlSession.selectList(namespace + ".getModuleListBuy");
	}
	@Override
	public List<ModuleDTO> getModuleListSales() {
		return sqlSession.selectList(namespace + ".getModuleListSales");
	}
	@Override
	public List<ModuleDTO> getModuleListCar() {
		return sqlSession.selectList(namespace + ".getModuleListCar");
	}
	@Override
	public List<ModuleDTO> getModuleListAccount() {
		return sqlSession.selectList(namespace + ".getModuleListAccount");
	}
}
