package com.e3i3.moduerp.item.model.dto;

import java.io.Serializable;

public class ItemDTO implements Serializable {
    private String itemName;
    private String bizNumber;
    // 추가적인 필드들

    // Getters and Setters
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getBizNumber() {
        return bizNumber;
    }

    public void setBizNumber(String bizNumber) {
        this.bizNumber = bizNumber;
    }
}
