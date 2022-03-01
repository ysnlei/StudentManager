package club.justca.studentmanager.service;

import club.justca.studentmanager.entity.Building;

import java.util.List;

public interface BuildingService {
    List<Building> findAll();

    Building findById(Integer id);

    Integer update(Building building);

    Integer deleteById(Integer id);

    Integer insert(Building building);
}
