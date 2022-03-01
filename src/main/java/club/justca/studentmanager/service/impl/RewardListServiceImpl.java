package club.justca.studentmanager.service.impl;

import club.justca.studentmanager.entity.RewardList;
import club.justca.studentmanager.mapper.RewardListMapper;
import club.justca.studentmanager.service.RewardListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RewardListServiceImpl implements RewardListService {
    @Autowired
    RewardListMapper rewardListMapper;

    @Override
    public List<RewardList> findAll() {
        return rewardListMapper.findAll();
    }

    @Override
    public List<RewardList> findByPage(int page, int rows) {
        int offset = (page - 1) * rows;
        return rewardListMapper.findByPage(offset, rows);
    }

    @Override
    public List<RewardList> findByMajor(String major) {
        return rewardListMapper.findByMajor(major);
    }

    @Override
    public RewardList findById(Integer id) {
        return rewardListMapper.findById(id);
    }

    @Override
    public Integer deleteById(Integer id) {
        return rewardListMapper.deleteById(id);
    }

    @Override
    public Integer insert(RewardList rewardList) {
        return rewardListMapper.insert(rewardList);
    }

    @Override
    public Integer update(RewardList rewardList) {
        return rewardListMapper.update(rewardList);
    }
}
