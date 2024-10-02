package com.e3i3.moduerp.productionstock.model.dto;

import java.sql.Timestamp;

public class ProductionStockInDTO {
    private String pStockInId;
    private String itemCode;
    private Timestamp pStockInDate;  // 필드 추가
    private String pStockPlace;
    private int pStockInQty;
    private String uuid;

    // Getters and Setters
    public String getPStockInId() {
        return pStockInId;
    }

    public String getpStockInId() {
		return pStockInId;
	}

	public void setpStockInId(String pStockInId) {
		this.pStockInId = pStockInId;
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

	public void setPStockInId(String pStockInId) {
        this.pStockInId = pStockInId;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public Timestamp getPStockInDate() {  // getter 추가
        return pStockInDate;
    }

    public void setPStockInDate(Timestamp pStockInDate) {  // setter 추가
        this.pStockInDate = pStockInDate;
    }

    public String getPStockPlace() {
        return pStockPlace;
    }

    public void setPStockPlace(String pStockPlace) {
        this.pStockPlace = pStockPlace;
    }

    public int getPStockInQty() {
        return pStockInQty;
    }

    public void setPStockInQty(int pStockInQty) {
        this.pStockInQty = pStockInQty;
    }

    public String getUUID() {
        return uuid;
    }

    public void setUUID(String UUID) {
        this.uuid = UUID;
    }
}
