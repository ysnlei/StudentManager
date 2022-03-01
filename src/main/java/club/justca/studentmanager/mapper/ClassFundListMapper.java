package club.justca.studentmanager.mapper;

import club.justca.studentmanager.entity.ClassFundList;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassFundListMapper {
    List<ClassFundList> findAll();

    ClassFundList findById(Integer id);

    List<ClassFundList> findByMajor(String major);

    Integer insert(ClassFundList classFundList);

    Integer update(ClassFundList classFundList);
}
