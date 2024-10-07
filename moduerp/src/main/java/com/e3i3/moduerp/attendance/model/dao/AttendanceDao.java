package com.e3i3.moduerp.attendance.model.dao;

import java.util.List;

import com.e3i3.moduerp.attendance.model.dto.Attendance;

public interface AttendanceDao {
	   // Create
    int insertAttendance(Attendance attendance);

    // Read
    Attendance selectAttendanceByUuid(String uuid); //uuid로 출퇴근 관리 
    List<Attendance> selectAttendancesByBizNumber(String bizNumber); //사업자번호로 출퇴근 관리
    List<Attendance> selectAllAttendances(); //전체 출퇴근 관리

    // Update
    int updateAttendance(Attendance attendance); //출퇴근 수정

    // Delete
    int deleteAttendanceByUuid(String uuid); //uuid로 내 출퇴근 모든 기록 삭제
    int deleteAttendanceByAttendanceId(String attendanceId); //출퇴근 선택 삭제
}