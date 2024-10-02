package com.e3i3.moduerp.salesstock.model.dto;

public class SalesStockInDTO {
    private String sStockInId;
    private String itemCode;
    private String accountNo; // 추가된 필드
    private String sStockInDate;
    private String sStockPlace;
    private int unitPrice;
    private int sStockInQty;

    // Getters and Setters
    public String getsStockInId() {
        return sStockInId;
    }
    public void setsStockInId(String sStockInId) {
        this.sStockInId = sStockInId;
    }
    public String getItemCode() {
        return itemCode;
    }
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }
    public String getAccountNo() { // 추가된 getter
        return accountNo;
    }
    public void setAccountNo(String accountNo) { // 추가된 setter
        this.accountNo = accountNo;
    }
    public String getsStockInDate() {
        return sStockInDate;
    }
    public void setsStockInDate(String sStockInDate) {
        this.sStockInDate = sStockInDate;
    }
    public String getsStockPlace() {
        return sStockPlace;
    }
    public void setsStockPlace(String sStockPlace) {
        this.sStockPlace = sStockPlace;
    }
    public int getsStockInQty() {
        return sStockInQty;
    }
    public void setsStockInQty(int sStockInQty) {
        this.sStockInQty = sStockInQty;
    }

    public int getUnitPrice() { // unitPrice 필드의 getter
        return unitPrice;
    }
    public void setUnitPrice(int unitPrice) { // unitPrice 필드의 setter
        this.unitPrice = unitPrice;
    }
}
