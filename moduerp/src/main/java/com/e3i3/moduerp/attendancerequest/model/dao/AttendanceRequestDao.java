package com.e3i3.moduerp.attendancerequest.model.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.e3i3.moduerp.attendancerequest.model.dto.AttendanceRequest;

@Repository
public class AttendanceRequestDao {

    private static final String NAMESPACE = "AttendanceRequestMapper.";

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    // 1. 임시 저장 없이 바로 근태 관리 요청 제출
    public int insertAttendanceRequest(AttendanceRequest request) {
        return sqlSessionTemplate.insert(NAMESPACE + "insertAttendanceRequest", request);
    }

    // 2. 근태 관리 요청 임시 저장
    public int insertSavedAttendanceRequest(AttendanceRequest request) {
        return sqlSessionTemplate.insert(NAMESPACE + "insertSavedAttendanceRequest", request);
    }

    // 3. 상태 업데이트 (임시 저장에서 최종 제출로 변경)
    public int updateRequestStatus(Map<String, Object> params) {
        return sqlSessionTemplate.update(NAMESPACE + "updateRequestStatus", params);
    }

    // 4. 특정 근태 관리 요청 ID로 조회
    public AttendanceRequest selectAttendanceRequestById(String attendanceRequestId) {
        return sqlSessionTemplate.selectOne(NAMESPACE + "selectAttendanceRequestById", attendanceRequestId);
    }

    // 5. 특정 사용자 UUID로 근태 관리 요청 조회
    public List<AttendanceRequest> selectAttendanceRequestByUuid(String uuid) {
        return sqlSessionTemplate.selectList(NAMESPACE + "selectAttendanceRequestByUuid", uuid);
    }

    // 6. 특정 사업자번호(BizNumber)로 근태 관리 요청 조회
    public List<AttendanceRequest> selectAttendanceRequestsByBizNumber(String bizNumber) {
        return sqlSessionTemplate.selectList(NAMESPACE + "selectAttendanceRequestsByBizNumber", bizNumber);
    }

    // 7. 특정 사용자 UUID와 신청 유형(Application Type)으로 근태 관리 요청 조회
    public List<AttendanceRequest> selectByUUIDAndApplicationType(Map<String, Object> params) {
        return sqlSessionTemplate.selectList(NAMESPACE + "selectByUUIDAndApplicationType", params);
    }

    // 8. 결재자 UUID로 대기 중인 근태 관리 요청 조회
    public List<AttendanceRequest> selectPendingRequestsByApprover(String approver) {
        return sqlSessionTemplate.selectList(NAMESPACE + "selectPendingRequestsByApprover", approver);
    }

    // 9. 특정 사용자 UUID와 날짜 범위로 근태 관리 요청 조회
    public List<AttendanceRequest> selectByUUIDAndDateRange(Map<String, Object> params) {
        return sqlSessionTemplate.selectList(NAMESPACE + "selectByUUIDAndDateRange", params);
    }

    // 10. 전체 근태 관리 요청 조회
    public List<AttendanceRequest> selectAllAttendanceRequests() {
        return sqlSessionTemplate.selectList(NAMESPACE + "selectAllAttendanceRequests");
    }

    // 11. 근태 관리 요청 업데이트
    public int updateAttendanceRequest(AttendanceRequest request) {
        return sqlSessionTemplate.update(NAMESPACE + "updateAttendanceRequest", request);
    }

    // 12. 특정 UUID로 근태 관리 요청 삭제
    public int deleteAttendanceRequestByUuid(String uuid) {
        return sqlSessionTemplate.delete(NAMESPACE + "deleteAttendanceRequestByUuid", uuid);
    }

    // 13. 특정 근태 관리 요청 ID로 삭제
    public int deleteAttendanceRequestById(String attendanceRequestId) {
        return sqlSessionTemplate.delete(NAMESPACE + "deleteAttendanceRequestById", attendanceRequestId);
    }
}
