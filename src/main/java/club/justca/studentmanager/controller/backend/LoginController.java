package club.justca.studentmanager.controller.backend;

import club.justca.studentmanager.VO.LoginError;
import club.justca.studentmanager.entity.Student;
import club.justca.studentmanager.entity.Teacher;
import club.justca.studentmanager.mapper.StudentMapper;
import club.justca.studentmanager.service.StudentService;
import club.justca.studentmanager.service.TeacherService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Enumeration;

@Controller
public class LoginController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @GetMapping({"/login", "/"})   //登录界面
    public String login(HttpSession session) {
        if (session.getAttribute("loginUser") != null) {
            String loginRole = (String) session.getAttribute("loginRole");
            if (loginRole.equals("student")) {
                return "redirect:student/index";
            } else if (loginRole.equals("teacher")) {
                return "redirect:teacher/index";
            }
        }
        return "login";
    }

    @PostMapping("/userLogin")
    public String userLogin(@RequestParam("username") String username,
                            @RequestParam("password") String password,
                            @RequestParam("userType") Integer userType,
                            HttpSession session) {
        session.removeAttribute("loginError");
        if (userType.equals(1)) {
            Student student = studentService.findByStudentNumAndPassword(username, password);
            if (student == null) {
                LoginError loginError = new LoginError();
                loginError.setErrorResult("用户名或密码错误");
                loginError.setUsername(username);
                loginError.setRole(userType);
                session.setAttribute("loginError", loginError);
                return "redirect:/login";
            }
            session.setAttribute("loginUser", student);
            session.setAttribute("loginRole", "student");
            return "redirect:/student/index";
        } else {
            Teacher teacher = teacherService.findByTeacherNumAndPassword(username, password);
            if (teacher == null) {
                LoginError loginError = new LoginError();
                loginError.setErrorResult("用户名或密码错误");
                loginError.setUsername(username);
                loginError.setRole(userType);
                session.setAttribute("loginError", loginError);
                return "redirect:/login";
            }
            session.setAttribute("loginUser", teacher);
            session.setAttribute("loginRole", "teacher");
            return "redirect:/teacher/index";
        }

    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        Enumeration<String> attributeNames = session.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            session.removeAttribute(attributeNames.nextElement());
        }
        LoginError loginError = new LoginError();
        loginError.setErrorResult("登出成功！");
        session.setAttribute("loginError", loginError);
        return "redirect:/login";
    }

    @GetMapping("/forgetPassword")
    public String forgetPassword(){
        return "forgetPassword";
    }
}
