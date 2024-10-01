package com.e3i3.moduerp.quality.model.dto;

import java.util.Date;

public class QualityControlDTO {
	private String inspecCode;
	private String orderNumber;
	private String uuid;
	private String departmentId;
	private Date startDate;
	private Date endDate;
	private String inspecType;
	private String progressStatus;
	private String inspecResult;
	private int inspecQty;

	public QualityControlDTO() {
		super();
	}

	public QualityControlDTO(String inspecCode, String orderNumber, String uuid, String departmentId, Date startDate,
			Date endDate, String inspecType, String progressStatus, String inspecResult, int inspecQty) {
		super();
		this.inspecCode = inspecCode;
		this.orderNumber = orderNumber;
		this.uuid = uuid;
		this.departmentId = departmentId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.inspecType = inspecType;
		this.progressStatus = progressStatus;
		this.inspecResult = inspecResult;
		this.inspecQty = inspecQty;
	}

	public String getInspecCode() {
		return inspecCode;
	}

	public void setInspecCode(String inspecCode) {
		this.inspecCode = inspecCode;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getInspecType() {
		return inspecType;
	}

	public void setInspecType(String inspecType) {
		this.inspecType = inspecType;
	}

	public String getProgressStatus() {
		return progressStatus;
	}

	public void setProgressStatus(String progressStatus) {
		this.progressStatus = progressStatus;
	}

	public String getInspecResult() {
		return inspecResult;
	}

	public void setInspecResult(String inspecResult) {
		this.inspecResult = inspecResult;
	}

	public int getInspecQty() {
		return inspecQty;
	}

	public void setInspecQty(int inspecQty) {
		this.inspecQty = inspecQty;
	}
}
