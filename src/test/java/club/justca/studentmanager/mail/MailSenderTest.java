package club.justca.studentmanager.mail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import javax.annotation.Resource;

@SpringBootTest
public class MailSenderTest {

    @Autowired
    MailSender mailSender;
    @Test
    public void mailSenderTest(){
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setSubject("111");
        msg.setFrom("managerstudent@163.com");
        msg.setTo("2943338200@qq.com");
        msg.setText("111");
        mailSender.send(msg);
    }
}
