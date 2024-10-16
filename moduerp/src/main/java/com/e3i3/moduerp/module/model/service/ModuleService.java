package com.e3i3.moduerp.module.model.service;

import java.util.List;

import com.e3i3.moduerp.module.model.dto.ModuleDTO;

public interface ModuleService {

	List<ModuleDTO> getActiveModules();

	List<ModuleDTO> getModulesN();

	void insertModule(ModuleDTO module);

	ModuleDTO getModuleDetailByModuleId(String moduleId);

	void updateModule(ModuleDTO module);

	void deleteModule(String moduleId);

	List<ModuleDTO> getModuleListGroup();

	List<ModuleDTO> getModuleListProduction();

	List<ModuleDTO> getModuleListBuy();

	List<ModuleDTO> getModuleListSales();

	List<ModuleDTO> getModuleListCar();

	List<ModuleDTO> getModuleListAccount();

	List<String> getModuleGradesByIds(List<String> moduleIds);

	List<ModuleDTO> selectModulesByGrades(List<String> moduleGrades);

	List<ModuleDTO> getModulesByGrades(List<String> moduleGrades);
}
