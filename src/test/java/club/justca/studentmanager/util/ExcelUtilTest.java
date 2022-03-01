package club.justca.studentmanager.util;

import club.justca.studentmanager.entity.Student;
import club.justca.studentmanager.utils.ExcelUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ExcelUtilTest {
    @Test
    public void getStudentListFromExcelTest(){
        String filePath = "D:/workspace/Java-workspace/Intellj IDEA/HttpClientDemo/src/main/resources/导入学生名单.xlsx";
        List<Student> studentList = ExcelUtil.getStudentListFromExcel(filePath);
        for (Student student : studentList) {
            System.out.println(student);
        }
    }
}
