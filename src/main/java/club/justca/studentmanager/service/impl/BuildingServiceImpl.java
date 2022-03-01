package club.justca.studentmanager.service.impl;

import club.justca.studentmanager.entity.Building;
import club.justca.studentmanager.mapper.BuildingMapper;
import club.justca.studentmanager.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuildingServiceImpl implements BuildingService {
    @Autowired
    BuildingMapper buildingMapper;

    @Override
    public List<Building> findAll() {
        return buildingMapper.findAll();
    }

    @Override
    public Building findById(Integer id) {
        return buildingMapper.findById(id);
    }

    @Override
    public Integer update(Building building) {
        return buildingMapper.update(building);
    }

    @Override
    public Integer deleteById(Integer id) {
        return buildingMapper.deleteById(id);
    }

    @Override
    public Integer insert(Building building) {
        return buildingMapper.insert(building);
    }
}
