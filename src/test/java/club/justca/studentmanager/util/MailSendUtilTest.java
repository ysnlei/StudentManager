package club.justca.studentmanager.util;

import club.justca.studentmanager.utils.MailSendUtil;
import club.justca.studentmanager.utils.RandomNumberUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MailSendUtilTest {
    @Autowired
    MailSendUtil mailSendUtil;
    @Test
    public void sendCodeTest(){
        mailSendUtil.sendCode("1045475239@qq.com", RandomNumberUtil.getCode());
    }
}
