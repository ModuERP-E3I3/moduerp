package com.e3i3.moduerp.billingInfo.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CardInfo {

    @JsonProperty("issuerCode")
    private String issuerCode;

    @JsonProperty("acquirerCode")
    private String acquirerCode;

    @JsonProperty("number")
    private String cardNumber;

    @JsonProperty("cardType")
    private String cardType;

    @JsonProperty("ownerType")
    private String ownerType;

    // Getters and Setters
    public String getIssuerCode() { return issuerCode; }
    public void setIssuerCode(String issuerCode) { this.issuerCode = issuerCode; }

    public String getAcquirerCode() { return acquirerCode; }
    public void setAcquirerCode(String acquirerCode) { this.acquirerCode = acquirerCode; }

    public String getCardNumber() { return cardNumber; }
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }

    public String getCardType() { return cardType; }
    public void setCardType(String cardType) { this.cardType = cardType; }

    public String getOwnerType() { return ownerType; }
    public void setOwnerType(String ownerType) { this.ownerType = ownerType; }

    @Override
    public String toString() {
        return "CardInfo{" +
               "issuerCode='" + issuerCode + '\'' +
               ", acquirerCode='" + acquirerCode + '\'' +
               ", cardNumber='" + cardNumber + '\'' +
               ", cardType='" + cardType + '\'' +
               ", ownerType='" + ownerType + '\'' +
               '}';
    }
}
