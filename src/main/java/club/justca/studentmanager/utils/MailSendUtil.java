package club.justca.studentmanager.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component
public class MailSendUtil {
    @Autowired
    private MailSender mailSender;

    /**
     * @param emailAddress 目标邮箱地址
     * @param code 验证码
     */
    public void sendCode(String emailAddress,String code) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setSubject("学生信息管理系统找回密码");
        msg.setFrom("managerstudent@163.com");
        msg.setTo(emailAddress);
        msg.setText("您正在使用学生信息管理系统的找回密码功能，验证码为：" +
                code +
                "\n请不要将验证码告诉其他人，以免造成不必要的损失。");
        mailSender.send(msg);
    }

    public void sendFund(String emailAddress){
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setSubject("学生信息管理系统班费催缴");
        msg.setFrom("managerstudent@163.com");
        msg.setTo(emailAddress);
        msg.setText("您有一笔班费需要缴纳，请登录学生信息管理系统进行查看！");
        mailSender.send(msg);
    }
}
