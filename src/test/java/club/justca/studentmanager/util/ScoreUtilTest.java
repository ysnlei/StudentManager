package club.justca.studentmanager.util;

import club.justca.studentmanager.entity.Score;
import club.justca.studentmanager.service.ScoreService;
import club.justca.studentmanager.utils.ScoreUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ScoreUtilTest {
    @Autowired
    ScoreService scoreService;
    @Test
    public void getGPATest(){
        List<Score> scoreList = scoreService.findByStudentNum("188111545133");
        Double gpa = ScoreUtil.getGPA(scoreList);
        System.out.println(gpa);
    }
}
