package club.justca.studentmanager.service;

import club.justca.studentmanager.entity.Classroom;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ClassroomService {
    List<Classroom> findAll();

    List<Classroom> findByBuildingId(Integer buildingId);

    Classroom findById(Integer id);

    Integer update(Classroom classroom);

    Integer insert(Classroom classroom);

    Integer deleteById(Integer id);
}
