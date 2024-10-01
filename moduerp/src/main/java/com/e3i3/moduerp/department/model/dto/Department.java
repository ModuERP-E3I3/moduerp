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
    private String departmentId;  // 부서코드 (Primary Key)
    private String departmentName;  // 부서명
    private String bizNumber;       // 회사의 사업자번호 (Foreign Key)
}