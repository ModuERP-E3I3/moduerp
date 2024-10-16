package com.e3i3.moduerp.answer.model.dto;

import java.sql.Timestamp;

public class AnswerDto {
	private String aSeq;
	private int qSeq;
	private String uuid;
	private String aTitle;
	private String aContents;
	private Timestamp aDate;
	
	public AnswerDto() {
		super();
	}

	public AnswerDto(String aSeq, int qSeq, String uuid, String aTitle, String aContents, Timestamp aDate) {
		super();
		this.aSeq = aSeq;
		this.qSeq = qSeq;
		this.uuid = uuid;
		this.aTitle = aTitle;
		this.aContents = aContents;
		this.aDate = aDate;
	}

	public String getaSeq() {
		return aSeq;
	}

	public void setaSeq(String aSeq) {
		this.aSeq = aSeq;
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

	public String getaTitle() {
		return aTitle;
	}

	public void setaTitle(String aTitle) {
		this.aTitle = aTitle;
	}

	public String getaContents() {
		return aContents;
	}

	public void setaContents(String aContents) {
		this.aContents = aContents;
	}

	public Timestamp getaDate() {
		return aDate;
	}

	public void setaDate(Timestamp aDate) {
		this.aDate = aDate;
	}

	@Override
	public String toString() {
		return "AnswerDto [aSeq=" + aSeq + ", qSeq=" + qSeq + ", uuid=" + uuid + ", aTitle=" + aTitle + ", aContents="
				+ aContents + ", aDate=" + aDate + "]";
	}

	
	
	
}
