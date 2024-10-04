package com.e3i3.moduerp.productionstock.model.dto;

import java.sql.Timestamp;

public class ProductionStockInDTO {
	private String pStockInId;
	private String itemCode;
	private Timestamp pStockInDate; 
	private String pStockPlace;
	private int pStockInQty;
	private String uuid;

	// Getters and Setters

	public String getpStockInId() {
		return pStockInId;
	}

	public void setpStockInId(String pStockInId) {
		this.pStockInId = pStockInId;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public Timestamp getpStockInDate() {
		return pStockInDate;
	}

	public void setpStockInDate(Timestamp pStockInDate) {
		this.pStockInDate = pStockInDate;
	}

	public String getpStockPlace() {
		return pStockPlace;
	}

	public void setpStockPlace(String pStockPlace) {
		this.pStockPlace = pStockPlace;
	}

	
	public int getpStockInQty() {
		return pStockInQty;
	}

	public void setpStockInQty(int pStockInQty) {
		this.pStockInQty = pStockInQty;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

}
