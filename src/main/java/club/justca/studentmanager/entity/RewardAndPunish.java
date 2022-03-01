package club.justca.studentmanager.entity;

import lombok.Data;

@Data
public class RewardAndPunish {
    private Integer id;
    private String studentNum;
    private Integer rewardId;    //存放rewardList的id，如果为惩罚则为
    private String content;     //学生的申请理由
    private String state;
    private String type;
}
