package club.justca.studentmanager.service.impl;

import club.justca.studentmanager.entity.ClassFundList;
import club.justca.studentmanager.mapper.ClassFundListMapper;
import club.justca.studentmanager.service.ClassFundListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassFundListServiceImpl implements ClassFundListService {
    @Autowired
    ClassFundListMapper classFundListMapper;

    @Override
    public List<ClassFundList> findAll() {
        return classFundListMapper.findAll();
    }

    @Override
    public List<ClassFundList> findByMajor(String major) {
        return classFundListMapper.findByMajor(major);
    }

    @Override
    public Integer insert(ClassFundList classFundList) {
        return classFundListMapper.insert(classFundList);
    }

    @Override
    public Integer update(ClassFundList classFundList) {
        return classFundListMapper.update(classFundList);
    }

    @Override
    public ClassFundList findById(Integer id) {
        return classFundListMapper.findById(id);
    }
}
