package com.e3i3.moduerp.purchaseorders.model.dto;

import java.util.Date;

public class PurchaseOrdersDTO {
    private String orderId;
    private int quantity;
    private double supplyPrice;
    private String accountNo;
    private String accountName;
    private String deliveryDate;
    private String mgrName;
    private String puItemName;
    private String bizNumber;  // 추가된 필드

    public PurchaseOrdersDTO() {
        super();
    }

    public PurchaseOrdersDTO(String orderId, int quantity, 
    		double supplyPrice, String accountNo, String accountName, 
    		String deliveryDate, String mgrName, String bizNumber, String puItemName
    		) {
        super();
        this.orderId = orderId;
        this.quantity = quantity;
        this.supplyPrice = supplyPrice;
        this.accountNo = accountNo;
        this.accountName = accountName;
        this.deliveryDate = deliveryDate;
        this.mgrName = mgrName;
        this.puItemName = puItemName;
        this.bizNumber = bizNumber; 
    }

    // Getters and Setters
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
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

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getMgrName() {
        return mgrName;
    }

    public void setMgrName(String mgrName) {
        this.mgrName = mgrName;
    }
    
    public String getPuItemName() {
        return puItemName;
    }

    public void setPuItemName(String puItemName) {
        this.puItemName = puItemName;
    }

    public String getBizNumber() {  // 추가된 getter
        return bizNumber;
    }

    public void setBizNumber(String bizNumber) {  // 추가된 setter
        this.bizNumber = bizNumber;
    }
}
