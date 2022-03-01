package club.justca.studentmanager.service.impl;

import club.justca.studentmanager.VO.RewardAndPunishVO;
import club.justca.studentmanager.entity.RewardAndPunish;
import club.justca.studentmanager.entity.Score;
import club.justca.studentmanager.entity.Student;
import club.justca.studentmanager.mapper.RewardAndPunishMapper;
import club.justca.studentmanager.mapper.ScoreMapper;
import club.justca.studentmanager.mapper.StudentMapper;
import club.justca.studentmanager.service.RewardAndPunishService;
import club.justca.studentmanager.utils.ScoreUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RewardAndPunishServiceImpl implements RewardAndPunishService {
    @Autowired
    RewardAndPunishMapper rewardAndPunishMapper;

    @Override
    public List<RewardAndPunish> findAll() {
        return rewardAndPunishMapper.findAll();
    }

    @Override
    public List<RewardAndPunish> findByPage(int page, int row) {
        int offset = (page - 1) * row;
        return rewardAndPunishMapper.findByPage(offset, row);
    }

    @Autowired
    StudentMapper studentMapper;
    @Autowired
    ScoreMapper scoreMapper;

    @Override
    public List<RewardAndPunishVO> findByRewardId(Integer rewardId) {     //查看申请学生
        List<RewardAndPunish> rewardAndPunishList = rewardAndPunishMapper.findByRewardId(rewardId);
        List<RewardAndPunishVO> rewardAndPunishVOList = new ArrayList<>();
        for (RewardAndPunish rewardAndPunish : rewardAndPunishList) {
            RewardAndPunishVO rewardAndPunishVO = new RewardAndPunishVO();
            rewardAndPunishVO.setRewardAndPunish(rewardAndPunish);
            String studentNum = rewardAndPunish.getStudentNum();
            Student student = studentMapper.findByStudentNum(studentNum);
            if (student != null) {
                rewardAndPunishVO.setStuName(student.getName());
                List<Score> studentScore = scoreMapper.findByStudentNum(studentNum);
                if (studentScore != null && studentScore.size() > 0) {
                    Double gpa = ScoreUtil.getGPA(studentScore);
                    rewardAndPunishVO.setGpa(gpa);
                } else {
                    continue;
                }
                rewardAndPunishVOList.add(rewardAndPunishVO);
            }
        }
        rewardAndPunishVOList.sort((o1, o2) -> {       //lambda表达式
            if (o1.getGpa() > o2.getGpa()) {            //自定义比较器
                return 1;
            }
            return 0;
        });

        return rewardAndPunishVOList;
    }

    @Override
    public List<RewardAndPunish> findByStudentNum(String studentNum) {
        return rewardAndPunishMapper.findByStudentNum(studentNum);
    }

    @Override
    public Integer deleteById(Integer id) {
        return rewardAndPunishMapper.deleteById(id);
    }

    @Override
    public Integer deleteByStudentNum(String studentNum) {
        return rewardAndPunishMapper.deleteByStudentNum(studentNum);
    }

    @Override
    public Integer update(RewardAndPunish rewardAndPunish) {
        return rewardAndPunishMapper.update(rewardAndPunish);
    }

    @Override
    public Integer insert(RewardAndPunish rewardAndPunish) {
        return rewardAndPunishMapper.insert(rewardAndPunish);
    }

    @Override
    public RewardAndPunish findByStudentNumAndRewardId(String studentNum, Integer rewardId) {
        return rewardAndPunishMapper.findByStudentNumAndRewardId(studentNum, rewardId);
    }

    @Override
    public List<RewardAndPunish> findByType(String type) {
        return rewardAndPunishMapper.findByType(type);
    }
}
