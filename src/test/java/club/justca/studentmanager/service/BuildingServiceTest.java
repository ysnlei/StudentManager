package club.justca.studentmanager.service;

import club.justca.studentmanager.entity.Building;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class BuildingServiceTest {
    @Autowired
    BuildingService buildingService;

    @Test
    public void findAllTest() {
        List<Building> buildingList = buildingService.findAll();
        for (Building building : buildingList) {
            System.out.println(building);
        }
    }

    @Test
    public void findByIdTest() {
        Building building = buildingService.findById(5);
        System.out.println(building);
    }

    @Test
    public void updateTest() {
        Building building = buildingService.findById(5);
        building.setTotalFloor(10);
        Integer row = buildingService.update(building);
        assert row >= 1;
    }

    @Test
    public void deleteById() {
        Integer row = buildingService.deleteById(5);
        assert row >= 1;
    }

    @Test
    public void insert() {
        Building building = new Building();
        building.setBuildingName("12");
        building.setTotalFloor(33);
        Integer row = buildingService.insert(building);
        assert row >= 1;
    }
}
