package club.justca.studentmanager.mapper;

import club.justca.studentmanager.entity.ClassOrderDetail;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassOrderDetailMapper {
    List<ClassOrderDetail> findAll();

    ClassOrderDetail findById(Integer id);

    List<ClassOrderDetail> findByMajor(String major);

    List<ClassOrderDetail> findByStatus(String status);

    List<ClassOrderDetail> findByClassroomId(Integer classroomId);

    ClassOrderDetail findByClassroomIdAndOrderTime(Integer classroomId, String orderTime);

    Integer insert(ClassOrderDetail classOrderDetail);

    Integer update(ClassOrderDetail classOrderDetail);

    Integer deleteById(Integer id);
}
