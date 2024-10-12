package com.e3i3.moduerp.attendancedocument.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e3i3.moduerp.attendancedocument.model.dao.AttendanceDocumentDao;
import com.e3i3.moduerp.attendancedocument.model.dto.AttendanceDocument;

@Service
public class AttendanceDocumentServiceImpl implements AttendanceDocumentService {

	@Autowired
	private AttendanceDocumentDao attendanceRequestDao;

	// 1. 임시 저장 없이 바로 근태 관리 요청 제출
	@Override
	public int insertAttendanceRequest(AttendanceDocument request) {
		return attendanceRequestDao.insertAttendanceRequest(request);
	}

	// 2. 근태 관리 요청 임시 저장
	@Override
	public int insertSavedAttendanceRequest(AttendanceDocument request) {
		return attendanceRequestDao.insertSavedAttendanceRequest(request);
	}

	// 3. 상태 업데이트 (임시 저장에서 최종 제출, 'N'에서 '대기'로 변경)
	@Override
	public int updateRequestStatus(Map<String, Object> params) {
		return attendanceRequestDao.updateRequestStatus(params);
	}

	// 4. 특정 근태 관리 요청 ID로 조회
	@Override
	public AttendanceDocument selectAttendanceRequestById(String attendanceRequestId) {
		return attendanceRequestDao.selectAttendanceRequestById(attendanceRequestId);
	}

	// 5. 특정 사용자 UUID로 근태 관리 요청 조회
	@Override
	public List<AttendanceDocument> selectAttendanceRequestByUuid(String uuid) {
		return attendanceRequestDao.selectAttendanceRequestByUuid(uuid);
	}

	// 6. 특정 사업자번호(BizNumber)로 근태 관리 요청 조회
	@Override
	public List<AttendanceDocument> selectAttendanceRequestsByBizNumber(String bizNumber) {
		return attendanceRequestDao.selectAttendanceRequestsByBizNumber(bizNumber);
	}

	// 7. 특정 사용자 UUID와 신청 유형(Application Type)으로 근태 관리 요청 조회
	@Override
	public List<AttendanceDocument> selectByUUIDAndApplicationType(Map<String, Object> params) {
		return attendanceRequestDao.selectByUUIDAndApplicationType(params);
	}

	// 8. 결재자 UUID로 대기 중인 근태 관리 요청 조회
	@Override
	public List<AttendanceDocument> selectPendingRequestsByApprover(String approver) {
		return attendanceRequestDao.selectPendingRequestsByApprover(approver);
	}

	// 9. 특정 사용자 UUID와 날짜 범위로 근태 관리 요청 조회
	@Override
	public List<AttendanceDocument> selectByUUIDAndDateRange(Map<String, Object> params) {
		return attendanceRequestDao.selectByUUIDAndDateRange(params);
	}

	// 10. 전체 근태 관리 요청 조회
	@Override
	public List<AttendanceDocument> selectAllAttendanceRequests() {
		return attendanceRequestDao.selectAllAttendanceRequests();
	}

	// 11. 근태 관리 요청 업데이트
	@Override
	public int updateAttendanceRequest(AttendanceDocument request) {
		return attendanceRequestDao.updateAttendanceRequest(request);
	}

	// 12. 특정 UUID로 근태 관리 요청 삭제
	@Override
	public int deleteAttendanceRequestByUuid(String uuid) {
		return attendanceRequestDao.deleteAttendanceRequestByUuid(uuid);
	}

	// 13. 특정 근태 관리 요청 ID로 삭제
	@Override
	public int deleteAttendanceRequestById(String attendanceRequestId) {
		return attendanceRequestDao.deleteAttendanceRequestById(attendanceRequestId);
	}


}
