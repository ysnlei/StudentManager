package club.justca.studentmanager.mapper;

import club.justca.studentmanager.entity.Score;
import club.justca.studentmanager.utils.GetScoreUtil;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ScoreMapperTest {
    @Autowired
    ScoreMapper scoreMapper;
    @Test
    public void insertScoreTest(){
//        String username = "188111545133";
//        String password = "yl2018608";
        String username = "188111545232";
        String password = "Yan957672665Pc";
        String loginUrl = "http://jwgl.just.edu.cn:8080/jsxsd/xk/LoginToXk";
        String dataUrl = "http://jwgl.just.edu.cn:8080/jsxsd/kscj/cjcx_list";
        Element html = GetScoreUtil.getHtmlElement(loginUrl, dataUrl, username, password);
        if(html==null){
            System.out.println("教务系统用户名或密码错误");
        }else {
            Element table = GetScoreUtil.getScoreTable(html);
            List<Score> scoreList = GetScoreUtil.getScoreList(table, username);
            for (Score score : scoreList) {
                System.out.println(score);
                Integer integer = scoreMapper.insertScore(score);
            }
        }
    }

    @Test
    public void findByStudentNumTest(){
        List<Score> scoreList = scoreMapper.findByStudentNum("188111545232");
        for (Score score : scoreList) {
            System.out.println(score);
        }
    }

    @Test
    public void deleteByStudentNumTest(){
        Integer deleteRow = scoreMapper.deleteByStudentNum("188111545232");
        System.out.println(deleteRow);
    }
}
