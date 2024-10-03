package com.e3i3.moduerp.carmgt.model.dto;

import java.sql.Timestamp;

public class CarmgtDto {
	private String carId;
	private String departmentId;
	private int paymentHistory;
	private String paymentPrice;
	private Timestamp paymentDate;
	private String paymentPlace;
	private String uuid;
	private String carModel;
	private String ownershipStatus;
	
	public CarmgtDto() {
		super();
	}

	public CarmgtDto(String carId, String departmentId, int paymentHistory, String paymentPrice, Timestamp paymentDate,
			String paymentPlace, String uuid, String carModel, String ownershipStatus) {
		super();
		this.carId = carId;
		this.departmentId = departmentId;
		this.paymentHistory = paymentHistory;
		this.paymentPrice = paymentPrice;
		this.paymentDate = paymentDate;
		this.paymentPlace = paymentPlace;
		this.uuid = uuid;
		this.carModel = carModel;
		this.ownershipStatus = ownershipStatus;
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

	public int getPaymentHistory() {
		return paymentHistory;
	}

	public void setPaymentHistory(int paymentHistory) {
		this.paymentHistory = paymentHistory;
	}

	public String getPaymentPrice() {
		return paymentPrice;
	}

	public void setPaymentPrice(String paymentPrice) {
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

	@Override
	public String toString() {
		return "CarmgtDto [carId=" + carId + ", departmentId=" + departmentId + ", paymentHistory=" + paymentHistory
				+ ", paymentPrice=" + paymentPrice + ", paymentDate=" + paymentDate + ", paymentPlace=" + paymentPlace
				+ ", uuid=" + uuid + ", carModel=" + carModel + ", ownershipStatus=" + ownershipStatus + "]";
	}

	
	
	
	
}
