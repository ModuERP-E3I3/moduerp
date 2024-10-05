package com.e3i3.moduerp.item.model.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class ItemDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String itemCode;
	private String itemName;
	private String itemDesc;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	private Integer stockIn;
	private Integer stockOut;
	private Integer stock;
	private String stockPlace;
	private Double inPrice;
	private List<String> itemList;
	private String bizNumber;
	private Double outPrice; // 추가된 필드
	private String stockOutPlace; // 추가된 필드
	private Timestamp createdOutAt; // 추가된 필드
	private Timestamp updateOutAt; // 추가된 필드

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemDesc() {
		return itemDesc;
	}

	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Integer getStockIn() {
		return stockIn;
	}

	public void setStockIn(Integer stockIn) {
		this.stockIn = stockIn;
	}

	public Integer getStockOut() {
		return stockOut;
	}

	public void setStockOut(Integer stockOut) {
		this.stockOut = stockOut;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public String getStockPlace() {
		return stockPlace;
	}

	public void setStockPlace(String stockPlace) {
		this.stockPlace = stockPlace;
	}

	public Double getInPrice() {
		return inPrice;
	}

	public void setInPrice(Double inPrice) {
		this.inPrice = inPrice;
	}

	public List<String> getItemList() {
		return itemList;
	}

	public void setItemList(List<String> itemList) {
		this.itemList = itemList;
	}

	public String getBizNumber() {
		return bizNumber;
	}

	public void setBizNumber(String bizNumber) {
		this.bizNumber = bizNumber;
	}

	public Double getOutPrice() {
		return outPrice;
	}

	public void setOutPrice(Double outPrice) {
		this.outPrice = outPrice;
	}

	public String getStockOutPlace() {
		return stockOutPlace;
	}

	public void setStockOutPlace(String stockOutPlace) {
		this.stockOutPlace = stockOutPlace;
	}

	public Timestamp getcreatedOutAt() {
		return createdOutAt;
	}

	public void setCreatedOutAt(Timestamp createdOutAt) {
		this.createdOutAt = createdOutAt;
	}

	public Timestamp getUpdateOutAt() {
		return updateOutAt;
	}

	public void setUpdateOutAt(Timestamp updateOutAt) {
		this.updateOutAt = updateOutAt;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
