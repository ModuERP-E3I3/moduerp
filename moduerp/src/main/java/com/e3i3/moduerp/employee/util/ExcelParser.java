package com.e3i3.moduerp.employee.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;

import com.e3i3.moduerp.company.model.dto.Company;
import com.e3i3.moduerp.department.model.dto.Department;
import com.e3i3.moduerp.employee.model.dto.Employee;

public class ExcelParser {
	  private static final Logger logger = LoggerFactory.getLogger(ExcelParser.class);
	  
	  // 직원 정보 파싱 메소드
	    public static List<Employee> parseEmployees(Workbook workbook, Company company, List<Department> departments) throws IOException {
	        List<Employee> employees = new ArrayList<>();
	        Sheet sheet = workbook.getSheet("사원정보");

	        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
	            Row row = sheet.getRow(i);
	            if (isRowEmpty(row)) continue; // 모든 셀이 비어 있으면 건너뜀

	            Employee employee = new Employee();
	            String departmentName = getCellValue(row.getCell(3)); // 부서명

	            // departmentName이 빈 문자열이거나 null이면 예외 처리
	            if (departmentName == null || departmentName.trim().isEmpty()) {
	                throw new IllegalArgumentException("부서명이 누락된 행이 있습니다. 행 번호: " + (i + 1));
	            }

	            // departmentName으로 departmentId 찾기
	            String departmentId = findDepartmentIdByName(departmentName, departments);

	            employee.setEmpName(getCellValue(row.getCell(0))) // 사원명
	                    .setEmpNo(getCellValue(row.getCell(1)))   // 사번
	                    .setEmpEmail(getCellValue(row.getCell(2)))// 사원 이메일
	                    .setDepartmentId(departmentId)           // 부서코드
	                    .setJobId(getCellValue(row.getCell(4))) // 직급
	                    .setUuid(java.util.UUID.randomUUID().toString())
	                    .setBizNumber(company.getBizNumber())
	                    .setIsDeleted('N')
	                    .setApprovalCode(company.getApprovalCode())
	                    .setPrivateAuthority("N")
	                    .setLastLoginLocation("default")
	                    .setIsEmailChanged('N')
	                    .setRegistrationDate(new java.sql.Date(System.currentTimeMillis()));

	            employees.add(employee);
	        }
	        workbook.close();
	        return employees;
	    }

	    // 부서명으로 departmentId 찾기
	    private static String findDepartmentIdByName(String departmentName, List<Department> departments) {
	        for (Department department : departments) {
	            if (department.getDepartmentName().equalsIgnoreCase(departmentName)) {
	                return department.getDepartmentId();
	            }
	        }
	        throw new IllegalArgumentException("부서명이 일치하는 부서를 찾을 수 없습니다: " + departmentName);
	    }

	    // 부서 정보 파싱 메소드
	    public static List<Department> parseDepartments(Workbook workbook, String bizNumber) throws IOException {
	        List<Department> departments = new ArrayList<>();
	        Sheet sheet = workbook.getSheet("부서정보");

	        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
	            Row row = sheet.getRow(i);
	            if (isRowEmpty(row)) continue; // 모든 셀이 비어 있으면 건너뜀

	            Department department = new Department();
	            department.generateCustomDepartmentId(i) // 행의 순서로 departmentId 생성 (DPT-0001, DPT-0002 등) 
	                      .setDepartmentName(getCellValue(row.getCell(0)))  // 부서명
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
	            case NUMERIC: 
	                if (DateUtil.isCellDateFormatted(cell)) {
	                    return cell.getDateCellValue().toString();
	                } else {
	                    // 소수점 없는 정수로 변환
	                    double numericValue = cell.getNumericCellValue();
	                    if (numericValue == (long) numericValue) {
	                        return String.valueOf((long) numericValue);
	                    } else {
	                        return String.valueOf(numericValue);
	                    }
	                }
	            case BOOLEAN: return String.valueOf(cell.getBooleanCellValue());
	            case FORMULA: return cell.getCellFormula();
	            case BLANK: return "";
	            default: return "";
	        }
	    }

	    // 행이 모두 비어있는지 확인하는 메소드
	    private static boolean isRowEmpty(Row row) {
	        if (row == null) return true;
	        for (int c = 0; c < row.getLastCellNum(); c++) {
	            Cell cell = row.getCell(c);
	            if (cell != null && cell.getCellType() != CellType.BLANK && 
	                !getCellValue(cell).trim().isEmpty()) {
	                return false;
	            }
	        }
	        return true;
	    }
	}