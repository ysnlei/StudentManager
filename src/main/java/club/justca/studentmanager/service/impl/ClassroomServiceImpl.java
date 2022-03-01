package club.justca.studentmanager.service.impl;

import club.justca.studentmanager.entity.Classroom;
import club.justca.studentmanager.mapper.ClassroomMapper;
import club.justca.studentmanager.service.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassroomServiceImpl implements ClassroomService {
    @Autowired
    ClassroomMapper classroomMapper;

    @Override
    public List<Classroom> findAll() {
        return classroomMapper.findAll();
    }

    @Override
    public List<Classroom> findByBuildingId(Integer buildingId) {
        return classroomMapper.findByBuildingId(buildingId);
    }

    @Override
    public Classroom findById(Integer id) {
        return classroomMapper.findById(id);
    }

    @Override
    public Integer update(Classroom classroom) {
        return classroomMapper.update(classroom);
    }

    @Override
    public Integer insert(Classroom classroom) {
        return classroomMapper.insert(classroom);
    }

    @Override
    public Integer deleteById(Integer id) {
        return classroomMapper.deleteById(id);
    }
}
