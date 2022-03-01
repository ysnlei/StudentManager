package club.justca.studentmanager.service.impl;

import club.justca.studentmanager.entity.SchoolYear;
import club.justca.studentmanager.mapper.SchoolYearMapper;
import club.justca.studentmanager.service.SchoolYearService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolYearServiceImpl implements SchoolYearService {
    @Autowired
    SchoolYearMapper schoolYearMapper;

    @Override
    public List<SchoolYear> findAll() {
        return schoolYearMapper.findAll();
    }

    @Override
    public SchoolYear findById(Integer id) {
        return schoolYearMapper.findById(id);
    }

    @Override
    public List<SchoolYear> findByPage(int page, int row) {
        int offset = (page - 1) * row;
        return schoolYearMapper.findByPage(offset, row);
    }

    @Override
    public Integer deleteById(Integer id) {
        return schoolYearMapper.deleteById(id);
    }

    @Override
    public Integer insert(SchoolYear schoolYear) {
        return schoolYearMapper.insert(schoolYear);
    }

    @Override
    public Integer update(SchoolYear schoolYear) {
        return schoolYearMapper.update(schoolYear);
    }

    @Override
    public Integer getCount() {
        return schoolYearMapper.getCount();
    }
}
