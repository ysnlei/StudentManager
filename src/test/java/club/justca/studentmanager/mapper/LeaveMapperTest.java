package club.justca.studentmanager.mapper;

import club.justca.studentmanager.entity.Leave;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class LeaveMapperTest {
    @Autowired
    LeaveMapper leaveMapper;

    @Test
    public void findAllTest() {
        List<Leave> leaveList = leaveMapper.findAll();
        for (Leave leave : leaveList) {
            System.out.println(leave);
        }
    }

    @Test
    public void insertTest() throws ParseException {
        Leave leave = new Leave();
        leave.setStudentNum("188111545232");
        leave.setReason("去医院");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date1 = dateFormat.parse("20211016");
        Date date2 = dateFormat.parse("20211016");
        leave.setStartTime(date1);
        leave.setEndTime(date2);
        leave.setState("审核中");
        System.out.println(leave);
        Integer insert = leaveMapper.insert(leave);
        System.out.println(insert);
    }

    @Test
    public void findByIdTest() {
        Leave leave = leaveMapper.findById(1);
        System.out.println(leave);
    }

    @Test
    public void findByStudentNum() {
        List<Leave> leaveList = leaveMapper.findByStudentNum("188111545232");
        for (Leave leave : leaveList) {
            System.out.println(leave);
        }
    }

    @Test
    public void findByPageTest() {
        List<Leave> leaveList = leaveMapper.findByPage(0, 2);
        System.out.println(leaveList.size());
        for (Leave leave : leaveList) {
            System.out.println(leave);
        }
    }

    @Test
    public void deleteAllTest() {
        Integer deleteRows = leaveMapper.deleteAll();
        System.out.println(deleteRows);
    }

    @Test
    public void deleteByIdTest() {
        Integer deleteRows = leaveMapper.deleteById(1);
        System.out.println(deleteRows);
    }

    @Test
    public void updateTest() throws ParseException {
        Leave leave = new Leave();
        leave.setId(1);
        leave.setStudentNum("188111545232");
        leave.setReason("去医院");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date1 = dateFormat.parse("20211016");
        Date date2 = dateFormat.parse("20211016");
        leave.setStartTime(date1);
        leave.setEndTime(date2);
        leave.setState("审核通过");
        System.out.println(leave);
        Integer update = leaveMapper.update(leave);
        System.out.println(update);
    }
}
