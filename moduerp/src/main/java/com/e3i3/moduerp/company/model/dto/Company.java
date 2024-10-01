package com.e3i3.moduerp.company.model.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import com.e3i3.moduerp.department.model.dto.Department;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data // getter, setter, toString, equals, hashCode �޼ҵ带 �ڵ����� ����
@NoArgsConstructor // �⺻������
@AllArgsConstructor // ��� �ʵ带 �Ű������� �޴� ������ ����
@Accessors(chain = true) // company.setBizNumber("123").setCompanyName("MyCompany)
public class Company implements Serializable {
	private static final long serialVersionUID = -3757699675044058669L;
	/*
	 * biz_number VARCHAR2(50 BYTE) approval_code VARCHAR2(100 BYTE) company_name
	 * VARCHAR2(50 BYTE) created_at TIMESTAMP(6)
	 */
	private String bizNumber; // ����ڹ�ȣ
	private String approvalCode; // �����ڵ�
	private String companyName;// ȸ���
	private Timestamp createdAt; // �����Ͻ� java.sql.Timestamp
	private List<Department> departments; // �μ� ����Ʈ (1:N ����)
}