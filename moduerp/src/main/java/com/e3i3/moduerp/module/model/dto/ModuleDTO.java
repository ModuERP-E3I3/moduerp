package com.e3i3.moduerp.module.model.dto;

import java.sql.Timestamp;

public class ModuleDTO {
	private String moduleId; // MODULE_ID
	private String moduleName; // MODULE_NAME
	private int modulePrice; // MODULE_PRICE
	private String moduleDesc; // MODULE_DESC
	private String moduleVer; // MODULE_VER
	private String moduleGrade; // MODULE_GRADE
	private char moduleUsage; // MODUKE_USAGE 
	private Timestamp createDate; // CREATE_DATE
	private Timestamp updateDate; // UPDATE_DATE
	private String moduleType;

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public int getModulePrice() {
		return modulePrice;
	}

	public void setModulePrice(int modulePrice) {
		this.modulePrice = modulePrice;
	}

	public String getModuleDesc() {
		return moduleDesc;
	}

	public void setModuleDesc(String moduleDesc) {
		this.moduleDesc = moduleDesc;
	}

	public String getModuleVer() {
		return moduleVer;
	}

	public void setModuleVer(String moduleVer) {
		this.moduleVer = moduleVer;
	}

	public String getModuleGrade() {
		return moduleGrade;
	}

	public void setModuleGrade(String moduleGrade) {
		this.moduleGrade = moduleGrade;
	}

	public char getModuleUsage() {
		return moduleUsage;
	}

	public void setModuleUsage(char moduleUsage) {
		this.moduleUsage = moduleUsage;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public Timestamp getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	public String getModuleType() {
		return moduleType;
	}

	public void setModuleType(String moduleType) {
		this.moduleType = moduleType;
	}

}
