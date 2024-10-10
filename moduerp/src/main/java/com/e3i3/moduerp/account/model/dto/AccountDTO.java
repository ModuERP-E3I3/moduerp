package com.e3i3.moduerp.account.model.dto;

import java.util.Date;
import java.util.List;

public class AccountDTO {
    private String accountNo;
    private String accountName;
    private String businessType;
    private List<String> items;
    private String bossName;
    private Double creditLimit;
    private String businessNumber;
    private String accountAddress;
    private String accountPhone;   // 추가
    private String postalCode;     // 추가
    private String email;          // 추가
    private String fax;            // 추가

    public AccountDTO() {
        super();
    }

    public AccountDTO(String accountNo, String accountName, String businessType, List<String> items, String bossName, Double creditLimit, String businessNumber, String accountAddress, String accountPhone, String postalCode, String email, String fax) {
        super();
        this.accountNo = accountNo;
        this.accountName = accountName;
        this.businessType = businessType;
        this.items = items;
        this.bossName = bossName;
        this.creditLimit = creditLimit;
        this.businessNumber = businessNumber;
        this.accountAddress = accountAddress;
        this.accountPhone = accountPhone;  // 추가
        this.postalCode = postalCode;      // 추가
        this.email = email;                // 추가
        this.fax = fax;                    // 추가
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

    public Double getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(Double creditLimit) {
        this.creditLimit = creditLimit;
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

    public String getPostalCode() {  // 추가
        return postalCode;
    }

    public void setPostalCode(String postalCode) {  // 추가
        this.postalCode = postalCode;
    }

    public String getEmail() {  // 추가
        return email;
    }

    public void setEmail(String email) {  // 추가
        this.email = email;
    }

    public String getFax() {  // 추가
        return fax;
    }

    public void setFax(String fax) {  // 추가
        this.fax = fax;
    }
}
