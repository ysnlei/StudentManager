package club.justca.studentmanager.controller.backend;

import club.justca.studentmanager.VO.EmailSendData;
import club.justca.studentmanager.entity.Student;
import club.justca.studentmanager.entity.Teacher;
import club.justca.studentmanager.service.StudentService;
import club.justca.studentmanager.service.TeacherService;
import club.justca.studentmanager.utils.MailSendUtil;
import club.justca.studentmanager.utils.RandomNumberUtil;
import club.justca.studentmanager.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@RestController
@RequestMapping("/mail")
public class MailSendController {
    @Autowired
    private MailSendUtil mailSendUtil;
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    RedisUtil redisUtil;

    /**
     * @param username 用户名
     * @param role     角色0代表学生，1代表老师
     */
    @PostMapping("/getCode")
    public EmailSendData getCode(String username, Integer role) {
        EmailSendData emailSendData = new EmailSendData();
        //此处添加缓存，判断是否有此用户的验证码信息
        if (redisUtil.get(username + "send") != null) {
            emailSendData.setCode(1);
            emailSendData.setReason("请1分钟后再发送！");
            return emailSendData;
        }
        if (role != null) {
            String email = null;
            if (role.equals(0)) {
                Student student = studentService.findByStudentNum(username);
                if (student != null) {
                    email = student.getEmail();
                } else {
                    emailSendData.setCode(1);
                    emailSendData.setReason("用户名不存在！");
                    return emailSendData;
                }
            } else if (role.equals(1)) {
                Teacher teacher = teacherService.findByTeacherNum(username);
                if (teacher != null) {
                    email = teacher.getEmail();
                }
            }
            if (email == null || email.isEmpty()) {
                emailSendData.setCode(1);
                emailSendData.setReason("未配置密保邮箱！");
            } else {
                String code = RandomNumberUtil.getCode();
                System.out.println(email);
                try {
                    mailSendUtil.sendCode(email, code);
                    System.out.println(code);
                    //此处添加将验证码添加到缓存中逻辑
                    redisUtil.set(username, code, 5);
                    redisUtil.set(username + "send", "已发送", 1);
                    emailSendData.setReason("邮件发送成功！");
                } catch (Exception e) {
                    emailSendData.setCode(1);
                    emailSendData.setReason("邮件发送失败！");
                }
            }
        }
        return emailSendData;
    }

    @PostMapping("/checkCode")
    public EmailSendData checkCode(String username,
                                   Integer role,
                                   String code,
                                   String password,
                                   String password1) {
        EmailSendData emailSendData = new EmailSendData();
        if(!password.equals(password1)){
            emailSendData.setCode(1);
            emailSendData.setReason("两次密码不一致");
            return emailSendData;
        }
        String savedCode = redisUtil.get(username);
        if (savedCode == null) {
            emailSendData.setCode(1);
            emailSendData.setReason("未发送邮件或验证码已过期！");
            return emailSendData;
        }
        if (code.equals(savedCode)) {
            redisUtil.delete(username);
            redisUtil.delete(username+"send");
            if(role==1){
                Teacher teacher = teacherService.findByTeacherNum(username);
                if(teacher==null){
                    emailSendData.setCode(1);
                    emailSendData.setReason("用户名不存在！");
                    return emailSendData;
                }
                teacher.setPassword(password);
                teacherService.update(teacher);
            }else if(role==0){
                Student student = studentService.findByStudentNum(username);
                if(student==null){
                    emailSendData.setCode(1);
                    emailSendData.setReason("用户名不存在！");
                    return emailSendData;
                }
                student.setPassword(password);
                studentService.update(student);
            }else {
                emailSendData.setCode(1);
                emailSendData.setReason("role参数错误！");
                return emailSendData;
            }
            emailSendData.setReason("验证成功");
            return emailSendData;
        } else {
            emailSendData.setCode(1);
            emailSendData.setReason("验证码错误！");
            return emailSendData;
        }
    }

}
