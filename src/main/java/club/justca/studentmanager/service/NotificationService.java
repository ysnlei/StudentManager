package club.justca.studentmanager.service;

import club.justca.studentmanager.entity.Notification;

import java.util.List;

public interface NotificationService {

    List<Notification> findAll();

    /**
     * 分页查询
     *
     * @param page 页数，1为第一页，从1开始
     * @param num  查询的数量
     * @return 返回第几页的数据
     */
    List<Notification> findByPage(int page, int num);

    Notification findById(Integer id);

    Integer deleteById(Integer id);

    Integer update(Notification notification);

    Integer insert(Notification notification);

    Integer getCount();
}
