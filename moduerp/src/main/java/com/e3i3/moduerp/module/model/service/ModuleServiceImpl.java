package com.e3i3.moduerp.module.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e3i3.moduerp.module.model.dao.ModuleDAO;
import com.e3i3.moduerp.module.model.dto.ModuleDTO;

@Service
public class ModuleServiceImpl implements ModuleService {
	@Autowired
	private ModuleDAO moduleDAO;

	@Override
	public List<ModuleDTO> getActiveModules() {
		return moduleDAO.selectActiveModules();
	}

	@Override
	public List<ModuleDTO> getModulesN() {
		return moduleDAO.getModulesN();
	}

	@Override
	public void insertModule(ModuleDTO module) {
		moduleDAO.insertModule(module);

	}

	@Override
	public ModuleDTO getModuleDetailByModuleId(String moduleId) {
		return moduleDAO.getModuleDetailByModuleId(moduleId);
	}

	@Override
	public void updateModule(ModuleDTO module) {
		moduleDAO.updateModule(module);

	}

	@Override
	public void deleteModule(String moduleId) {
		moduleDAO.deleteModule(moduleId);

	}

	@Override
	public List<ModuleDTO> getModuleListGroup() {
		return moduleDAO.getModuleListGroup();
	}

	@Override
	public List<ModuleDTO> getModuleListProduction() {
		return moduleDAO.getModuleListProduction();
	}

	@Override
	public List<ModuleDTO> getModuleListBuy() {
		return moduleDAO.getModuleListBuy();
	}

	@Override
	public List<ModuleDTO> getModuleListSales() {
		return moduleDAO.getModuleListSales();
	}

	@Override
	public List<ModuleDTO> getModuleListCar() {
		return moduleDAO.getModuleListCar();
	}

	@Override
	public List<ModuleDTO> getModuleListAccount() {
		return moduleDAO.getModuleListAccount();
	}

	@Override
	public List<ModuleDTO> getModuleListEmpMgt() {
		return moduleDAO.getModuleListEmpMgt();
	}

	@Override
	public List<String> getModuleGradesByIds(List<String> moduleIds) {
		return moduleDAO.selectModuleGradesByIds(moduleIds);
	}

	@Override
	public List<ModuleDTO> selectModulesByGrades(List<String> moduleGrades) {
		return moduleDAO.selectModulesByGrades(moduleGrades);
	}

	@Override
	public List<ModuleDTO> getModulesByGrades(List<String> moduleGrades) {
		return moduleDAO.selectModuleDataByGrades(moduleGrades);
	}

	@Override
	public List<String> selectModuleNamesByGradesList(List<String> gradesList) {
		return moduleDAO.selectModuleNamesByGradesList(gradesList);
	}

}
