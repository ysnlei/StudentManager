package club.justca.studentmanager.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ScoreServiceTest {
    @Autowired
    ScoreService scoreService;

    @Test
    public void getPropertiesTest() {
        Integer row = scoreService.updateScore("188111545232", "Yan957672665Pc");
        System.out.println(row);
    }

    @Test
    public void getScoreTest(){
        Integer gUhang19980409 = scoreService.updateScore("188111545239", "GUhang19980409");
        System.out.println(gUhang19980409);
    }
}
