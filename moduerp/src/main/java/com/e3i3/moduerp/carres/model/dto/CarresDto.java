package com.e3i3.moduerp.carres.model.dto;

import java.sql.Timestamp;

public class CarresDto {
	private String carReserveCode;
	private String carId;
	private String empName;
	private String departmentId;
	private String carModel;
	private String carNum;
	private String bizNumber;
	private Timestamp reserveStartDate;
	private Timestamp reserveEndDate;
	private String useReason;
	private String uuid;
	private String drivingStatus;
	
	public CarresDto() {
		super();
	}

	public CarresDto(String carReserveCode, String carId, String empName, String departmentId, String carModel,
			String carNum, String bizNumber, Timestamp reserveStartDate, Timestamp reserveEndDate, String useReason,
			String uuid) {
		super();
		this.carReserveCode = carReserveCode;
		this.carId = carId;
		this.empName = empName;
		this.departmentId = departmentId;
		this.carModel = carModel;
		this.carNum = carNum;
		this.bizNumber = bizNumber;
		this.reserveStartDate = reserveStartDate;
		this.reserveEndDate = reserveEndDate;
		this.useReason = useReason;
		this.uuid = uuid;
	}

	public String getCarReserveCode() {
		return carReserveCode;
	}

	public void setCarReserveCode(String carReserveCode) {
		this.carReserveCode = carReserveCode;
	}

	public String getCarId() {
		return carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getCarModel() {
		return carModel;
	}

	public void setCarModel(String carModel) {
		this.carModel = carModel;
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

	public Timestamp getReserveStartDate() {
		return reserveStartDate;
	}

	public void setReserveStartDate(Timestamp reserveStartDate) {
		this.reserveStartDate = reserveStartDate;
	}

	public Timestamp getReserveEndDate() {
		return reserveEndDate;
	}

	public void setReserveEndDate(Timestamp reserveEndDate) {
		this.reserveEndDate = reserveEndDate;
	}

	public String getUseReason() {
		return useReason;
	}

	public void setUseReason(String useReason) {
		this.useReason = useReason;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	@Override
	public String toString() {
		return "CarresDto [carReserveCode=" + carReserveCode + ", carId=" + carId + ", empName=" + empName
				+ ", departmentId=" + departmentId + ", carModel=" + carModel + ", carNum=" + carNum + ", bizNumber="
				+ bizNumber + ", reserveStartDate=" + reserveStartDate + ", reserveEndDate=" + reserveEndDate
				+ ", useReason=" + useReason + ", uuid=" + uuid + "]";
	}

	public String getDrivingStatus() {
		return drivingStatus;
	}

	public void setDrivingStatus(String drivingStatus) {
		this.drivingStatus = drivingStatus;
	}

	

	
	
	
	
	
}
