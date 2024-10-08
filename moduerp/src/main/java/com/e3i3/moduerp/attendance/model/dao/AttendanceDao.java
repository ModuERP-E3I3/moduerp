package com.e3i3.moduerp.attendance.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.e3i3.moduerp.attendance.model.dto.Attendance;

@Repository
public class AttendanceDao {
    
    private static final String NAMESPACE = "AttendanceMapper.";
    
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;
    
    // Create
    public int insertAttendance(Attendance attendance) {
        return sqlSessionTemplate.insert("AttendanceMapper.insertAttendance", attendance);
    }

    // Read
    public Attendance selectAttendanceByUuid(String uuid) {
        return sqlSessionTemplate.selectOne("AttendanceMapper.selectAttendanceByUuid", uuid);
    }
    // 새로운 메서드: 오늘의 출퇴근 기록 조회
    public Attendance selectTodayAttendanceByUuid(String uuid) {
        return sqlSessionTemplate.selectOne("AttendanceMapper.selectTodayAttendanceByUuid", uuid);
    }
    
    
    public Attendance selectAttendanceById(String attendanceId) {
        return sqlSessionTemplate.selectOne("AttendanceMapper.selectAttendanceByAttendanceId", attendanceId);
    }
    
    public List<Attendance> selectAttendancesByBizNumber(String bizNumber) {
        return sqlSessionTemplate.selectList("AttendanceMapper.selectAttendancesByBizNumber", bizNumber);
    }
    
    public List<Attendance> selectAllAttendances() {
        return sqlSessionTemplate.selectList("AttendanceMapper.selectAllAttendances");
    }

    // Update
    public int updateAttendance(Attendance attendance) {
        return sqlSessionTemplate.update("AttendanceMapper.updateAttendance", attendance);
    }

    // Delete
    public int deleteAttendanceByUuid(String uuid) {
        return sqlSessionTemplate.delete("AttendanceMapper.deleteAttendanceByUuid", uuid);
    }
    
    public int deleteAttendanceByAttendanceId(String attendanceId) {
        return sqlSessionTemplate.delete("AttendanceMapper.deleteAttendanceByAttendanceId", attendanceId);
    }
}
