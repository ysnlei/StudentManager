package club.justca.studentmanager.service.impl;

import club.justca.studentmanager.entity.Score;
import club.justca.studentmanager.mapper.ScoreMapper;
import club.justca.studentmanager.service.ScoreService;
import club.justca.studentmanager.utils.GetScoreUtil;
import lombok.Data;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.List;

@ConfigurationProperties(
        prefix = "just.jwgl"
)
@Service
@Data
public class ScoreServiceImpl implements ScoreService {
    String loginUrl;
    String scoreUrl;
    @Autowired
    ScoreMapper scoreMapper;

    @Override
    public List<Score> findByStudentNum(String studentNum) {
        return scoreMapper.findByStudentNum(studentNum);
    }

    /**
     * 更新前先删除已有成绩信息
     *
     * @param username 学生号 studentNum
     * @param password 教务系统密码
     * @return 数据库受影响的行数
     */
    @Override
    public Integer updateScore(String username, String password) {
        Element html = GetScoreUtil.getHtmlElement(loginUrl, scoreUrl, username, password);
        if (html == null) {
            return null;
        } else {
            Element table = GetScoreUtil.getScoreTable(html);
            List<Score> scoreList = GetScoreUtil.getScoreList(table, username);
            if (scoreList.size() != 0) {
                scoreMapper.deleteByStudentNum(username);   //先删除所有成绩
                int row = 0;
                for (Score score : scoreList) {
                    row += scoreMapper.insertScore(score);  //再更新成绩
                }
                return row;
            }
        }
        return 0;
    }

    @Override
    public Integer deleteByStudentNum(String studentNum) {
        return scoreMapper.deleteByStudentNum(studentNum);
    }
}
