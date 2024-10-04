package com.e3i3.moduerp.purchaseorders.model.dto;

import java.util.Date;
import java.util.List;

public class PurchaseordersDTO {
    private String purchaseOrderNo;
    private String supplierName;
    private String orderStatus;
    private List<String> orderedItems;
    private String purchaserName;

    public PurchaseordersDTO() {
        super();
    }

    public PurchaseordersDTO(String purchaseOrderNo, String supplierName, String orderStatus, List<String> orderedItems, String purchaserName) {
        super();
        this.purchaseOrderNo = purchaseOrderNo;
        this.supplierName = supplierName;
        this.orderStatus = orderStatus;
        this.orderedItems = orderedItems;
        this.purchaserName = purchaserName;
    }

    // Getters and Setters
    public String getPurchaseOrderNo() {
        return purchaseOrderNo;
    }

    public void setPurchaseOrderNo(String purchaseOrderNo) {
        this.purchaseOrderNo = purchaseOrderNo;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<String> getOrderedItems() {
        return orderedItems;
    }

    public void setOrderedItems(List<String> orderedItems) {
        this.orderedItems = orderedItems;
    }

    public String getPurchaserName() {
        return purchaserName;
    }

    public void setPurchaserName(String purchaserName) {
        this.purchaserName = purchaserName;
    }
}
