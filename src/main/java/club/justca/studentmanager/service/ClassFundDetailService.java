package club.justca.studentmanager.service;

import club.justca.studentmanager.entity.ClassFundDetail;

import java.util.List;

public interface ClassFundDetailService {
    List<ClassFundDetail> findAll();

    ClassFundDetail findByClassFundListIdAndStudentNum(Integer classFundListId, String studentNum);

    List<ClassFundDetail> findByStudentNum(String studentNum);

    List<ClassFundDetail> findByClassFundListId(Integer classFundListId);

    ClassFundDetail findByTradeNo(String tradeNo);

    ClassFundDetail findByOutTradeNo(String outTradeNo);

    Integer insert(ClassFundDetail classFundDetail);

    Integer update(ClassFundDetail classFundDetail);
}
