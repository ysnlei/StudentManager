package club.justca.studentmanager.util;

import club.justca.studentmanager.utils.RandomNumberUtil;
import org.junit.jupiter.api.Test;

public class RandomNumberUtilTest {
    @Test
    public void getCodeTest(){
        String code = RandomNumberUtil.getCode();
        System.out.println(code);
    }
}
