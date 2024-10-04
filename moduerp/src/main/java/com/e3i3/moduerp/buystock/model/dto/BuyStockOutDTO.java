package com.e3i3.moduerp.buystock.model.dto;

import java.sql.Date;

public class BuyStockOutDTO {

	private String bStockOutId;
    private String itemCode;
    private String uuid;
    private String accountNo;
    private String bankId;
    private java.sql.Date bStockOutDate;
    private String bStockOutPlace;
    private int bStockOutQty;
    
    
    
    
	public BuyStockOutDTO() {
		super();
	}
	
	
	
	public BuyStockOutDTO(String bStockOutId, String itemCode, String uuid, String accountNo, String bankId,
			Date bStockOutDate, String bStockOutPlace, int bStockOutQty) {
		super();
		this.bStockOutId = bStockOutId;
		this.itemCode = itemCode;
		this.uuid = uuid;
		this.accountNo = accountNo;
		this.bankId = bankId;
		this.bStockOutDate = bStockOutDate;
		this.bStockOutPlace = bStockOutPlace;
		this.bStockOutQty = bStockOutQty;
	}



	public String getbStockOutId() {
		return bStockOutId;
	}
	public void setbStockOutId(String bStockOutId) {
		this.bStockOutId = bStockOutId;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getBankId() {
		return bankId;
	}
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	public java.sql.Date getbStockOutDate() {
		return bStockOutDate;
	}
	public void setbStockOutDate(java.sql.Date bStockOutDate) {
		this.bStockOutDate = bStockOutDate;
	}
	public String getbStockOutPlace() {
		return bStockOutPlace;
	}
	public void setbStockOutPlace(String bStockOutPlace) {
		this.bStockOutPlace = bStockOutPlace;
	}
	public int getbStockOutQty() {
		return bStockOutQty;
	}
	public void setbStockOutQty(int bStockOutQty) {
		this.bStockOutQty = bStockOutQty;
	}

	
    
    
	
	
}
