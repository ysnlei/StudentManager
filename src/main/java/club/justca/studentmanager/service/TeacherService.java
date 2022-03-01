package club.justca.studentmanager.service;

import club.justca.studentmanager.entity.Teacher;

import java.util.List;

public interface TeacherService {
    List<Teacher> findAll();

    List<Teacher> findAllExceptSuperAdmin();

    Teacher findByTeacherNum(String teacherNum);

    Teacher findByTeacherNumAndPassword(String teacherNum, String password);

    Integer insert(Teacher teacher);

    Integer update(Teacher teacher);

    Integer deleteByTeacherNum(String teacherNum);

    Integer deleteById(Integer id);
}
