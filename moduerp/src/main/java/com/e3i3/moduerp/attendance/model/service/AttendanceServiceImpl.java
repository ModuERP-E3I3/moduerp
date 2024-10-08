package com.e3i3.moduerp.attendance.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e3i3.moduerp.attendance.model.dao.AttendanceDao;
import com.e3i3.moduerp.attendance.model.dto.Attendance;

@Service
public class AttendanceServiceImpl implements AttendanceService {
    
    @Autowired
    private AttendanceDao attendanceDao;

    // Create
    @Override
    public void addAttendance(Attendance attendance) {
        attendanceDao.insertAttendance(attendance);
    }

    // Read
    @Override
    public Attendance getAttendanceById(String attendanceId) {
        return attendanceDao.selectAttendanceById(attendanceId);
    }

    @Override
    public Attendance selectAttendanceByUuid(String uuid) {
        return attendanceDao.selectAttendanceByUuid(uuid);
    }
    
    // 로그인 유저의 오늘 출퇴근 조회 메소드
    @Override
    public Attendance getTodayAttendance(String uuid) {
        return attendanceDao.selectTodayAttendanceByUuid(uuid);
    }
    @Override
    public List<Attendance> getAttendancesByBizNumber(String bizNumber) {
        return attendanceDao.selectAttendancesByBizNumber(bizNumber);
    }

    @Override
    public List<Attendance> getAllAttendances() {
        return attendanceDao.selectAllAttendances();
    }

    // Update
    @Override
    public int updateAttendance(Attendance attendance) {
        return attendanceDao.updateAttendance(attendance);
    }

    // Delete
    @Override
    public int deleteAttendance(String attendanceId) {
        return attendanceDao.deleteAttendanceByAttendanceId(attendanceId);
    }

    @Override
    public int deleteAttendanceByUuid(String uuid) {
        return attendanceDao.deleteAttendanceByUuid(uuid);
    }
}
