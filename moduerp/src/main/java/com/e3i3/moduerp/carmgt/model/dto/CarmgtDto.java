package com.e3i3.moduerp.carmgt.model.dto;

import java.sql.Timestamp;

public class CarmgtDto {
	private String paymentHistoryCode;	
	private String carId;
	private String departmentId;
	private String paymentHistory;
	private int paymentPrice;
	private Timestamp paymentDate;
	private String paymentPlace;
	private String uuid;
	private String carModel;
	private String carNum;
	private String ownershipStatus;
	private String bizNumber;
	private String empName;
	
	
	public CarmgtDto() {
		super();
	}


	public CarmgtDto(String paymentHistoryCode, String carId, String departmentId, String paymentHistory,
			int paymentPrice, Timestamp paymentDate, String paymentPlace, String uuid, String carModel,
			String ownershipStatus, String bizNumber) {
		super();
		this.paymentHistoryCode = paymentHistoryCode;
		this.carId = carId;
		this.departmentId = departmentId;
		this.paymentHistory = paymentHistory;
		this.paymentPrice = paymentPrice;
		this.paymentDate = paymentDate;
		this.paymentPlace = paymentPlace;
		this.uuid = uuid;
		this.carModel = carModel;
		this.ownershipStatus = ownershipStatus;
		this.bizNumber = bizNumber;
	}


	public String getPaymentHistoryCode() {
		return paymentHistoryCode;
	}


	public void setPaymentHistoryCode(String paymentHistoryCode) {
		this.paymentHistoryCode = paymentHistoryCode;
	}


	public String getCarId() {
		return carId;
	}


	public void setCarId(String carId) {
		this.carId = carId;
	}


	public String getDepartmentId() {
		return departmentId;
	}


	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}


	public String getPaymentHistory() {
		return paymentHistory;
	}


	public void setPaymentHistory(String paymentHistory) {
		this.paymentHistory = paymentHistory;
	}


	public int getPaymentPrice() {
		return paymentPrice;
	}


	public void setPaymentPrice(int paymentPrice) {
		this.paymentPrice = paymentPrice;
	}


	public Timestamp getPaymentDate() {
		return paymentDate;
	}


	public void setPaymentDate(Timestamp paymentDate) {
		this.paymentDate = paymentDate;
	}


	public String getPaymentPlace() {
		return paymentPlace;
	}


	public void setPaymentPlace(String paymentPlace) {
		this.paymentPlace = paymentPlace;
	}


	public String getUuid() {
		return uuid;
	}


	public void setUuid(String uuid) {
		this.uuid = uuid;
	}


	public String getCarModel() {
		return carModel;
	}


	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}


	public String getOwnershipStatus() {
		return ownershipStatus;
	}


	public void setOwnershipStatus(String ownershipStatus) {
		this.ownershipStatus = ownershipStatus;
	}


	public String getBizNumber() {
		return bizNumber;
	}


	public void setBizNumber(String bizNumber) {
		this.bizNumber = bizNumber;
	}
	

	public String getCarNum() {
		return carNum;
	}


	public void setCarNum(String carNum) {
		this.carNum = carNum;
	}


	@Override
	public String toString() {
		return "CarmgtDto [paymentHistoryCode=" + paymentHistoryCode + ", carId=" + carId + ", departmentId="
				+ departmentId + ", paymentHistory=" + paymentHistory + ", paymentPrice=" + paymentPrice
				+ ", paymentDate=" + paymentDate + ", paymentPlace=" + paymentPlace + ", uuid=" + uuid + ", carModel="
				+ carModel + ", ownershipStatus=" + ownershipStatus + ", bizNumber=" + bizNumber + "]";
	}


	public String getEmpName() {
		return empName;
	}


	public void setEmpName(String empName) {
		this.empName = empName;
	}
	
	
	
	
	
	
}
