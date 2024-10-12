package com.e3i3.moduerp.attendancedocument.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.e3i3.moduerp.attendancedocument.model.dto.AttendanceDocument;

@Service
public interface AttendanceDocumentService {

    // 1. 임시 저장 없이 바로 근태 관리 요청 제출
    int insertAttendanceRequest(AttendanceDocument request);

    // 2. 근태 관리 요청 임시 저장
    int insertSavedAttendanceRequest(AttendanceDocument request);

    // 3. 상태 업데이트 (임시 저장에서 최종 제출로 변경, 승인여부도 대기로 승급)
    int updateRequestStatus(Map<String, Object> params);

    // 4. 특정 근태 관리 요청 ID로 조회
    AttendanceDocument selectAttendanceRequestById(String attendanceRequestId);

    // 5. 특정 사용자 UUID로 근태 관리 요청 조회
    List<AttendanceDocument> selectAttendanceRequestByUuid(String uuid);

    // 6. 특정 사업자번호(BizNumber)로 근태 관리 요청 조회
    List<AttendanceDocument> selectAttendanceRequestsByBizNumber(String bizNumber);

    // 7. 특정 사용자 UUID와 신청 유형(Application Type)으로 근태 관리 요청 조회
    List<AttendanceDocument> selectByUUIDAndApplicationType(Map<String, Object> params);

    // 8. 결재자 UUID로 대기 중인 근태 관리 요청 조회
    List<AttendanceDocument> selectPendingRequestsByApprover(String approver);

    // 9. 특정 사용자 UUID와 날짜 범위로 근태 관리 요청 조회
    List<AttendanceDocument> selectByUUIDAndDateRange(Map<String, Object> params);

    // 10. 전체 근태 관리 요청 조회
    List<AttendanceDocument> selectAllAttendanceRequests();

    // 11. 근태 관리 요청 업데이트
    int updateAttendanceRequest(AttendanceDocument request);

    // 12. 특정 UUID로 근태 관리 요청 삭제
    int deleteAttendanceRequestByUuid(String uuid);

    // 13. 특정 근태 관리 요청 ID로 삭제
    int deleteAttendanceRequestById(String attendanceRequestId);

	/*
	 * // 결재자가 결재 요청을 볼 수 있게 데이터를 필터링 void assignToApprover(String
	 * attendanceRequestId, String approverUUID);
	 */
}
