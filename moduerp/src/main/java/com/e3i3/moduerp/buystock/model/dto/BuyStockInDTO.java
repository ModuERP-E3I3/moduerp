package com.e3i3.moduerp.buystock.model.dto;


import java.sql.Timestamp;

public class BuyStockInDTO {
    private String bStockInId;
    private String itemCode;
    private String uuid;
    private String accountNo;
    private String accountName;
    private Timestamp bStockInDate;
    private String bStockInPlace;
    private int bStockInQty;
    private String empName; 
    
    
	public String getbStockInId() {
		return bStockInId;
	}
	public void setbStockInId(String bStockInId) {
		this.bStockInId = bStockInId;
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
	public Timestamp getbStockInDate() {
		return bStockInDate;
	}
	public void setbStockInDate(Timestamp stockInDate) {
		this.bStockInDate = stockInDate;
	}
	public String getbStockInPlace() {
		return bStockInPlace;
	}
	public void setbStockInPlace(String bStockInPlace) {
		this.bStockInPlace = bStockInPlace;
	}
	public int getbStockInQty() {
		return bStockInQty;
	}
	public void setbStockInQty(int bStockInQty) {
		this.bStockInQty = bStockInQty;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}


    
    
    
}
