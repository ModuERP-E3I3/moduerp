package com.e3i3.moduerp.employee.model.dto;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


<<<<<<< Updated upstream
@Data // getter, setter, toString, equals, hashCode 메소드를 자동으로 생성
@NoArgsConstructor // 기본생성자
@AllArgsConstructor // 모든 필드를 매개변수로 받는 생성자 생성
=======
@Data // getter, setter, toString, equals, hashCode 硫붿냼�뱶瑜� �옄�룞�쑝濡� �깮�꽦
@NoArgsConstructor // 湲곕낯�깮�꽦�옄
@AllArgsConstructor // 紐⑤뱺 �븘�뱶瑜� 留ㅺ컻蹂��닔濡� 諛쏅뒗 �깮�꽦�옄 �깮�꽦
>>>>>>> Stashed changes
@Accessors(chain = true) // company.setBizNumber("123").setCompanyName("MyCompany)
public class Employee implements Serializable{
	private static final long serialVersionUID = -3562883942862635847L;
	
	private UUID uuid; // uuid
<<<<<<< Updated upstream
	private String bizNumber; // 사업자번호
	private String approvalCode; // 승인코드
	private String departmentId; // 부서 ID
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
=======
	private String bizNumber; // �궗�뾽�옄踰덊샇
	private String approvalCode; // �듅�씤肄붾뱶
	private String departmentId; // 遺��꽌 ID
	private String jobId; // 吏곷Т ID
	private char privateAuthority; // �궗�꽕 沅뚰븳 �뿬遺�
	private String empNo; // �궗�썝踰덊샇
	private String empName; // �궗�썝紐�
	private String empEmail; // �궗�썝 �씠硫붿씪
	private String userPhone; // �궗�슜�옄 �쟾�솕踰덊샇
	private Date registrationDate; // �벑濡앹씪�옄
	private String profileImg; // �봽濡쒗븘 �씠誘몄��紐� (uuid_�봽濡쒗븘紐�)
	private Date updatedAt; // �닔�젙�씪�옄
	private char isDeleted; // �깉�눜 �뿬遺�
	private String lastLoginLocation; // 留덉��留� 濡쒓렇�씤 �쐞移�
	private String deletedExcuse; // �궘�젣 �궗�쑀
	private Date deletedAt; // �궘�젣�씪�옄
	private Date hireDate; // �엯�궗�씪�옄
	private Date quitDate; // �눜�궗�씪�옄
	private String reNum; // 二쇰�쇰쾲�샇
	private String address; // 二쇱냼
	private char isEmailChanged; // �씠硫붿씪 蹂�寃� �뿬遺�
	private String newEmpEmail; // �깉 �씠硫붿씪 二쇱냼
	private double remainingLeave; // �궓��� �쑕媛� �씪�닔
	private LocalTime contractStartTime; // 怨꾩빟�긽 異쒓렐 �떆媛�
	private LocalTime contractEndTime; // 怨꾩빟�긽 �눜洹� �떆媛�
	private UUID mgrUuid; // 愿�由ъ옄 uuid
}
>>>>>>> Stashed changes
