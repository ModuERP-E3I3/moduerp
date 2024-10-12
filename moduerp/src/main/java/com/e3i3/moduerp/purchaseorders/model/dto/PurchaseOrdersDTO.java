package com.e3i3.moduerp.purchaseorders.model.dto;

import java.util.Date;
import java.util.List;

public class PurchaseOrdersDTO {
    private String orderId;
    private String itemCode;
    private int quantity;
    private double supplyPrice;
    private String accountNo;
    private String deliveryDate;
    private String mgrName;
    
    public PurchaseOrdersDTO() {
        super();
    }

    public PurchaseOrdersDTO(String orderId, String itemCode, int quantity, double supplyPrice, String accountNo, String deliveryDate, String mgrName) {
        super();
        this.orderId = orderId;
        this.itemCode = itemCode;
        this.quantity = quantity;
        this.supplyPrice = supplyPrice;
        this.accountNo = accountNo;
        this.deliveryDate = deliveryDate;
        this.mgrName = mgrName;
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
}
