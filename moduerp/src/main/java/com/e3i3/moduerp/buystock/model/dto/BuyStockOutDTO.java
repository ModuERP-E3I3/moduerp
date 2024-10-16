package com.e3i3.moduerp.buystock.model.dto;

import java.sql.Date;
import java.sql.Timestamp;

public class BuyStockOutDTO {

	private String bStockOutId;
    private String itemCode;
    private String uuid;
    private Timestamp bStockOutDate;
    private String bStockOutPlace;
    private int bStockOutQty;
    private Timestamp bStockOutUpdate;
    private String oDirector;
    private Double bStockOutPrice;
    
    
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
	public Timestamp getbStockOutDate() {
		return bStockOutDate;
	}
	public void setbStockOutDate(Timestamp bStockOutDate) {
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
	public Timestamp getbStockOutUpdate() {
		return bStockOutUpdate;
	}
	public void setbStockOutUpdate(Timestamp bStockOutUpdate) {
		this.bStockOutUpdate = bStockOutUpdate;
	}
	public String getoDirector() {
		return oDirector;
	}
	public void setoDirector(String oDirector) {
		this.oDirector = oDirector;
	}
	public Double getbStockOutPrice() {
		return bStockOutPrice;
	}
	public void setbStockOutPrice(Double bStockOutPrice) {
		this.bStockOutPrice = bStockOutPrice;
	}
  
    


	
	

  	
	
}
