package club.justca.studentmanager.service;

import club.justca.studentmanager.entity.Student;

import java.util.List;

public interface StudentService {
    List<Student> findAll();

    Student findByStudentNum(String studentNum);

    Student findByStudentNumAndPassword(String username, String password);

    List<Student> findStudentByMajor(String major);

    Integer insertFromStudentList(List<Student> studentList);

    Integer insert(Student student);

    Integer deleteAll();

    Integer deleteByStudentNum(String studentNum);

    Integer update(Student student);

    Integer resetPassword(String studentNum);

    List<String> findAllMajor();
}
