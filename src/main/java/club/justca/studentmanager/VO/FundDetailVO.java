package club.justca.studentmanager.VO;

import club.justca.studentmanager.entity.ClassFundDetail;
import club.justca.studentmanager.entity.Student;
import lombok.Data;

@Data
public class FundDetailVO {
    private Student student;
    private ClassFundDetail classFundDetail;
}
