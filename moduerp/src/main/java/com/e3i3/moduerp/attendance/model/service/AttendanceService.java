package com.e3i3.moduerp.attendance.model.service;

import java.util.List;

import com.e3i3.moduerp.attendance.model.dto.Attendance;

public interface AttendanceService {
    // Create
    public void addAttendance(Attendance attendance);

    // Read
    public Attendance getAttendanceById(String attendanceId);
    public Attendance selectAttendanceByUuid(String uuid);
    public List<Attendance> getAttendancesByBizNumber(String bizNumber);
    public List<Attendance> getAllAttendances();

    // 새로운 메서드: 오늘의 출퇴근 기록 조회
    public Attendance getTodayAttendance(String uuid);
    
    // Update
    public int updateAttendance(Attendance attendance);

    // Delete
    public int deleteAttendance(String attendanceId);
    public int deleteAttendanceByUuid(String uuid);
    
}