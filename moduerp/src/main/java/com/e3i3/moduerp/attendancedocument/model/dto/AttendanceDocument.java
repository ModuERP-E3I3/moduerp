package com.e3i3.moduerp.attendancedocument.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class AttendanceDocument {
	private String attendancerequestId; // 신청 ID (Primary Key)
	private String uuid; // 신청자 UUID (Foreign Key: Employee)
	private String requesterName; // 신청자 이름
	private String bizNumber; // 사업자 번호 (Foreign Key: Company)
	private String applicationType; // 신청 유형 (연차, 병가, 조퇴 등)
	private String isApproved; // 승인여부 ('Y', 'N')
	private String status; // 임시저장인지 제출완료인지 ('saved', 'submitted')
	private String startDate; // 신청 시작일 (String으로 변경)
	private String endDate; // 신청 종료일 (String으로 변경)
	private String startTime; // 신청 시작 시간 (Optional)
	private String endTime; // 신청 종료 시간 (Optional)
	private String reason; // 신청 사유 (Optional)
	private String attachment; // 첨부 파일 경로 (Optional)
	private String approver; // 결재자 UUID
	private String approverName; // 결재자 이름 추가
	private String remarks; // 비고 (Optional)
	private String contact; // 연락처 (Optional)

	public Date getStartTimeAsDate() {
		if (startTime == null) {
			return null;
		}
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
			return formatter.parse(startTime);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Date getEndTimeAsDate() {
		if (endTime == null) {
			return null;
		}
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
			return formatter.parse(endTime);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

}
