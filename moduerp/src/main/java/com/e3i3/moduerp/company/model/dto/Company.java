package com.e3i3.moduerp.company.model.dto;

import java.io.Serializable;
import java.security.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data //getter, setter, toString, equals, hashCode 메소드를 자동으로 생성
@NoArgsConstructor //기본생성자
@AllArgsConstructor //모든 필드를 매개변수로 받는 생성자 생성
@Accessors(chain=true) // company.setBizNumber("123").setCompanyName("MyCompany)
public class Company implements Serializable {
	private static final long serialVersionUID = -3757699675044058669L;
	/*
		biz_number	VARCHAR2(50 BYTE)
		approval_code	VARCHAR2(100 BYTE)
		department_id	VARCHAR2(250 BYTE)
		company_name	VARCHAR2(50 BYTE)
		created_at	TIMESTAMP(6)
	 */
	private String bizNumber; //사업자번호
	private String approvalCode; //승인코드
	private String departmentId; //부서ID
	private String companyName;//회사명
	private Timestamp createdAt; //생성일시
	
}
