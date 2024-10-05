package com.e3i3.moduerp.account.model.dto;

import java.util.Date;
import java.util.List;

public class AccountDTO {
    private String accountNo;
    private String accountName;
    private String businessType;
    private List<String> items;
    private String bossName;

    public AccountDTO() {
        super();
    }

    public AccountDTO(String accountNo, String accountName, String businessType, List<String> items, String bossName) {
        super();
        this.accountNo = accountNo;
        this.accountName = accountName;
        this.businessType = businessType;
        this.items = items;
        this.bossName = bossName;
    }

    // Getters and Setters
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

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }

    public String getBossName() {
        return bossName;
    }

    public void setBossName(String bossName) {
        this.bossName = bossName;
    }
}
