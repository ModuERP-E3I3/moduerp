package com.e3i3.moduerp.employee.model.dto;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@JsonInclude(JsonInclude.Include.NON_NULL)  // null 필드는 JSON에 포함되지 않음
@Data // getter, setter, toString, equals, hashCode 메소드를 자동으로 생성
@NoArgsConstructor // 기본생성자
@AllArgsConstructor // 모든 필드를 매개변수로 받는 생성자 생성
@Accessors(chain = true) // company.setBizNumber("123").setCompanyName("MyCompany)
public class EmployeeBasicInfo implements Serializable {
	private static final long serialVersionUID = 4869902505223740047L;
	
	private String empName; // 직원 이름
    private String empEmail; // 직원 이메일

 
}
