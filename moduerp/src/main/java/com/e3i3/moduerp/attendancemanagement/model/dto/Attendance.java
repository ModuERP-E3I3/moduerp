package com.e3i3.moduerp.attendancemanagement.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Attendance implements Serializable {
    private static final long serialVersionUID = 3000856470837427795L;
    
    private String attendanceId;    // ATTENDANCE_ID (PK)
    private String uuid;            // UUID(FK to EMPLOYEE)
    private String bizNumber;       // BIZ_NUMBER(FK to COMPANY)
    private Date attDate;           // ATT_DATE
    private Timestamp clockInTime;  // CLOCK_IN_TIME
    private Timestamp clockOutTime; // CLOCK_OUT_TIME
    private Double totWorkHrs;      // TOT_WORK_HRS
    private Double overtime;        // OVERTIME
    
    // 추가된 필드
    private String formattedTotWorkHrs;   // "00h 00m" 형식
    private String formattedOvertime;     // "O시간 O분" 형식
    
}
