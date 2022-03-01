package club.justca.studentmanager.service;

import club.justca.studentmanager.entity.RewardList;

import java.util.List;

public interface RewardListService {
    List<RewardList> findAll();

    List<RewardList> findByPage(int page, int rows);

    List<RewardList> findByMajor(String major);

    RewardList findById(Integer id);

    Integer deleteById(Integer id);

    Integer insert(RewardList rewardList);

    Integer update(RewardList rewardList);
}
