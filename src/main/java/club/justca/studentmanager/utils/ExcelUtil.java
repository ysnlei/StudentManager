package club.justca.studentmanager.utils;

import club.justca.studentmanager.entity.Student;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtil {
    public static List<Student> getStudentListFromExcel(String filePath) {
        Workbook wb = null;
        String extString = filePath.substring(filePath.lastIndexOf("."));
        InputStream is = null;
        try {
            is = new FileInputStream(filePath);
            if (".xls".equals(extString)) {
                wb = new HSSFWorkbook(is);
            } else if (".xlsx".equals(extString)) {
                wb = new XSSFWorkbook(is);
            } else {
                return null;
            }
            List<Student> studentList = new ArrayList<>();
            Sheet sheet = wb.getSheetAt(0);
            for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {// XSSFRow 代表一行数据
                Row row = sheet.getRow(rowIndex);
                if (row == null) {
                    continue;
                }
                Student student = new Student();
                student.setStudentNum(row.getCell(0).getStringCellValue());
                student.setName(row.getCell(1).getStringCellValue());
                student.setGender(row.getCell(2).getStringCellValue());
                student.setMajor(row.getCell(3).getStringCellValue());
                student.setTelephone(row.getCell(4).getStringCellValue());
                student.setDormitory(row.getCell(5).getStringCellValue());
                student.setDuty(row.getCell(6).getStringCellValue());
                studentList.add(student);
            }
            return studentList;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(is);
            close(wb);
        }
        return null;
    }

    private static void close(AutoCloseable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
