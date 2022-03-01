package club.justca.studentmanager.service;

import club.justca.studentmanager.entity.ClassFundList;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ClassFundListService {
    List<ClassFundList> findAll();

    List<ClassFundList> findByMajor(String major);

    Integer insert(ClassFundList classFundList);

    Integer update(ClassFundList classFundList);

    ClassFundList findById(Integer id);
}
