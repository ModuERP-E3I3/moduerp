package com.e3i3.moduerp.salesstock.model.dto;

import java.util.Date;

public class SalesStockOutDTO {
    private String sStockOutId;
    private String itemCode;
    private Date sStockOutDate;
    private String sStockOutPlace;
    private int sStockOutQty;
    private String uuid;
    private Double sStockOutPrice;
    private Date sStockOutUpdate;
    private String oDirector;
    
    private String sStockOutStatus; // S_STOCK_OUT_STATUS
    private String panningStatus;   // PANNINT_STATUS

    
    
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
    public Double getsStockOutPrice() {
        return sStockOutPrice;
    }
    public void setsStockOutPrice(Double sStockOutPrice) {
        this.sStockOutPrice = sStockOutPrice;
    }
    public Date getsStockOutUpdate() {
        return sStockOutUpdate;
    }
    public void setsStockOutUpdate(Date sStockOutUpdate) {
        this.sStockOutUpdate = sStockOutUpdate;
    }
    public String getoDirector() {
        return oDirector;
    }
    public void setoDirector(String oDirector) {
        this.oDirector = oDirector;
    }

//    STATUS - - - - - - - - - - - - - - - - - - - - - - - - - -
    public String getsStockOutStatus() {
        return sStockOutStatus;
    }
    public void setsStockOutStatus(String sStockOutStatus) {
        this.sStockOutStatus = sStockOutStatus;
    }
    public String getPanningStatus() {
        return panningStatus;
    }
    public void setPanningStatus(String panningStatus) {
        this.panningStatus = panningStatus;
    }
}
