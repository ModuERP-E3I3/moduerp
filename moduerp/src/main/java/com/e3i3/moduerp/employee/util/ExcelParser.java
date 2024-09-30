package com.e3i3.moduerp.employee.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.e3i3.moduerp.company.model.dto.Company;
import com.e3i3.moduerp.employee.model.dto.Employee;

/*엑셀 파일 파싱을 위한 ExcelParser 유틸리티 클래스*/
public class ExcelParser {
	public static List<Employee> parseExcelFile(InputStream is, Company company) throws IOException {
		List<Employee> employees = new ArrayList<>();
		
		Workbook workbook = new XSSFWorkbook(is);
		Sheet sheet = workbook.getSheet("사원정보");

		for (int i = 1; i <= sheet.getLastRowNum(); i++) { // 헤더 제외하고 첫 번째 줄부터 읽기
			Row row = sheet.getRow(i);
			if (row == null)
				continue;
			
			
			Employee employee = new Employee();
			//사원명	사번	사원이메일	부서코드
			employee.setUuid(UUID.randomUUID())
					.setBizNumber(company.getBizNumber())
					.setApprovalCode(company.getApprovalCode())
					.setJobId(null)
					.setUserPhone(null)
					.setProfileImg(null)
					.setUpdatedAt(null)
					.setIsDeleted('N')
					.setDeletedExcuse(null)
					.setDeletedAt(null)
					.setPrivateAuthority('N')
					.setRegistrationDate(new Date(System.currentTimeMillis()))
					.setLastLoginLocation("default")
					.setHireDate(null)
					.setQuitDate(null)
					.setReNum(null)
					.setAddress(null)
					.setIsEmailChanged('N')
					.setNewEmpEmail(null)
					.setRemainingLeave(0)
					.setMgrUuid(null)
					.setContractStartTime(LocalTime.of(9, 0))
					.setContractEndTime(LocalTime.of(18, 0))
					.setEmpName(getCellValue(row.getCell(0)))
					.setEmpNo(getCellValue(row.getCell(1)))
					.setEmpEmail(getCellValue(row.getCell(2)))
					.setDeptId(getCellValue(row.getCell(3)));
			
			employees.add(employee);
			
		}
		workbook.close();
		return employees;
	}

	private static String getCellValue(Cell cell) {
		if(cell == null) return "";
		switch(cell.getCellType()) {
		case STRING:
				return cell.getStringCellValue();
		case NUMERIC:
			   // 숫자 셀에서 소수점 제거 (필요한 경우)
                return String.valueOf((int) cell.getNumericCellValue());
		case BOOLEAN:
				return String.valueOf(cell.getBooleanCellValue());
		default:
			return "";
		}
	}
}
