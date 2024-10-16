package com.e3i3.moduerp.financialclosing.model.dto;

import java.sql.Date;

public class FinClose implements java.io.Serializable {
	private static final long serialVersionUID = -8269302835859129601L;

	public String closingId; // CLOSING_ID VARCHAR2(50 BYTE)
	public String bizNumber; // BIZ_NUMBER	VARCHAR2(50 BYTE)
	public String bankId;// BANK_ID VARCHAR2(50 BYTE)
	public java.sql.Date startDate;// START_DATE DATE
	public java.sql.Date endDate;// END_DATE DATE
	public int totalSales;// TOTAL_SALES NUMBER(38,0)
	public int totalExpenses;// TOTAL_EXPENSES NUMBER(38,0)
	public int netProfit;// NET_PROFIT NUMBER(38,0)
	public java.sql.Date closingDate;// CLOSING_DATE DATE
	public String closingType;// CLOSING_TYPE VARCHAR2(10 BYTE)

	public FinClose() {
		super();
	}

	public FinClose(String closingId, String bizNumber, String bankId, Date startDate, Date endDate, int totalSales,
			int totalExpenses, int netProfit, Date closingDate, String closingType) {
		super();
		this.closingId = closingId;
		this.bizNumber = bizNumber;
		this.bankId = bankId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.totalSales = totalSales;
		this.totalExpenses = totalExpenses;
		this.netProfit = netProfit;
		this.closingDate = closingDate;
		this.closingType = closingType;
	}

	public String getClosingId() {
		return closingId;
	}

	public void setClosingId(String closingId) {
		this.closingId = closingId;
	}

	public String getBizNumber() {
		return bizNumber;
	}

	public void setBizNumber(String bizNumber) {
		this.bizNumber = bizNumber;
	}

	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	public java.sql.Date getStartDate() {
		return startDate;
	}

	public void setStartDate(java.sql.Date startDate) {
		this.startDate = startDate;
	}

	public java.sql.Date getEndDate() {
		return endDate;
	}

	public void setEndDate(java.sql.Date endDate) {
		this.endDate = endDate;
	}

	public int getTotalSales() {
		return totalSales;
	}

	public void setTotalSales(int totalSales) {
		this.totalSales = totalSales;
	}

	public int getTotalExpenses() {
		return totalExpenses;
	}

	public void setTotalExpenses(int totalExpenses) {
		this.totalExpenses = totalExpenses;
	}

	public int getNetProfit() {
		return netProfit;
	}

	public void setNetProfit(int netProfit) {
		this.netProfit = netProfit;
	}

	public java.sql.Date getClosingDate() {
		return closingDate;
	}

	public void setClosingDate(java.sql.Date closingDate) {
		this.closingDate = closingDate;
	}

	public String getClosingType() {
		return closingType;
	}

	public void setClosingType(String closingType) {
		this.closingType = closingType;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "FinClose [closingId=" + closingId + ", bizNumber=" + bizNumber + ", bankId=" + bankId + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", totalSales=" + totalSales + ", totalExpenses=" + totalExpenses
				+ ", netProfit=" + netProfit + ", closingDate=" + closingDate + ", closingType=" + closingType + "]";
	}
}
