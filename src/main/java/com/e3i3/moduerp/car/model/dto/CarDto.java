package com.e3i3.moduerp.car.model.dto;

public class CarDto {
	private String carId;
	private String carModel;
	private String ownershipStatus;
	
	public CarDto() {
		super();
	}

	public CarDto(String carId, String carModel, String ownershipStatus) {
		super();
		this.carId = carId;
		this.carModel = carModel;
		this.ownershipStatus = ownershipStatus;
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
	
	
	
}