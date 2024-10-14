package com.e3i3.moduerp.delivery.model.dto;

import java.sql.Date;

public class DeliveryDTO {
   private String deliveryId;
   private String itemCode;
   private String spec;
   private String receiverId;
   private String address;
   private String deliveryType;
   private String key;
   private Date deliveryDate;
   private Date inDate;
   private Date upDate;
   private String recipient;
   private String waybill;
   private String deliveryCompany;
   
   
public String getDeliveryId() {
	return deliveryId;
}
public void setDeliveryId(String deliveryId) {
	this.deliveryId = deliveryId;
}
public String getItemCode() {
	return itemCode;
}
public void setItemCode(String itemCode) {
	this.itemCode = itemCode;
}
public String getSpec() {
	return spec;
}
public void setSpec(String spec) {
	this.spec = spec;
}
public String getReceiverId() {
	return receiverId;
}
public void setReceiverId(String receiverId) {
	this.receiverId = receiverId;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public String getDeliveryType() {
	return deliveryType;
}
public void setDeliveryType(String deliveryType) {
	this.deliveryType = deliveryType;
}
public String getKey() {
	return key;
}
public void setKey(String key) {
	this.key = key;
}
public Date getDeliveryDate() {
	return deliveryDate;
}
public void setDeliveryDate(Date deliveryDate) {
	this.deliveryDate = deliveryDate;
}
public Date getInDate() {
	return inDate;
}
public void setInDate(Date inDate) {
	this.inDate = inDate;
}
public Date getUpDate() {
	return upDate;
}
public void setUpDate(Date upDate) {
	this.upDate = upDate;
}
public String getRecipient() {
	return recipient;
}
public void setRecipient(String recipient) {
	this.recipient = recipient;
}
public String getWaybill() {
	return waybill;
}
public void setWaybill(String waybill) {
	this.waybill = waybill;
}
public String getDeliveryCompany() {
	return deliveryCompany;
}
public void setDeliveryCompany(String deliveryCompany) {
	this.deliveryCompany = deliveryCompany;
}
   
   
}
