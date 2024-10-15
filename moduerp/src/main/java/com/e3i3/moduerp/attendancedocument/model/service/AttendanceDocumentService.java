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
    
    // 14. 특정 근태의 승인여부를 '승인'으로 업데이트
    int updateApprovalStatus(String attendancerequestId);
   
    // 15. 특정 근태의 승인여부를 '반려'로 업데이트
    public int rejectRequest(String attendancerequestId);
    
    // 16. 특정 근태의 반려를 대기로 되돌리기
    public int undoRejectRequest(String attendancerequestId);
    
    /**
     * 특정 사용자(UUID)의 사용한 연차 개수를 반환합니다.
     *
     * @param uuid 사용자 UUID
     * @return 사용한 연차 개수
     */
    int countAnnualLeaveUsed(String uuid);
    
    int countDraftDocumentsByUUID(String uuid);
    
    int countPendingDocumentsByUUID(String uuid);
    
    int countApprovedDocumentsByUUID(String uuid);
    
    int countRejectedDocumentsByUUID(String uuid);
    
    int countDocumentsToApproveByUUID(String uuid);
}
