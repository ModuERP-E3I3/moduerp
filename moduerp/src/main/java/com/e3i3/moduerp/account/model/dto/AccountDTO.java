package com.e3i3.moduerp.account.model.dto;

import java.util.Date;
import java.util.List;

public class AccountDTO {
    private String accountNo;
    private String accountName;
    private String businessType;
    private List<String> items;
    private String bossName;
    private String businessNumber;
    private String accountAddress;
    private String accountPhone;  
    private String email;         
    private String bizNumber;
    
    public AccountDTO() {
        super();
    }

    public AccountDTO(String accountNo, String accountName, String businessType, List<String> items, String bossName, String businessNumber, String accountAddress, String accountPhone, String email) {
        super();
        this.accountNo = accountNo;
        this.accountName = accountName;
        this.businessType = businessType;
        this.items = items;
        this.bossName = bossName;
        this.businessNumber = businessNumber;
        this.accountAddress = accountAddress;
        this.accountPhone = accountPhone; 
        this.email = email;                  
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

    public String getBusinessNumber() {
        return businessNumber;
    }

    public void setBusinessNumber(String businessNumber) {
        this.businessNumber = businessNumber;
    }

    public String getAccountAddress() {
        return accountAddress;
    }

    public void setAccountAddress(String accountAddress) {
        this.accountAddress = accountAddress;
    }

    public String getAccountPhone() {  // 추가
        return accountPhone;
    }

    public void setAccountPhone(String accountPhone) {  // 추가
        this.accountPhone = accountPhone;
    }


    public String getEmail() {  // 추가
        return email;
    }

    public void setEmail(String email) {  // 추가
        this.email = email;
    }

    public String getBizNumber() {
        return bizNumber;
    }

    public void setBizNumber(String bizNumber) {
        this.bizNumber = bizNumber;
    }
    
}
