package club.justca.studentmanager.service.impl;

import club.justca.studentmanager.entity.ClassOrderDetail;
import club.justca.studentmanager.mapper.ClassOrderDetailMapper;
import club.justca.studentmanager.service.ClassOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassOrderDetailServiceImpl implements ClassOrderDetailService {
    @Autowired
    ClassOrderDetailMapper classOrderDetailMapper;

    @Override
    public List<ClassOrderDetail> findAll() {
        return classOrderDetailMapper.findAll();
    }

    @Override
    public List<ClassOrderDetail> findByMajor(String major) {
        return classOrderDetailMapper.findByMajor(major);
    }

    @Override
    public ClassOrderDetail findById(Integer id) {
        return classOrderDetailMapper.findById(id);
    }

    @Override
    public List<ClassOrderDetail> findByStatus(String status) {
        return classOrderDetailMapper.findByStatus(status);
    }

    @Override
    public List<ClassOrderDetail> findByClassroomId(Integer classroomId) {
        return classOrderDetailMapper.findByClassroomId(classroomId);
    }

    @Override
    public ClassOrderDetail findByClassroomIdAndOrderTime(Integer classroomId,String orderTime) {
        return classOrderDetailMapper.findByClassroomIdAndOrderTime(classroomId,orderTime);
    }

    @Override
    public Integer insert(ClassOrderDetail classOrderDetail) {
        return classOrderDetailMapper.insert(classOrderDetail);
    }

    @Override
    public Integer update(ClassOrderDetail classOrderDetail) {
        return classOrderDetailMapper.update(classOrderDetail);
    }

    @Override
    public Integer deleteById(Integer id) {
        return classOrderDetailMapper.deleteById(id);
    }
}
