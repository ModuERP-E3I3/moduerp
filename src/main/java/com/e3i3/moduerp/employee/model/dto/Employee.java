package com.e3i3.moduerp.employee.model.dto;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


@Data // getter, setter, toString, equals, hashCode 메소드를 자동으로 생성
@NoArgsConstructor // 기본생성자
@AllArgsConstructor // 모든 필드를 매개변수로 받는 생성자 생성
@Accessors(chain = true) // company.setBizNumber("123").setCompanyName("MyCompany)
public class Employee implements Serializable{
	private static final long serialVersionUID = -3562883942862635847L;
	
	private UUID uuid; // uuid
	private String bizNumber; // 사업자번호
	private String approvalCode; // 승인코드
	private String deptId; // 부서 ID
	private String jobId; // 직무 ID
	private char privateAuthority; // 사설 권한 여부
	private String empNo; // 사원번호
	private String empName; // 사원명
	private String empEmail; // 사원 이메일
	private String userPhone; // 사용자 전화번호
	private Date registrationDate; // 등록일자
	private String profileImg; // 프로필 이미지명 (uuid_프로필명)
	private Date updatedAt; // 수정일자
	private char isDeleted; // 탈퇴 여부
	private String lastLoginLocation; // 마지막 로그인 위치
	private String deletedExcuse; // 삭제 사유
	private Date deletedAt; // 삭제일자
	private Date hireDate; // 입사일자
	private Date quitDate; // 퇴사일자
	private String reNum; // 주민번호
	private String address; // 주소
	private char isEmailChanged; // 이메일 변경 여부
	private String newEmpEmail; // 새 이메일 주소
	private double remainingLeave; // 남은 휴가 일수
	private LocalTime contractStartTime; // 계약상 출근 시간
	private LocalTime contractEndTime; // 계약상 퇴근 시간
	private UUID mgrUuid; // 관리자 uuid
}
