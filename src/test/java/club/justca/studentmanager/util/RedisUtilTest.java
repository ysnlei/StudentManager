package club.justca.studentmanager.util;

import club.justca.studentmanager.utils.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RedisUtilTest {
    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void setTest() {
        redisUtil.set("111", "222", 1);
    }

    @Test
    public void getTest() {
        redisUtil.get("111");
    }

    @Test
    public void deleteTest() {
        Boolean delete = redisUtil.delete("111");
        System.out.println(delete);
    }

    @Test
    public void updateTest() {
        redisUtil.update("111", "333", 1);
    }

}
