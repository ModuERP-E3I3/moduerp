package com.e3i3.moduerp.quality.model.dto;

import java.sql.Timestamp;
import java.util.Date;

public class QualityControlDTO {

	private String inspecCode;
	private String orderNumber;
	private String uuid;
	private String bizNumber;
	private Timestamp startDate;
	private Timestamp endDate;
	private String inspecType;
	private String progressStatus;
	private int inspecQty;
	private Date endExDate;
	private String qDirector;
	private String taskName;
	private Timestamp updateDate;

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

	public String getBizNumber() {
		return bizNumber;
	}

	public void setBizNumber(String bizNumber) {
		this.bizNumber = bizNumber;
	}

	public Timestamp getStartDate() {
		return startDate;
	}

	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	public Timestamp getEndDate() {
		return endDate;
	}

	public void setEndDate(Timestamp endDate) {
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

	public int getInspecQty() {
		return inspecQty;
	}

	public void setInspecQty(int inspecQty) {
		this.inspecQty = inspecQty;
	}

	public Date getEndExDate() {
		return endExDate;
	}

	public void setEndExDate(Date endExDate) {
		this.endExDate = endExDate;
	}

	public String getqDirector() {
		return qDirector;
	}

	public void setqDirector(String qDirector) {
		this.qDirector = qDirector;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public Timestamp getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}

}
