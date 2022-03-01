package club.justca.studentmanager.VO;

import club.justca.studentmanager.entity.RewardAndPunish;
import club.justca.studentmanager.entity.RewardList;
import lombok.Data;

@Data
public class RewardAndPunishVO {
    private RewardAndPunish rewardAndPunish;
    private String stuName;
    private Double gpa;
    private RewardList rewardList;
}
