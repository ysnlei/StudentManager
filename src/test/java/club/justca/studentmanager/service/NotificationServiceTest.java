package club.justca.studentmanager.service;

import club.justca.studentmanager.entity.Notification;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class NotificationServiceTest {
    @Autowired
    NotificationService notificationService;

    @Test
    public void findByPageTest() {
        List<Notification> notificationList = notificationService.findByPage(2, 4); //获取第二页的四条数据
        for (Notification notification : notificationList) {
            System.out.println(notification.getId());
        }
    }
}
