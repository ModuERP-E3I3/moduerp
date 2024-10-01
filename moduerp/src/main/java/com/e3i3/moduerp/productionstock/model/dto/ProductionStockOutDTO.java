package com.e3i3.moduerp.productionstock.model.dto;

import java.util.Date;

public class ProductionStockOutDTO {
    private String pStockOutId;
    private String itemCode;
    private Date pStockOutDate;
    private String pStockOutPlace;
    private int pStockOutQty;
    private String uuid;
    
    
    
    public ProductionStockOutDTO() {
		super();
	}
    
    
	public ProductionStockOutDTO(String pStockOutId, String itemCode, Date pStockOutDate, String pStockOutPlace,
			int pStockOutQty, String uuid) {
		super();
		this.pStockOutId = pStockOutId;
		this.itemCode = itemCode;
		this.pStockOutDate = pStockOutDate;
		this.pStockOutPlace = pStockOutPlace;
		this.pStockOutQty = pStockOutQty;
		this.uuid = uuid;
	}


	// Getters and Setters
	public String getpStockOutId() {
		return pStockOutId;
	}
	public void setpStockOutId(String pStockOutId) {
		this.pStockOutId = pStockOutId;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public Date getpStockOutDate() {
		return pStockOutDate;
	}
	public void setpStockOutDate(Date pStockOutDate) {
		this.pStockOutDate = pStockOutDate;
	}
	public String getpStockOutPlace() {
		return pStockOutPlace;
	}
	public void setpStockOutPlace(String pStockOutPlace) {
		this.pStockOutPlace = pStockOutPlace;
	}
	public int getpStockOutQty() {
		return pStockOutQty;
	}
	public void setpStockOutQty(int pStockOutQty) {
		this.pStockOutQty = pStockOutQty;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

   
   
}
