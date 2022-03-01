package club.justca.studentmanager.mapper;

import club.justca.studentmanager.entity.RewardList;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RewardListMapper {
    List<RewardList> findAll();

    List<RewardList> findByPage(int offset, int rows);

    List<RewardList> findByMajor(String major);

    RewardList findById(Integer id);

    Integer deleteAll();

    Integer deleteById(Integer id);

    Integer insert(RewardList rewardList);

    Integer update(RewardList rewardList);
}
