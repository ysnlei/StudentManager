package club.justca.studentmanager.mapper;

import club.justca.studentmanager.entity.Notification;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationMapper {

    List<Notification> findAll();

    /**
     * @param offset 距离第一条数据的偏移量
     * @param rows   行数
     * @return 从(offset + 1)行开始的rows条数据
     */
    List<Notification> findByPage(int offset, int rows);

    /**
     * @param num 查找最新的通知数量
     * @return 最新的num条通知
     */
    List<Notification> findLatestNotification(int num);

    Notification findById(int id);

    Integer deleteById(int id);

    Integer update(Notification notification);

    Integer insert(Notification notification);

    /**
     * @return 数据库中通知的数量
     */
    Integer getCount();
}
