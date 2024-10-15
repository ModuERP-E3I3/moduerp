package com.e3i3.moduerp.pay.model.dto;

import java.sql.Timestamp;

public class PayDTO {
	private String payId;
	private String uuid;
	private int payPrice;
	private char payStatus;
	private Timestamp firstPayDate;
	private String bizNumber;
	private String paymentItem;
	private String paymentRequestDay;

	public String getPayId() {
		return payId;
	}

	public void setPayId(String payId) {
		this.payId = payId;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public int getPayPrice() {
		return payPrice;
	}

	public void setPayPrice(int payPrice) {
		this.payPrice = payPrice;
	}

	public char getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(char payStatus) {
		this.payStatus = payStatus;
	}

	public Timestamp getFirstPayDate() {
		return firstPayDate;
	}

	public void setFirstPayDate(Timestamp firstPayDate) {
		this.firstPayDate = firstPayDate;
	}

	public String getBizNumber() {
		return bizNumber;
	}

	public void setBizNumber(String bizNumber) {
		this.bizNumber = bizNumber;
	}

	public String getPaymentItem() {
		return paymentItem;
	}

	public void setPaymentItem(String paymentItem) {
		this.paymentItem = paymentItem;
	}

	public String getPaymentRequestDay() {
		return paymentRequestDay;
	}

	public void setPaymentRequestDay(String paymentRequestDay) {
		this.paymentRequestDay = paymentRequestDay;
	}

}
