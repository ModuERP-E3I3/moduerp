package com.e3i3.moduerp.buystock.DTO;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor 
@AllArgsConstructor
@Accessors(chain = true)
public class BuyStockOutDto {
	private String bStockOutId;
    private String itemCode;
    private String uuid;
    private String accountNo;
    private String bankId;
    private String departmentId;
    private Date bStockOutDate;
    private String bStockOutPlace;
    private Integer bStockOutQty;
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
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	public Date getbStockOutDate() {
		return bStockOutDate;
	}
	public void setbStockOutDate(Date bStockOutDate) {
		this.bStockOutDate = bStockOutDate;
	}
	public String getbStockOutPlace() {
		return bStockOutPlace;
	}
	public void setbStockOutPlace(String bStockOutPlace) {
		this.bStockOutPlace = bStockOutPlace;
	}
	public Integer getbStockOutQty() {
		return bStockOutQty;
	}
	public void setbStockOutQty(Integer bStockOutQty) {
		this.bStockOutQty = bStockOutQty;
	}
    
    
}
