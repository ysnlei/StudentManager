package club.justca.studentmanager.mapper;

import club.justca.studentmanager.entity.RewardAndPunish;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RewardAndPunishMapper {
    List<RewardAndPunish> findAll();

    List<RewardAndPunish> findByPage(int offset, int rows);

    List<RewardAndPunish> findByStudentNum(String studentNum);

    List<RewardAndPunish> findByRewardId(Integer rewardId);

    RewardAndPunish findById(Integer id);

    Integer deleteAll();

    Integer deleteById(Integer id);

    Integer deleteByStudentNum(String studentNum);

    Integer insert(RewardAndPunish rewardAndPunish);

    Integer update(RewardAndPunish rewardAndPunish);

    RewardAndPunish findByStudentNumAndRewardId(String studentNum,Integer rewardId);

    List<RewardAndPunish> findByType(String type);
}
