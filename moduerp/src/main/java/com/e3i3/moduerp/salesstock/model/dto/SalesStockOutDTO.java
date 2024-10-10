package com.e3i3.moduerp.salesstock.model.dto;

import java.util.Date;

public class SalesStockOutDTO {
    private String sStockOutId;
    private String itemCode;
    private Date sStockOutDate;
    private String sStockOutPlace;
    private int sStockOutQty;
    private String uuid;
    
    public SalesStockOutDTO() {
		super();
	}
    
	public SalesStockOutDTO(String sStockOutId, String itemCode, Date sStockOutDate, String sStockOutPlace,
			int sStockOutQty, String uuid) {
		super();
		this.sStockOutId = sStockOutId;
		this.itemCode = itemCode;
		this.sStockOutDate = sStockOutDate;
		this.sStockOutPlace = sStockOutPlace;
		this.sStockOutQty = sStockOutQty;
		this.uuid = uuid;
	}

	// Getters and Setters
	public String getsStockOutId() {
		return sStockOutId;
	}
	public void setsStockOutId(String sStockOutId) {
		this.sStockOutId = sStockOutId;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public Date getsStockOutDate() {
		return sStockOutDate;
	}
	public void setsStockOutDate(Date sStockOutDate) {
		this.sStockOutDate = sStockOutDate;
	}
	public String getsStockOutPlace() {
		return sStockOutPlace;
	}
	public void setsStockOutPlace(String sStockOutPlace) {
		this.sStockOutPlace = sStockOutPlace;
	}
	public int getsStockOutQty() {
		return sStockOutQty;
	}
	public void setsStockOutQty(int sStockOutQty) {
		this.sStockOutQty = sStockOutQty;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

}
