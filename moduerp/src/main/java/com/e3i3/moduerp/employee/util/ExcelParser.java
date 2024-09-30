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

<<<<<<< Updated upstream
    // ¡˜ø¯ ¡§∫∏ ∆ƒΩÃ ∏ﬁº“µÂ
    public static List<Employee> parseEmployees(InputStream is, Company company) throws IOException {
        List<Employee> employees = new ArrayList<>();
        Workbook workbook = new XSSFWorkbook(is);
        Sheet sheet = workbook.getSheet("ªÁø¯¡§∫∏");
=======
    // ÏßÅÏõê Ï†ïÎ≥¥ ÌååÏã± Î©îÏÜåÎìú
    public static List<Employee> parseEmployees(InputStream is, Company company) throws IOException {
        List<Employee> employees = new ArrayList<>();
        Workbook workbook = new XSSFWorkbook(is);
        Sheet sheet = workbook.getSheet("ÏÇ¨ÏõêÏ†ïÎ≥¥");
>>>>>>> Stashed changes

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row == null) continue;

            Employee employee = new Employee();
<<<<<<< Updated upstream
            String departmentId = getCellValue(row.getCell(3)); // ∫Œº≠ƒ⁄µÂ
            // departmentId∞° ∫Û πÆ¿⁄ø≠¿Ã∞≈≥™ null¿Ã∏È øπø‹ √≥∏Æ
            if (departmentId == null || departmentId.trim().isEmpty()) {
                throw new IllegalArgumentException("∫Œº≠ƒ⁄µÂ∞° ¥©∂Ùµ» «‡¿Ã ¿÷Ω¿¥œ¥Ÿ. «‡ π¯»£: " + (i + 1));
            }
            employee.setEmpName(getCellValue(row.getCell(0))) // ªÁø¯∏Ì
                    .setEmpNo(getCellValue(row.getCell(1)))   // ªÁπ¯
                    .setEmpEmail(getCellValue(row.getCell(2)))// ªÁø¯ ¿Ã∏ﬁ¿œ
                    .setDepartmentId(departmentId)            // ∫Œº≠ƒ⁄µÂ
=======
            String departmentId = getCellValue(row.getCell(3)); // Î∂ÄÏÑúÏΩîÎìú
            // departmentIdÍ∞Ä Îπà Î¨∏ÏûêÏó¥Ïù¥Í±∞ÎÇò nullÏù¥Î©¥ ÏòàÏô∏ Ï≤òÎ¶¨
            if (departmentId == null || departmentId.trim().isEmpty()) {
                throw new IllegalArgumentException("Î∂ÄÏÑúÏΩîÎìúÍ∞Ä ÎàÑÎùΩÎêú ÌñâÏù¥ ÏûàÏäµÎãàÎã§. Ìñâ Î≤àÌò∏: " + (i + 1));
            }
            employee.setEmpName(getCellValue(row.getCell(0))) // ÏÇ¨ÏõêÎ™Ö
                    .setEmpNo(getCellValue(row.getCell(1)))   // ÏÇ¨Î≤à
                    .setEmpEmail(getCellValue(row.getCell(2)))// ÏÇ¨Ïõê Ïù¥Î©îÏùº
                    .setDepartmentId(departmentId)            // Î∂ÄÏÑúÏΩîÎìú
>>>>>>> Stashed changes
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

<<<<<<< Updated upstream
    // ∫Œº≠ ¡§∫∏ ∆ƒΩÃ ∏ﬁº“µÂ
    public static List<Department> parseDepartments(InputStream is, String bizNumber) throws IOException {
        List<Department> departments = new ArrayList<>();
        Workbook workbook = new XSSFWorkbook(is);
        Sheet sheet = workbook.getSheet("∫Œº≠¡§∫∏");
=======
    // Î∂ÄÏÑú Ï†ïÎ≥¥ ÌååÏã± Î©îÏÜåÎìú
    public static List<Department> parseDepartments(InputStream is, String bizNumber) throws IOException {
        List<Department> departments = new ArrayList<>();
        Workbook workbook = new XSSFWorkbook(is);
        Sheet sheet = workbook.getSheet("Î∂ÄÏÑúÏ†ïÎ≥¥");
>>>>>>> Stashed changes

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row == null) continue;

            Department department = new Department();
<<<<<<< Updated upstream
            department.setDepartmentId(getCellValue(row.getCell(0)))  // ∫Œº≠ƒ⁄µÂ
                      .setDepartmentName(getCellValue(row.getCell(1)))  // ∫Œº≠∏Ì
                      .setBizNumber(bizNumber);                        // ªÁæ˜¿⁄π¯»£
=======
            department.setDepartmentId(getCellValue(row.getCell(0)))  // Î∂ÄÏÑúÏΩîÎìú
                      .setDepartmentName(getCellValue(row.getCell(1)))  // Î∂ÄÏÑúÎ™Ö
                      .setBizNumber(bizNumber);                        // ÏÇ¨ÏóÖÏûêÎ≤àÌò∏
>>>>>>> Stashed changes

            departments.add(department);
        }
        workbook.close();
        return departments;
    }

<<<<<<< Updated upstream
    // ºø ∞™ ¿–±‚ ¿Ø∆ø∏Æ∆º ∏ﬁº“µÂ
=======
    // ÏÖÄ Í∞í ÏùΩÍ∏∞ Ïú†Ìã∏Î¶¨Ìã∞ Î©îÏÜåÎìú
>>>>>>> Stashed changes
    private static String getCellValue(Cell cell) {
        if (cell == null) return "";
        switch (cell.getCellType()) {
            case STRING: return cell.getStringCellValue();
            case NUMERIC: return String.valueOf((int) cell.getNumericCellValue());
            case BOOLEAN: return String.valueOf(cell.getBooleanCellValue());
            default: return "";
        }
    }
<<<<<<< Updated upstream
}
=======
}
>>>>>>> Stashed changes
