package club.justca.studentmanager.mapper;

import club.justca.studentmanager.entity.Building;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuildingMapper {
    List<Building> findAll();

    Building findById(Integer id);

    Integer update(Building building);

    Integer insert(Building building);

    Integer deleteById(Integer id);
}
