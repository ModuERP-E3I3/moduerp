package com.e3i3.moduerp.attendancemanagement.model.service;

import java.util.Date;
import java.util.List;

import com.e3i3.moduerp.attendancemanagement.model.dto.Attendance;

public interface AttendanceService {
    // Create
    public void addAttendance(Attendance attendance);

    // Read
    public Attendance selectAttendanceById(String attendanceId);
    public Attendance selectAttendanceByUuid(String uuid);
    public List<Attendance> selectAttendancesByBizNumber(String bizNumber);
    public List<Attendance> selectAllAttendances();

    // 새로운 메서드: 오늘의 출퇴근 기록 조회
    public Attendance selectTodayAttendance(String uuid);
    
    
    /**
     * 특정 사용자(uuid)와 날짜(attDate)에 해당하는 출근 기록을 조회합니다.
     * @param uuid
     * @param attDate
     * @return Attendance 객체 또는 null
     */
    public Attendance selectAttendanceByUUIDAndDate(String uuid, Date attDate);

    /**
     * 특정 사용자(uuid)와 날짜(attDate)에 해당하는 퇴근 시간이 없는 출근 기록을 조회합니다.
     * @param uuid
     * @param attDate
     * @return Attendance 객체 또는 null
     */
    public Attendance selectAttendanceByUUIDAndDateAndClockOutNull(String uuid, Date attDate);
    
    
    // Update
    public int updateAttendance(Attendance attendance);

    // Delete
    public int deleteAttendance(String attendanceId);
    public int deleteAttendanceByUuid(String uuid);
    
}