package club.justca.studentmanager.mapper;

import club.justca.studentmanager.entity.Teacher;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherMapper {
    /**
     * 获取全部教师信息
     */
    List<Teacher> findAll();

    List<Teacher> findAllExceptSuperAdmin();

    /**
     * 通过教师号获取教师的信息
     *
     * @param teacherNum 教师号teacherNum
     * @return 查找到的教师实例，没找到返回null
     */
    Teacher findByTeacherNum(String teacherNum);

    Teacher findByTeacherNumAndPassword(String teacherNum, String password);

    Integer insert(Teacher teacher);

    Integer deleteById(Integer id);

    Integer deleteByTeacherNum(String teacherNum);

    Integer update(Teacher teacher);
}
