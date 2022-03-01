package club.justca.studentmanager.service;

import club.justca.studentmanager.entity.Leave;

import java.util.List;

public interface LeaveService {
    List<Leave> findAll();

    Leave findById(Integer id);

    List<Leave> findByState(String state);

    Integer deleteById(Integer id);

    Integer update(Leave leave);

    Integer insert(Leave leave);

    List<Leave> findByStudentNum(String stuNum);

}
