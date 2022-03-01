package club.justca.studentmanager.service;

import club.justca.studentmanager.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

@SpringBootTest
public class StudentServiceTest {
    @Autowired
    StudentService studentService;
    @Test
    public void findByMajorTest(){
        List<Student> studentList = studentService.findStudentByMajor("软件工程");
        studentList.forEach(System.out::println);
    }
}
