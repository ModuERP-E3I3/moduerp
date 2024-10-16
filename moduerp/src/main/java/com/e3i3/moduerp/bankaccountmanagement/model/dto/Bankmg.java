package com.e3i3.moduerp.bankaccountmanagement.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bankmg implements java.io.Serializable {
	private static final long serialVersionUID = -8269302835859129601L;
	
	public String bankId;	//	BANK_ID	VARCHAR2(50 BYTE)
	public String bankName;	//	BANK_NAME	VARCHAR2(50 BYTE)
	public String bankNumber;//	BANK_NUMBER	VARCHAR2(50 BYTE)
	public String bankHolder;//	BANK_HOLDER	VARCHAR2(50 BYTE)
	public String bizNumber; // BIZ_NUMBER	VARCHAR2(50 BYTE)
	public String remarks; // REMARKS	VARCHAR2(255 BYTE)
	public int balance; // BALANCE	NUMBER(38,0)
}