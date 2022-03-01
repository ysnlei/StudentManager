package club.justca.studentmanager.mapper;

import club.justca.studentmanager.entity.Student;
import club.justca.studentmanager.utils.ExcelUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class StudentMapperTest {
    @Autowired
    StudentMapper studentMapper;

    @Test
    public void findAllTest() {
        List<Student> studentList = studentMapper.findAll();
        for (Student student : studentList) {
            System.out.println(student);
        }
    }

    @Test
    public void insertTest() {
        String filePath = "D:/workspace/Java-workspace/Intellj IDEA/HttpClientDemo/src/main/resources/导入学生名单.xlsx";
        List<Student> studentList = ExcelUtil.getStudentListFromExcel(filePath);
        int row = 0;
        if (studentList != null) {
            for (Student student : studentList) {
                if (student.getPassword() == null) {
                    student.setPassword("123456");
                }
                row += studentMapper.insert(student);
            }
        }
        System.out.println(row);
    }

    @Test
    public void findByStudentNumAndPasswordTest() {
        Student student = studentMapper.findByStudentNumAndPassword(
                "188111545232", "123456");
        System.out.println(student);
    }

    @Test
    public void findByStudentNumTest() {
        Student student = studentMapper.findByStudentNum("188111545230");
        System.out.println(student);
    }

    @Test
    public void deleteAllTest() {
        Integer row = studentMapper.deleteAll();
        System.out.println(row);
    }

    @Test void deleteByStudentNumTest(){
        Integer row = studentMapper.deleteByStudentNum("188111545233");
        System.out.println(row);
        System.out.println(studentMapper.findByStudentNum("188111545233"));
    }

    @Test
    public void findStudentByMajorTest() {
        List<Student> ss = studentMapper.findStudentByMajor("软件工程");
        for (Student s : ss) {
            System.out.println(s);
        }
        List<Student> ss1 = studentMapper.findStudentByMajor("工程");
        System.out.println(ss1);
    }

    @Test
    public void updateTest() {
        Student student = studentMapper.findByStudentNum("188111545232");
        System.out.println(student);
        student.setDuty("班长");
        Integer update = studentMapper.update(student);
        System.out.println(update);
        System.out.println(studentMapper.findByStudentNum("188111545232"));
    }

    @Test
    public void findAllMajorTest(){
        List<String> majorList = studentMapper.findAllMajor();
        System.out.println(majorList);
    }
}
