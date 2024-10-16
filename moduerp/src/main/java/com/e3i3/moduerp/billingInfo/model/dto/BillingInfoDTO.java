package com.e3i3.moduerp.billingInfo.model.dto;

import java.sql.Timestamp;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)  // 알 수 없는 필드 무시
public class BillingInfoDTO {
    private String cardBillingId; // 카드 빌링 ID
    private String customerKey; // 고객 키
    private String billingKey; // 발급된 빌링 키
    private String cardCompany; // 카드사 이름
    private String cardNumber; // 마스킹된 카드 번호
    private Timestamp authenticatedAt; // 인증 시각
    private Timestamp createdAt; // 레코드 생성 시각
    private String bizNumber; // 사업자 번호

    // 중첩된 Card 정보를 위한 객체
    @JsonProperty("card")
    private CardInfo card;

    // Getters and Setters
    public String getCardBillingId() {
        return cardBillingId;
    }

    public void setCardBillingId(String cardBillingId) {
        this.cardBillingId = cardBillingId;
    }

    public String getCustomerKey() {
        return customerKey;
    }

    public void setCustomerKey(String customerKey) {
        this.customerKey = customerKey;
    }

    public String getBillingKey() {
        return billingKey;
    }

    public void setBillingKey(String billingKey) {
        this.billingKey = billingKey;
    }

    public String getCardCompany() {
        return cardCompany;
    }

    public void setCardCompany(String cardCompany) {
        this.cardCompany = cardCompany;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Timestamp getAuthenticatedAt() {
        return authenticatedAt;
    }

    public void setAuthenticatedAt(Timestamp authenticatedAt) {
        this.authenticatedAt = authenticatedAt;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getBizNumber() {
        return bizNumber;
    }

    public void setBizNumber(String bizNumber) {
        this.bizNumber = bizNumber;
    }

    public CardInfo getCard() {
        return card;
    }

    public void setCard(CardInfo card) {
        this.card = card;
    }

    @Override
    public String toString() {
        return "BillingInfoDTO{" +
               "cardBillingId='" + cardBillingId + '\'' +
               ", customerKey='" + customerKey + '\'' +
               ", billingKey='" + billingKey + '\'' +
               ", cardCompany='" + cardCompany + '\'' +
               ", cardNumber='" + cardNumber + '\'' +
               ", authenticatedAt=" + authenticatedAt +
               ", createdAt=" + createdAt +
               ", bizNumber='" + bizNumber + '\'' +
               ", card=" + card +
               '}';
    }
}
