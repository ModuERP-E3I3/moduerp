package com.e3i3.moduerp.workorder.model.dto;

import java.util.Date;

public class WorkOrderDTO {
    private String orderNumber;
    private String itemCode;
    private String uuid;
    private String departmentId;
    private Date startDate;
    private Date endDate;
    private String taskName;
    private int qty;
    private String progressStatus;
    private String workerTeam;
    private String worker;
    private String workPlace;
    private String wDirector;

    public WorkOrderDTO() {
		super();
	}
    public WorkOrderDTO(String orderNumber, String itemCode, String uuid, String departmentId, Date startDate,
			Date endDate, String taskName, int qty, String progressStatus, String workerTeam, String worker,
			String workPlace, String wDirector) {
		super();
		this.orderNumber = orderNumber;
		this.itemCode = itemCode;
		this.uuid = uuid;
		this.departmentId = departmentId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.taskName = taskName;
		this.qty = qty;
		this.progressStatus = progressStatus;
		this.workerTeam = workerTeam;
		this.worker = worker;
		this.workPlace = workPlace;
		this.wDirector = wDirector;
	}

	

	public String getwDirector() {
		return wDirector;
	}
	public void setwDirector(String wDirector) {
		this.wDirector = wDirector;
	}
	// Getters and Setters
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

    public void setWorker(String worker) {
        this.worker = worker;
    }

   

    public String getWorkPlace() {
		return workPlace;
	}
	public void setWorkPlace(String workPlace) {
		this.workPlace = workPlace;
	}
	public String getWDirector() {
        return wDirector;
    }

    public void setWDirector(String wDirector) {
        this.wDirector = wDirector;
    }

    
    

    
    
}
