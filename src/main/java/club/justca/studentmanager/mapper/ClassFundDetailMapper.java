package club.justca.studentmanager.mapper;

import club.justca.studentmanager.entity.ClassFundDetail;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassFundDetailMapper {
    List<ClassFundDetail> findAll();

    ClassFundDetail findByClassFundListIdAndStudentNum(Integer classFundListId, String studentNum);

    List<ClassFundDetail> findByClassFundListId(Integer classFundListId);

    List<ClassFundDetail> findByStudentNum(String studentNum);

    ClassFundDetail findByTradeNo(String tradeNo);

    ClassFundDetail findByOutTradeNo(String outTradeNo);

    Integer insert(ClassFundDetail classFundDetail);

    Integer update(ClassFundDetail classFundDetail);
}
