package com.e3i3.moduerp.attendancemanagement.model.service;

import java.util.List;

import com.e3i3.moduerp.attendancemanagement.model.dto.Attendance;

public interface AttendanceService {
    // Create
    public void addAttendance(Attendance attendance);

    // Read
    public Attendance getAttendanceById(String attendanceId);
    public Attendance selectAttendanceByUuid(String uuid);
    public List<Attendance> getAttendancesByBizNumber(String bizNumber);
    public List<Attendance> getAllAttendances();

    // Update
    public int updateAttendance(Attendance attendance);

    // Delete
    public int deleteAttendance(String attendanceId);
    public int deleteAttendanceByUuid(String uuid);
    
}