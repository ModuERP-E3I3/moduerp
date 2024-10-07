package com.e3i3.moduerp.department.model.dto;

import java.io.Serializable;
import java.util.UUID;

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

	private String departmentId; // 부서코드 (Primary Key)
	private String departmentName; // 부서명
	private String bizNumber; // 회사의 사업자번호 (Foreign Key)

	// 특정 패턴을 이용한 부서 ID 생성 메소드
	public Department generateCustomDepartmentId(int sequenceNumber) {
		this.departmentId = String.format("DPT-%04d", sequenceNumber); 
		return this;
	}
}