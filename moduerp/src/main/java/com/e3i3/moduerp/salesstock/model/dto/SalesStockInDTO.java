package com.e3i3.moduerp.salesstock.model.dto;

import java.sql.Timestamp;

public class SalesStockInDTO {
    private String sStockInId;    // �Ǹ� ��� ID
    private String itemCode;      // ������ �ڵ�
    private Timestamp sStockInDate;   // �Ǹ� ��� �԰� ��¥
    private String sStockInPlace;   // �Ǹ� ��� ���
    private int sStockInQty;      // �Ǹ� ��� ����
    private String uuid;          // UUID
    private String accountNo; 	  // �ŷ�ó��ȣ

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

    public Timestamp getsStockInDate() {
        return sStockInDate;
    }

    public void setsStockInDate(Timestamp sStockInDate) {
        this.sStockInDate = sStockInDate;
    }

    public String getsStockInPlace() {
        return sStockInPlace;
    }

    public void setsStockInPlace(String sStockInPlace) {
        this.sStockInPlace = sStockInPlace;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }
    
    public int getsStockInQty() {
        return sStockInQty;
    }

    public void setsStockInQty(int sStockInQty) {
        this.sStockInQty = sStockInQty;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
