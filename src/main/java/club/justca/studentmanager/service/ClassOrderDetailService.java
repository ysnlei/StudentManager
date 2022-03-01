package club.justca.studentmanager.service;

import club.justca.studentmanager.entity.ClassOrderDetail;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ClassOrderDetailService {
    List<ClassOrderDetail> findAll();

    List<ClassOrderDetail> findByMajor(String major);

    ClassOrderDetail findById(Integer id);

    List<ClassOrderDetail> findByStatus(String status);

    List<ClassOrderDetail> findByClassroomId(Integer classroomId);

    ClassOrderDetail findByClassroomIdAndOrderTime(Integer classroomId, String orderTime);

    Integer insert(ClassOrderDetail classOrderDetail);

    Integer update(ClassOrderDetail classOrderDetail);

    Integer deleteById(Integer id);
}
