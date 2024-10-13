package com.e3i3.moduerp.attendancedocument.model.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.e3i3.moduerp.attendancedocument.model.dto.AttendanceDocument;

@Repository
public class AttendanceDocumentDao {

	private static final String NAMESPACE = "AttendanceRequestMapper.";

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	// 1. 임시 저장 없이 바로 근태 관리 요청 제출
	public int insertAttendanceRequest(AttendanceDocument request) {
		return sqlSessionTemplate.insert(NAMESPACE + "insertAttendanceRequest", request);
	}

	// 2. 근태 관리 요청 임시 저장
	public int insertSavedAttendanceRequest(AttendanceDocument request) {
		return sqlSessionTemplate.insert(NAMESPACE + "insertSavedAttendanceRequest", request);
	}

	// 3. 상태 업데이트 (임시 저장에서 최종 제출로 변경, 승인여부도 대기로 승급)
	public int updateRequestStatus(Map<String, Object> params) {
		return sqlSessionTemplate.update(NAMESPACE + "updateRequestStatus", params);
	}

	// 4. 특정 근태 관리 요청 ID로 조회
	public AttendanceDocument selectAttendanceRequestById(String attendanceRequestId) {
		return sqlSessionTemplate.selectOne(NAMESPACE + "selectAttendanceRequestById", attendanceRequestId);
	}

	// 5. 특정 사용자 UUID로 근태 관리 요청 조회
	public List<AttendanceDocument> selectAttendanceRequestByUuid(String uuid) {
		return sqlSessionTemplate.selectList(NAMESPACE + "selectAttendanceRequestByUuid", uuid);
	}

	// 6. 특정 사업자번호(BizNumber)로 근태 관리 요청 조회
	public List<AttendanceDocument> selectAttendanceRequestsByBizNumber(String bizNumber) {
		return sqlSessionTemplate.selectList(NAMESPACE + "selectAttendanceRequestsByBizNumber", bizNumber);
	}

	// 7. 특정 사용자 UUID와 신청 유형(Application Type)으로 근태 관리 요청 조회
	public List<AttendanceDocument> selectByUUIDAndApplicationType(Map<String, Object> params) {
		return sqlSessionTemplate.selectList(NAMESPACE + "selectByUUIDAndApplicationType", params);
	}

	// 8. 결재자 UUID로 대기 중인 근태 관리 요청 조회
	public List<AttendanceDocument> selectPendingRequestsByApprover(String approver) {
		return sqlSessionTemplate.selectList(NAMESPACE + "selectPendingRequestsByApprover", approver);
	}

	// 9. 특정 사용자 UUID와 날짜 범위로 근태 관리 요청 조회
	public List<AttendanceDocument> selectByUUIDAndDateRange(Map<String, Object> params) {
		return sqlSessionTemplate.selectList(NAMESPACE + "selectByUUIDAndDateRange", params);
	}

	// 10. 전체 근태 관리 요청 조회
	public List<AttendanceDocument> selectAllAttendanceRequests() {
		return sqlSessionTemplate.selectList(NAMESPACE + "selectAllAttendanceRequests");
	}

	// 11. 근태 관리 요청 업데이트
	public int updateAttendanceRequest(AttendanceDocument request) {
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

	// 14. 특정 근태의 승인여부를 '승인'으로 업데이트
	public int updateApprovalStatus(String attendancerequestId) {
		return sqlSessionTemplate.update(NAMESPACE + "updateApprovalStatus", attendancerequestId);
	}
	
	// 15. 특정 근태의 승인여부를 '반려'로 업데이트
	public int rejectRequest(String attendancerequestId) {
		return sqlSessionTemplate.update(NAMESPACE + "rejectRequest", attendancerequestId);
	}
	
	// 16. 특정 근태의 반려를 취소하는 메소드
	public int undoRejectRequest(String attendancerequestId) {
		return sqlSessionTemplate.update(NAMESPACE + "undoRejectRequest", attendancerequestId);
	}
	
}
