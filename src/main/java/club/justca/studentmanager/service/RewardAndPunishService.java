package club.justca.studentmanager.service;

import club.justca.studentmanager.VO.RewardAndPunishVO;
import club.justca.studentmanager.entity.RewardAndPunish;

import java.util.List;

public interface RewardAndPunishService {
    List<RewardAndPunish> findAll();

    List<RewardAndPunish> findByPage(int page, int row);

//    List<RewardAndPunish> findByRewardId(Integer rewardId);

    List<RewardAndPunishVO> findByRewardId(Integer rewardId);

    List<RewardAndPunish> findByStudentNum(String studentNum);

    Integer deleteById(Integer id);

    Integer deleteByStudentNum(String studentNum);

    Integer update(RewardAndPunish rewardAndPunish);

    Integer insert(RewardAndPunish rewardAndPunish);

    RewardAndPunish findByStudentNumAndRewardId(String studentNum, Integer rewardId);

    List<RewardAndPunish> findByType(String type);
}
