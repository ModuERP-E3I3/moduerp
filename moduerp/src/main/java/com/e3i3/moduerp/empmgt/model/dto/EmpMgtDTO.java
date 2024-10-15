package com.e3i3.moduerp.empmgt.model.dto;

public class EmpMgtDTO {
    private String empNo;          // 사번
    private String empName;        // 직원명
    private String departmentId;   // 부서 코드
    private String jobId;          // 직급 코드
    private String email;          // 이메일
    private String phone;          // 전화번호
    private String address;        // 주소
    private String bizNumber;      // 사업자 번호

    public EmpMgtDTO() {
        super();
    }

    public EmpMgtDTO(String empNo, String empName, String departmentId, String jobId, 
                     String email, String phone, String address, String bizNumber) {
        super();
        this.empNo = empNo;
        this.empName = empName;
        this.departmentId = departmentId;
        this.jobId = jobId;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.bizNumber = bizNumber;
    }

    // Getters and Setters
    public String getEmpNo() {
        return empNo;
    }

    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBizNumber() {
        return bizNumber;
    }

    public void setBizNumber(String bizNumber) {
        this.bizNumber = bizNumber;
    }
}
