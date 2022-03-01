package club.justca.studentmanager.mapper;

import club.justca.studentmanager.entity.SchoolYear;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SchoolYearMapper {
    List<SchoolYear> findAll();

    List<SchoolYear> findByPage(int offset, int rows);

    SchoolYear findById(Integer id);

    Integer deleteById(Integer id);

    Integer insert(SchoolYear schoolYear);

    Integer update(SchoolYear schoolYear);

    Integer getCount();
}
