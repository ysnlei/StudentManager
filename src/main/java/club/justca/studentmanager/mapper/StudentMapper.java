package club.justca.studentmanager.mapper;

import club.justca.studentmanager.entity.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentMapper {
    List<Student> findAll();

    List<Student> findStudentByMajor(String major);

    Student findByStudentNumAndPassword(String studentNum, String password);

    Student findByStudentNum(String studentNum);

    Integer insert(Student student);

    Integer update(Student student);

    Integer deleteAll();

    Integer deleteByStudentNum(String studentNum);

    Integer resetPassword(String studentNum);   //将密码重置为123456

    List<String> findAllMajor();
}
