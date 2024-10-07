package com.e3i3.moduerp.carres.model.dto;

import java.sql.Timestamp;

public class CarresDto {
	private String carId;
	private String departmentId;
	private Timestamp reserveStartDate;
	private Timestamp reserveEndDate;
	private String useReason;
	private String uuid;
	private String empName;
	
	public CarresDto() {
		super();
	}

	public CarresDto(String carId, String departmentId, Timestamp reserveStartDate, Timestamp reserveEndDate,
			String useReason, String uuid, String empName) {
		super();
		this.carId = carId;
		this.departmentId = departmentId;
		this.reserveStartDate = reserveStartDate;
		this.reserveEndDate = reserveEndDate;
		this.useReason = useReason;
		this.uuid = uuid;
		this.empName = empName;
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

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	@Override
	public String toString() {
		return "CarresDto [carId=" + carId + ", departmentId=" + departmentId + ", reserveStartDate=" + reserveStartDate
				+ ", reserveEndDate=" + reserveEndDate + ", useReason=" + useReason + ", uuid=" + uuid + ", empName="
				+ empName + "]";
	}

	
	
	
	
	
}
