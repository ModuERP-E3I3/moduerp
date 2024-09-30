package com.e3i3.moduerp.productionstock.dto;

public class ProductionStockInDTO {
    private String pStockInId;
    private String itemCode;
    private String pStockInDate;
    private String pStockPlace;
    private int pStockInQty;

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
    public String getpStockInDate() {
        return pStockInDate;
    }
    public void setpStockInDate(String pStockInDate) {
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
}