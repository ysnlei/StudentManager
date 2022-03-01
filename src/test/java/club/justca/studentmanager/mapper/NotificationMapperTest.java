package club.justca.studentmanager.mapper;

import club.justca.studentmanager.entity.Notification;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class NotificationMapperTest {
    @Autowired
    NotificationMapper notificationMapper;

    @Test
    public void findAllTest() {
        List<Notification> notificationList = notificationMapper.findAll();
        for (Notification notification : notificationList) {
            System.out.println(notification.getCreateTime());
        }
    }

    @Test
    public void findByPageTest(){
        List<Notification> notificationList = notificationMapper.findByPage(0, 10);
        for (Notification notification : notificationList) {
            System.out.println(notification.getCreateTime());
        }
    }

    @Test
    public void findLatestNotificationTest(){
        List<Notification> latestNotification = notificationMapper.findLatestNotification(10);
        for (Notification notification : latestNotification) {
            System.out.println(notification.getId());
        }
    }

    @Test
    public void findByIdTest(){
        Notification notification = notificationMapper.findById(2);
        System.out.println(notification);
    }

    @Test
    public void deleteByIdTest(){
        Integer delete = notificationMapper.deleteById(1);
        System.out.println(delete);
    }

    @Test
    public void getCountTest(){
        Integer count = notificationMapper.getCount();
        System.out.println(count);
    }
}
