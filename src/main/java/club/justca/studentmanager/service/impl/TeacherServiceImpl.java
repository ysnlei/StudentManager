package club.justca.studentmanager.service.impl;

import club.justca.studentmanager.entity.Teacher;
import club.justca.studentmanager.mapper.TeacherMapper;
import club.justca.studentmanager.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    TeacherMapper teacherMapper;

    @Override
    public List<Teacher> findAll() {
        return teacherMapper.findAll();
    }

    @Override
    public List<Teacher> findAllExceptSuperAdmin() {
        return teacherMapper.findAllExceptSuperAdmin();
    }

    @Override
    public Teacher findByTeacherNum(String teacherNum) {
        return teacherMapper.findByTeacherNum(teacherNum);
    }

    @Override
    public Teacher findByTeacherNumAndPassword(String teacherNum, String password) {
        return teacherMapper.findByTeacherNumAndPassword(teacherNum, password);
    }

    @Override
    public Integer insert(Teacher teacher) {
        return teacherMapper.insert(teacher);
    }

    @Override
    public Integer update(Teacher teacher) {
        return teacherMapper.update(teacher);
    }

    @Override
    public Integer deleteByTeacherNum(String teacherNum) {
        return teacherMapper.deleteByTeacherNum(teacherNum);
    }

    @Override
    public Integer deleteById(Integer id) {
        return teacherMapper.deleteById(id);
    }
}
