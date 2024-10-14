package com.e3i3.moduerp.qna.model.dto;

import java.sql.Timestamp;

public class QnaDto {
	private int qSeq;
	private String uuid;
	private String departmentId;
	private String qTitle;
	private String qContents;
	private String qStatus;
	private String isPublic;
	private String bizNumber;
	private String empName;
	private Timestamp qDate;
	
	public QnaDto() {
		super();
	}

	public QnaDto(int qSeq, String uuid, String departmentId, String qTitle, String qContents, String qStatus,
			String isPublic, String bizNumber, String empName) {
		super();
		this.qSeq = qSeq;
		this.uuid = uuid;
		this.departmentId = departmentId;
		this.qTitle = qTitle;
		this.qContents = qContents;
		this.qStatus = qStatus;
		this.isPublic = isPublic;
		this.bizNumber = bizNumber;
		this.empName = empName;
	}

	public int getqSeq() {
		return qSeq;
	}

	public void setqSeq(int qSeq) {
		this.qSeq = qSeq;
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

	public String getqTitle() {
		return qTitle;
	}

	public void setqTitle(String qTitle) {
		this.qTitle = qTitle;
	}

	public String getqContents() {
		return qContents;
	}

	public void setqContents(String qContents) {
		this.qContents = qContents;
	}

	public String getqStatus() {
		return qStatus;
	}

	public void setqStatus(String qStatus) {
		this.qStatus = qStatus;
	}

	public String getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(String isPublic) {
		this.isPublic = isPublic;
	}

	public String getBizNumber() {
		return bizNumber;
	}

	public void setBizNumber(String bizNumber) {
		this.bizNumber = bizNumber;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	@Override
	public String toString() {
		return "QnaDto [qSeq=" + qSeq + ", uuid=" + uuid + ", departmentId=" + departmentId + ", qTitle=" + qTitle
				+ ", qContents=" + qContents + ", qStatus=" + qStatus + ", isPublic=" + isPublic + ", bizNumber="
				+ bizNumber + ", empName=" + empName + "]";
	}

	public Timestamp getqDate() {
		return qDate;
	}

	public void setqDate(Timestamp qDate) {
		this.qDate = qDate;
	}

	
	
	
}
