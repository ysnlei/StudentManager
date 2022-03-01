package club.justca.studentmanager.service;

import club.justca.studentmanager.entity.SchoolYear;

import java.util.List;

public interface SchoolYearService {
    List<SchoolYear> findAll();

    SchoolYear findById(Integer id);

    List<SchoolYear> findByPage(int page, int row);

    Integer deleteById(Integer id);

    Integer insert(SchoolYear schoolYear);

    Integer update(SchoolYear schoolYear);

    Integer getCount();
}
