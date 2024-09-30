package com.e3i3.moduerp.employee.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.e3i3.moduerp.company.model.dto.Company;
import com.e3i3.moduerp.department.model.dto.Department;
import com.e3i3.moduerp.employee.model.dto.Employee;

public class ExcelParser {

    // 직원 정보 파싱 메소드
    public static List<Employee> parseEmployees(InputStream is, Company company) throws IOException {
        List<Employee> employees = new ArrayList<>();
        Workbook workbook = new XSSFWorkbook(is);
        Sheet sheet = workbook.getSheet("사원정보");

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row == null) continue;

            Employee employee = new Employee();
            String departmentId = getCellValue(row.getCell(3)); // 부서코드
            // departmentId가 빈 문자열이거나 null이면 예외 처리
            if (departmentId == null || departmentId.trim().isEmpty()) {
                throw new IllegalArgumentException("부서코드가 누락된 행이 있습니다. 행 번호: " + (i + 1));
            }
            employee.setEmpName(getCellValue(row.getCell(0))) // 사원명
                    .setEmpNo(getCellValue(row.getCell(1)))   // 사번
                    .setEmpEmail(getCellValue(row.getCell(2)))// 사원 이메일
                    .setDepartmentId(departmentId)            // 부서코드
                    .setUuid(java.util.UUID.randomUUID())
                    .setBizNumber(company.getBizNumber())
                    .setIsDeleted('N')
                    .setApprovalCode(company.getApprovalCode())
                    .setPrivateAuthority('N')
                    .setLastLoginLocation("default")
                    .setIsEmailChanged('N')
                    .setRegistrationDate(new java.sql.Date(System.currentTimeMillis()));

            employees.add(employee);
        }
        workbook.close();
        return employees;
    }

    // 부서 정보 파싱 메소드
    public static List<Department> parseDepartments(InputStream is, String bizNumber) throws IOException {
        List<Department> departments = new ArrayList<>();
        Workbook workbook = new XSSFWorkbook(is);
        Sheet sheet = workbook.getSheet("부서정보");

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row == null) continue;

            Department department = new Department();
            department.setDepartmentId(getCellValue(row.getCell(0)))  // 부서코드
                      .setDepartmentName(getCellValue(row.getCell(1)))  // 부서명
                      .setBizNumber(bizNumber);                        // 사업자번호

            departments.add(department);
        }
        workbook.close();
        return departments;
    }

    // 셀 값 읽기 유틸리티 메소드
    private static String getCellValue(Cell cell) {
        if (cell == null) return "";
        switch (cell.getCellType()) {
            case STRING: return cell.getStringCellValue();
            case NUMERIC: return String.valueOf((int) cell.getNumericCellValue());
            case BOOLEAN: return String.valueOf(cell.getBooleanCellValue());
            default: return "";
        }
    }
}
