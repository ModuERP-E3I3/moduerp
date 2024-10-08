package com.e3i3.moduerp.workorder.model.dto;

import java.sql.Timestamp;
import java.util.Date;


public class WorkOrderDTO {
	private String orderNumber;
	private String itemCode;
	private String uuid;

	private Timestamp startDate;
	private Timestamp endDate;
	private String taskName;
	private int qty;
	private String progressStatus;
	private String workerTeam;
	private String worker;
	private String workPlace;
	private String wDirector;
	private String bizNumber;
	private String itemName;
	private Date endExDate;
	private Timestamp updateDate;
	

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public String getProgressStatus() {
		return progressStatus;
	}

	public void setProgressStatus(String progressStatus) {
		this.progressStatus = progressStatus;
	}

	public String getWorkerTeam() {
		return workerTeam;
	}

	public void setWorkerTeam(String workerTeam) {
		this.workerTeam = workerTeam;
	}

	public String getWorker() {
		return worker;
	}

	public void setWorker(String workers) {
		this.worker = workers;
	}

	public String getWorkPlace() {
		return workPlace;
	}

	public void setWorkPlace(String workPlace) {
		this.workPlace = workPlace;
	}

	public String getwDirector() {
		return wDirector;
	}

	public void setwDirector(String wDirector) {
		this.wDirector = wDirector;
	}

	public String getBizNumber() {
		return bizNumber;
	}

	public void setBizNumber(String bizNumber) {
		this.bizNumber = bizNumber;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Date getEndExDate() {
		return endExDate;
	}

	public void setEndExDate(Date endExDate) {
		this.endExDate = endExDate;
	}

	public Timestamp getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}

}
