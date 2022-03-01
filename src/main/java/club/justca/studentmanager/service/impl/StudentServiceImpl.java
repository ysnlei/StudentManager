package club.justca.studentmanager.service.impl;

import club.justca.studentmanager.entity.Student;
import club.justca.studentmanager.mapper.StudentMapper;
import club.justca.studentmanager.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentMapper studentMapper;

    @Override
    public List<Student> findAll() {
        return studentMapper.findAll();
    }

    @Override
    public Student findByStudentNum(String studentNum) {
        return studentMapper.findByStudentNum(studentNum);
    }

    @Override
    public Student findByStudentNumAndPassword(String username, String password) {
        return studentMapper.findByStudentNumAndPassword(username, password);
    }

    @Override
    public List<Student> findStudentByMajor(String major) {
        return studentMapper.findStudentByMajor(major);
    }

    @Override
    public Integer insertFromStudentList(List<Student> studentList) {
        Integer row = 0;
        for (Student student : studentList) {
            studentMapper.deleteByStudentNum(student.getStudentNum());
            row += studentMapper.insert(student);
        }
        return row;
    }

    @Override
    public Integer insert(Student student) {
        studentMapper.deleteByStudentNum(student.getStudentNum());
        return studentMapper.insert(student);
    }

    @Override
    public Integer deleteAll() {
        return studentMapper.deleteAll();
    }

    @Override
    public Integer deleteByStudentNum(String studentNum) {
        return studentMapper.deleteByStudentNum(studentNum);
    }

    @Override
    public Integer update(Student student) {
        return studentMapper.update(student);
    }

    @Override
    public Integer resetPassword(String studentNum) {
        return studentMapper.resetPassword(studentNum);
    }

    @Override
    public List<String> findAllMajor() {
        return studentMapper.findAllMajor();
    }

}
