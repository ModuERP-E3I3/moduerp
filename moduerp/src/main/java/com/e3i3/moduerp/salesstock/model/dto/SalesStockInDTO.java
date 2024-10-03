package com.e3i3.moduerp.salesstock.model.dto;

import java.sql.Timestamp;

public class SalesStockInDTO {
    private String sStockInId;    // �Ǹ� ��� ID
    private String itemCode;      // ������ �ڵ�
    private Timestamp sStockInDate;   // �Ǹ� ��� �԰� ��¥
    private String sStockPlace;   // �Ǹ� ��� ���
    private int sStockInQty;      // �Ǹ� ��� ����
    private String uuid;          // UUID

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

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
