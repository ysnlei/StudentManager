package club.justca.studentmanager.service;

import club.justca.studentmanager.entity.Leave;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
    public class LeaveServiceTest {
    @Autowired
    LeaveService leaveService;
    @Test
    public void findByIdTest(){
        List<Leave> leaveList = leaveService.findAll();
        for (Leave leave : leaveList) {
            System.out.println(leave);
        }
    }

    @Test
    public void findByStateTest(){
        List<Leave> leaveList = leaveService.findByState("未审批");
        for (Leave leave : leaveList) {
            System.out.println(leave);
        }
    }
}
