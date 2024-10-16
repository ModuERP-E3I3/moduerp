package com.e3i3.moduerp.company.model.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import com.e3i3.moduerp.department.model.dto.Department;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data // getter, setter, toString, equals, hashCode 메소드를 자동으로 생성
@NoArgsConstructor // 기본생성자
@AllArgsConstructor // 모든 필드를 매개변수로 받는 생성자 생성
@Accessors(chain = true) // company.setBizNumber("123").setCompanyName("MyCompany)
public class Company implements Serializable {
	private static final long serialVersionUID = -3757699675044058669L;
	private String bizNumber; // 사업자번호
	private String approvalCode; // 승인코드
	private String companyName;// 회사명
	private Timestamp createdAt; // 생성일시 java.sql.Timestamp
	private List<Department> departments; // 부서 리스트 (1:N 관계)
	private String cardExistence; // 카드 여부
	private String moduleGrades; // 모듈 
	
}