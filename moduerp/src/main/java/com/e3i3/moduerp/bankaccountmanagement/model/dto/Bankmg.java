package com.e3i3.moduerp.bankaccountmanagement.model.dto;

public class Bankmg implements java.io.Serializable {
	private static final long serialVersionUID = -8269302835859129601L;
	
	public String bankId;	//	BANK_ID	VARCHAR2(50 BYTE)
	public String bankName;	//	BANK_NAME	VARCHAR2(50 BYTE)
	public String bankNumber;//	BANK_NUMBER	VARCHAR2(50 BYTE)
	public String bankHolder;//	BANK_HOLDER	VARCHAR2(50 BYTE)
	public int balance;//	BALANCE	NUMBER(38,0)
	public java.sql.Date transactionDate;	//	TRANSACTION_DATE	DATE
	public String transactionType;	//	TRANSACTION_TYPE	VARCHAR2(10 BYTE)
	public int transactionPrice;	//	TRANSACTION_PRICE	NUMBER(38,0)
	public String bizNumber; // BIZ_NUMBER	VARCHAR2(50 BYTE)
	public Bankmg() {
		super();
	}

	public Bankmg(String bankId, String bankName, String bankNumber, String bankHolder, int balance,
			java.sql.Date transactionDate, String transactionType, int transactionPrice, String bizNumber) {
		super();
		this.bankId = bankId;
		this.bankName = bankName;
		this.bankNumber = bankNumber;
		this.bankHolder = bankHolder;
		this.balance = balance;
		this.transactionDate = transactionDate;
		this.transactionType = transactionType;
		this.transactionPrice = transactionPrice;
		this.bizNumber = bizNumber;
	}

	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankNumber() {
		return bankNumber;
	}

	public void setBankNumber(String bankNumber) {
		this.bankNumber = bankNumber;
	}

	public String getBankHolder() {
		return bankHolder;
	}

	public void setBankHolder(String bankHolder) {
		this.bankHolder = bankHolder;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public java.sql.Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(java.sql.Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public int getTransactionPrice() {
		return transactionPrice;
	}

	public void setTransactionPrice(int transactionPrice) {
		this.transactionPrice = transactionPrice;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	public String getBizNumber() {
		return bizNumber;
	}

	public void setBizNumber(String bizNumber) {
		this.bizNumber = bizNumber;
	}
	
	@Override
	public String toString() {
		return "Bankmg [bankId=" + bankId + ", bankName=" + bankName + ", bankNumber=" + bankNumber + ", bankHolder="
				+ bankHolder + ", balance=" + balance + ", transactionDate=" + transactionDate + ", transactionType="
				+ transactionType + ", transactionPrice=" + transactionPrice + ", bizNumber=" + bizNumber + "]";
	}
}