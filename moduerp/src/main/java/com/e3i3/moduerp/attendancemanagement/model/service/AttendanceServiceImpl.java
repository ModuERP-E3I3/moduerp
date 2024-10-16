package com.e3i3.moduerp.attendancemanagement.model.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.e3i3.moduerp.attendancemanagement.model.dao.AttendanceDao;
import com.e3i3.moduerp.attendancemanagement.model.dto.Attendance;

@Service
public class AttendanceServiceImpl implements AttendanceService {
    
    @Autowired
    private AttendanceDao attendanceDao;

    // Create
    @Override
    @Transactional
    public void addAttendance(Attendance attendance) {
        attendanceDao.insertAttendance(attendance);
    }

    // Read
    @Override
    public Attendance selectAttendanceById(String attendanceId) {
        return attendanceDao.selectAttendanceById(attendanceId);
    }

    @Override
    public Attendance selectAttendanceByUuid(String uuid) {
        return attendanceDao.selectAttendanceByUuid(uuid);
    }

    @Override
    public List<Attendance> selectAttendancesByBizNumber(String bizNumber) {
        return attendanceDao.selectAttendancesByBizNumber(bizNumber);
    }

    @Override
    public List<Attendance> selectAllAttendances() {
        return attendanceDao.selectAllAttendances();
    }
    
    @Override
    public Attendance selectTodayAttendance(String uuid) {
        return attendanceDao.selectTodayAttendanceByUuid(uuid);
    }
    
    @Override
    public Attendance selectAttendanceByUUIDAndDate(String uuid, Date attDate) {
        Attendance attendance = attendanceDao.selectByUUIDAndDate(uuid, attDate);
        if (attendance != null) {
            // totWorkHrs 포맷팅
            if (attendance.getTotWorkHrs() != null) {
                int workHours = attendance.getTotWorkHrs().intValue();
                int workMinutes = (int) Math.round((attendance.getTotWorkHrs() - workHours) * 60);
                attendance.setFormattedTotWorkHrs(String.format("%02dh %02dm", workHours, workMinutes));
            } else {
                attendance.setFormattedTotWorkHrs("00h 00m");
            }

            // overtime 포맷팅
            if (attendance.getOvertime() != null && attendance.getOvertime() > 0) {
                int overtimeHours = attendance.getOvertime().intValue();
                int overtimeMinutes = (int) Math.round((attendance.getOvertime() - overtimeHours) * 60);
                attendance.setFormattedOvertime(String.format("%dh %dm", overtimeHours, overtimeMinutes));
            } else {
                attendance.setFormattedOvertime("0시간 0분");
            }
        }
        return attendance;
    }

    @Override
    public Attendance selectAttendanceByUUIDAndDateAndClockOutNull(String uuid, Date attDate) {
        return attendanceDao.selectByUUIDAndDateAndClockOutNull(uuid, attDate);
    }
    
    // Update
    @Override
    @Transactional
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
