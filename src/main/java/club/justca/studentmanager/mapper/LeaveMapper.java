package club.justca.studentmanager.mapper;

import club.justca.studentmanager.entity.Leave;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveMapper {
    List<Leave> findAll();

    /**
     *
     * @param offset 从0开始
     * @param rows 查询的行数
     * @return 分页数据
     */
    List<Leave> findByPage(int offset, int rows);

    Leave findById(Integer id);

    List<Leave> findByStudentNum(String studentNum);

    List<Leave> findByState(String state);

    Integer deleteAll();

    Integer deleteById(Integer id);

    Integer update(Leave leave);

    Integer insert(Leave leave);
}
