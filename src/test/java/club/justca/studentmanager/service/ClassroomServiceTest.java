package club.justca.studentmanager.service;

import club.justca.studentmanager.entity.Classroom;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ClassroomServiceTest {
    @Autowired
    ClassroomService classroomService;
    @Test
    public void findByIdTest(){
        Classroom byId = classroomService.findById(4);
        System.out.println(byId);
    }
}
