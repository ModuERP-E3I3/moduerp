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

    // ���� ���� �Ľ� �޼ҵ�
    public static List<Employee> parseEmployees(InputStream is, Company company) throws IOException {
        List<Employee> employees = new ArrayList<>();
        Workbook workbook = new XSSFWorkbook(is);
        Sheet sheet = workbook.getSheet("�������");

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row == null) continue;

            Employee employee = new Employee();
            String departmentId = getCellValue(row.getCell(3)); // �μ��ڵ�
            // departmentId�� �� ���ڿ��̰ų� null�̸� ���� ó��
            if (departmentId == null || departmentId.trim().isEmpty()) {
                throw new IllegalArgumentException("�μ��ڵ尡 ������ ���� �ֽ��ϴ�. �� ��ȣ: " + (i + 1));
            }
            employee.setEmpName(getCellValue(row.getCell(0))) // �����
                    .setEmpNo(getCellValue(row.getCell(1)))   // ���
                    .setEmpEmail(getCellValue(row.getCell(2)))// ��� �̸���
                    .setDepartmentId(departmentId)            // �μ��ڵ�
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

    // �μ� ���� �Ľ� �޼ҵ�
    public static List<Department> parseDepartments(InputStream is, String bizNumber) throws IOException {
        List<Department> departments = new ArrayList<>();
        Workbook workbook = new XSSFWorkbook(is);
        Sheet sheet = workbook.getSheet("�μ�����");

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row == null) continue;

            Department department = new Department();
            department.setDepartmentId(getCellValue(row.getCell(0)))  // �μ��ڵ�
                      .setDepartmentName(getCellValue(row.getCell(1)))  // �μ���
                      .setBizNumber(bizNumber);                        // ����ڹ�ȣ

            departments.add(department);
        }
        workbook.close();
        return departments;
    }

    // �� �� �б� ��ƿ��Ƽ �޼ҵ�
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