package com.e3i3.moduerp.attendance.model.service;

import java.util.List;

import com.e3i3.moduerp.attendance.model.dto.Attendance;

public interface AttendanceService {
    // Create
    void addAttendance(Attendance attendance);

    // Read
    Attendance getAttendanceById(String attendanceId);
    List<Attendance> getAllAttendances();

    // Update
    void updateAttendance(Attendance attendance);

    // Delete
    void deleteAttendance(String attendanceId);
}