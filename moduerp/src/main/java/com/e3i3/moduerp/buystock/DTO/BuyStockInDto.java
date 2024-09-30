package com.e3i3.moduerp.buystock.DTO;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor 
@AllArgsConstructor
@Accessors(chain = true)
public class BuyStockInDto {
	private String bStockInId;
    private String itemCode;
    private String uuid;
    private String accountNo;
    private String bankId;
    private String departmentId;
    private String orderId;
    private Date bStockInDate;
    private Integer bStockInPlace;
    private Integer bStockInQty;
    private Integer bStockInPrice;
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
	public String getBankId() {
		return bankId;
	}
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public Date getbStockInDate() {
		return bStockInDate;
	}
	public void setbStockInDate(Date bStockInDate) {
		this.bStockInDate = bStockInDate;
	}
	public Integer getbStockInPlace() {
		return bStockInPlace;
	}
	public void setbStockInPlace(Integer bStockInPlace) {
		this.bStockInPlace = bStockInPlace;
	}
	public Integer getbStockInQty() {
		return bStockInQty;
	}
	public void setbStockInQty(Integer bStockInQty) {
		this.bStockInQty = bStockInQty;
	}
	public Integer getbStockInPrice() {
		return bStockInPrice;
	}
	public void setbStockInPrice(Integer bStockInPrice) {
		this.bStockInPrice = bStockInPrice;
	}
    
    
}
