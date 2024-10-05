package com.e3i3.moduerp.purchaseorders.model.dto;

public class PurchaseordersDTO {
    private String orderId;        
    private String itemCode;       
    private String accountNo;      
    private int quantity;          
    private double supplyPrice;    

    public PurchaseordersDTO() {
        super();
    }

    public PurchaseordersDTO(String orderId, String itemCode, String accountNo, int quantity, double supplyPrice) {
        super();
        this.orderId = orderId;
        this.itemCode = itemCode;
        this.accountNo = accountNo;
        this.quantity = quantity;
        this.supplyPrice = supplyPrice;
    }

    // Getters and Setters
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSupplyPrice() {
        return supplyPrice;
    }

    public void setSupplyPrice(double supplyPrice) {
        this.supplyPrice = supplyPrice;
    }
}
