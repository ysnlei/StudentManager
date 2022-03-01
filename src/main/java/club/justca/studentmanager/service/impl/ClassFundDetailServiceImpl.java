package club.justca.studentmanager.service.impl;

import club.justca.studentmanager.entity.ClassFundDetail;
import club.justca.studentmanager.mapper.ClassFundDetailMapper;
import club.justca.studentmanager.service.ClassFundDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassFundDetailServiceImpl implements ClassFundDetailService {
    @Autowired
    ClassFundDetailMapper classFundDetailMapper;

    @Override
    public List<ClassFundDetail> findAll() {
        return classFundDetailMapper.findAll();
    }

    @Override
    public ClassFundDetail findByClassFundListIdAndStudentNum(Integer classFundListId, String studentNum) {
        return classFundDetailMapper.findByClassFundListIdAndStudentNum(classFundListId, studentNum);
    }

    @Override
    public List<ClassFundDetail> findByStudentNum(String studentNum) {
        return classFundDetailMapper.findByStudentNum(studentNum);
    }

    @Override
    public List<ClassFundDetail> findByClassFundListId(Integer classFundListId) {
        return classFundDetailMapper.findByClassFundListId(classFundListId);
    }

    @Override
    public ClassFundDetail findByTradeNo(String tradeNo) {
        return classFundDetailMapper.findByTradeNo(tradeNo);
    }

    @Override
    public ClassFundDetail findByOutTradeNo(String outTradeNo) {
        return classFundDetailMapper.findByOutTradeNo(outTradeNo);
    }

    @Override
    public Integer insert(ClassFundDetail classFundDetail) {
        return classFundDetailMapper.insert(classFundDetail);
    }

    @Override
    public Integer update(ClassFundDetail classFundDetail) {
        return classFundDetailMapper.update(classFundDetail);
    }
}
