package club.justca.studentmanager.service.impl;

import club.justca.studentmanager.entity.Notification;
import club.justca.studentmanager.mapper.NotificationMapper;
import club.justca.studentmanager.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {
    @Autowired
    NotificationMapper notificationMapper;

    @Override
    public List<Notification> findAll() {
        return notificationMapper.findAll();
    }

    @Override
    public List<Notification> findByPage(int page, int num) {
        int offset = (page - 1) * num;
        return notificationMapper.findByPage(offset, num);
    }

    @Override
    public Notification findById(Integer id) {
        return notificationMapper.findById(id);
    }

    @Override
    public Integer deleteById(Integer id) {
        return notificationMapper.deleteById(id);
    }

    @Override
    public Integer update(Notification notification) {
        return notificationMapper.update(notification);
    }

    @Override
    public Integer insert(Notification notification) {
        return notificationMapper.insert(notification);
    }

    @Override
    public Integer getCount() {
        return notificationMapper.getCount();
    }
}
