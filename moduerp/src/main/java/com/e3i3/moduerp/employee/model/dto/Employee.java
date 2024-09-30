package com.e3i3.moduerp.employee.model.dto;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


@Data // getter, setter, toString, equals, hashCode �޼ҵ带 �ڵ����� ����
@NoArgsConstructor // �⺻������
@AllArgsConstructor // ��� �ʵ带 �Ű������� �޴� ������ ����
@Accessors(chain = true) // company.setBizNumber("123").setCompanyName("MyCompany)
public class Employee implements Serializable{
	private static final long serialVersionUID = -3562883942862635847L;
	
	private UUID uuid; // uuid
	private String bizNumber; // ����ڹ�ȣ
	private String approvalCode; // �����ڵ�
	private String departmentId; // �μ� ID
	private String jobId; // ���� ID
	private char privateAuthority; // �缳 ���� ����
	private String empNo; // �����ȣ
	private String empName; // �����
	private String empEmail; // ��� �̸���
	private String userPhone; // ����� ��ȭ��ȣ
	private Date registrationDate; // �������
	private String profileImg; // ������ �̹����� (uuid_�����ʸ�)
	private Date updatedAt; // ��������
	private char isDeleted; // Ż�� ����
	private String lastLoginLocation; // ������ �α��� ��ġ
	private String deletedExcuse; // ���� ����
	private Date deletedAt; // ��������
	private Date hireDate; // �Ի�����
	private Date quitDate; // �������
	private String reNum; // �ֹι�ȣ
	private String address; // �ּ�
	private char isEmailChanged; // �̸��� ���� ����
	private String newEmpEmail; // �� �̸��� �ּ�
	private double remainingLeave; // ���� �ް� �ϼ�
	private LocalTime contractStartTime; // ���� ��� �ð�
	private LocalTime contractEndTime; // ���� ��� �ð�
	private UUID mgrUuid; // ������ uuid
}