package com.e3i3.moduerp.car.model.dto;

public class CarDto {
	private String carId;
	private String carModel;
	private String ownershipStatus;
	private String carNum;
	private String bizNumber;
	private String YN;
	private String imagePath;
	

	public CarDto() {
		super();
	}

	public CarDto(String carId, String carModel, String ownershipStatus, String carNum) {
		super();
		this.carId = carId;
		this.carModel = carModel;
		this.ownershipStatus = ownershipStatus;
		this.carNum = carNum;
	}

	public String getCarId() {
		return carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
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
		return "Car [carId=" + carId + ", carModel=" + carModel + ", ownershipStatus=" + ownershipStatus + "]";
	}

	public String getCarNum() {
		return carNum;
	}

	public void setCarNum(String carNum) {
		this.carNum = carNum;
	}

	public String getBizNumber() {
		return bizNumber;
	}

	public void setBizNumber(String bizNumber) {
		this.bizNumber = bizNumber;
	}

	public String getYN() {
		return YN;
	}

	public void setYN(String yN) {
		YN = yN;
	}
	
	public String getImagePath() {
		return imagePath;
	}
	
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	
	
	
}