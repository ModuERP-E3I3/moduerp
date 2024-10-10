package com.e3i3.moduerp.attendancerequest.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class AttendanceRequest {
    private String attendancerequestId;   // 신청 ID (Primary Key)
    private String uuid;                  // 신청자 UUID (Foreign Key: Employee)
    private String bizNumber;             // 사업자 번호 (Foreign Key: Company)
    private String applicationType;       // 신청 유형 (연차, 병가, 조퇴 등)
    private java.sql.Date startDate;      // 신청 시작일
    private java.sql.Date endDate;        // 신청 종료일
    private String startTime;             // 신청 시작 시간 (Optional)
    private String endTime;               // 신청 종료 시간 (Optional)
    private String reason;                // 신청 사유
    private String attachment;            // 첨부 파일 경로 (Optional)
    private String approver;              // 결재자 UUID
    private String remarks;               // 비고 (Optional)
    private String contact;               // 연락처 (Optional)
}
