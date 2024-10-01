package com.e3i3.moduerp.department.model.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Department implements Serializable {
    private static final long serialVersionUID = 1L;
    private String departmentId;  // �μ��ڵ� (Primary Key)
    private String departmentName;  // �μ���
    private String bizNumber;       // ȸ���� ����ڹ�ȣ (Foreign Key)
}