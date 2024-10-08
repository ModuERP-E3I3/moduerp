package com.e3i3.moduerp.attendance.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e3i3.moduerp.attendance.model.dao.AttendanceDao;
import com.e3i3.moduerp.attendance.model.dto.Attendance;

import java.util.List;

@Service
public class AttendanceServiceImpl implements AttendanceService {

	@Autowired
	private AttendanceDao attendanceDao;

	@Override
	public void addAttendance(Attendance attendance) {
		attendanceDao.insertAttendance(attendance);
	}

	@Override
	public Attendance getAttendanceById(String attendanceId) {
		return attendanceDao.selectAttendanceById(attendanceId);
	}

	@Override
	public List<Attendance> getAllAttendances() {
		return attendanceDao.selectAllAttendances();
	}

	@Override
	public int updateAttendance(Attendance attendance) {
		return attendanceDao.updateAttendance(attendance);
	}

	@Override
	public Attendance selectAttendanceByUuid(String uuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Attendance> getAttendancesByBizNumber(String bizNumber) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public int deleteAttendance(String attendanceId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteAttendanceByUuid(String uuid) {
		// TODO Auto-generated method stub
		return 0;
	}

}