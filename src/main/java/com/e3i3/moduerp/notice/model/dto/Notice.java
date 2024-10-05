package com.e3i3.moduerp.notice.model.dto;

import java.sql.Date;

public class Notice implements java.io.Serializable {
	private static final long serialVersionUID = -8269302835859129601L;
	
	private int noticeSeq;
	private String uuID;
	private String departmentId;
	private String noticeTitle;
	private java.sql.Date writeDate;
	private String noticeBody;
	private String noticeImp;
	private int viewCnt;
	private String attachFile;
	
	public Notice() {
		super();
	}

	public Notice(int noticeSeq, String uuID, String departmentId, String noticeTitle, Date writeDate,
			String noticeBody, String noticeImp, int viewCnt, String attachFile) {
		super();
		this.noticeSeq = noticeSeq;
		this.uuID = uuID;
		this.departmentId = departmentId;
		this.noticeTitle = noticeTitle;
		this.writeDate = writeDate;
		this.noticeBody = noticeBody;
		this.noticeImp = noticeImp;
		this.viewCnt = viewCnt;
		this.attachFile = attachFile;
	}

	public int getNoticeSeq() {
		return noticeSeq;
	}

	public void setNoticeSeq(int noticeSeq) {
		this.noticeSeq = noticeSeq;
	}

	public String getUuID() {
		return uuID;
	}

	public void setUuID(String uuID) {
		this.uuID = uuID;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getNoticeTitle() {
		return noticeTitle;
	}

	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}

	public java.sql.Date getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(java.sql.Date writeDate) {
		this.writeDate = writeDate;
	}

	public String getNoticeBody() {
		return noticeBody;
	}

	public void setNoticeBody(String noticeBody) {
		this.noticeBody = noticeBody;
	}

	public String getNoticeImp() {
		return noticeImp;
	}

	public void setNoticeImp(String noticeImp) {
		this.noticeImp = noticeImp;
	}

	public int getViewCnt() {
		return viewCnt;
	}

	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}

	public String getAttachFile() {
		return attachFile;
	}

	public void setAttachFile(String attachFile) {
		this.attachFile = attachFile;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Notice [noticeSeq=" + noticeSeq + ", uuID=" + uuID + ", departmentId=" + departmentId + ", noticeTitle="
				+ noticeTitle + ", writeDate=" + writeDate + ", noticeBody=" + noticeBody + ", noticeImp=" + noticeImp
				+ ", viewCnt=" + viewCnt + ", attachFile=" + attachFile + "]";
	}
}

