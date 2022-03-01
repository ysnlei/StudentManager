package club.justca.studentmanager.service.impl;

import club.justca.studentmanager.entity.Leave;
import club.justca.studentmanager.mapper.LeaveMapper;
import club.justca.studentmanager.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveServiceImpl implements LeaveService {
    @Autowired
    LeaveMapper leaveMapper;

    @Override
    public List<Leave> findAll() {
        return leaveMapper.findAll();
    }

    @Override
    public Leave findById(Integer id) {
        return leaveMapper.findById(id);
    }

    @Override
    public List<Leave> findByState(String state) {
        return leaveMapper.findByState(state);
    }

    @Override
    public Integer deleteById(Integer id) {
        return leaveMapper.deleteById(id);
    }

    @Override
    public Integer update(Leave leave) {
        return leaveMapper.update(leave);
    }

    @Override
    public Integer insert(Leave leave) {
        return leaveMapper.insert(leave);
    }

    @Override
    public List<Leave> findByStudentNum(String stuNum) {
        return leaveMapper.findByStudentNum(stuNum);
    }
}
