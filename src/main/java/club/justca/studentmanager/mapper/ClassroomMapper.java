package club.justca.studentmanager.mapper;

import club.justca.studentmanager.entity.Classroom;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassroomMapper {
    List<Classroom> findAll();

    List<Classroom> findByBuildingId(Integer buildingId);

    Classroom findById(Integer id);

    Integer update(Classroom classroom);

    Integer insert(Classroom classroom);

    Integer deleteById(Integer id);
}
