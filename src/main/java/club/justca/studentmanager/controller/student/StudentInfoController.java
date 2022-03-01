package club.justca.studentmanager.controller.student;


import club.justca.studentmanager.entity.Student;
import club.justca.studentmanager.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/student")
public class StudentInfoController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/info")
    public String info(Model model, HttpSession session) {
        Student student = (Student) session.getAttribute("loginUser");
        model.addAttribute("student", student);
        return "student/info";
    }

    @PostMapping("/info_update")
    @ResponseBody
    public String infoUpdate(@RequestParam("telephone") String telephone
            , HttpSession session) {
        Student student = (Student) session.getAttribute("loginUser");
        student.setTelephone(telephone);
        Integer row = studentService.update(student);
        if (row >= 1) {
            session.setAttribute("loginUser", student);
            return "true";
        } else {
            return "false";
        }
    }

    @PostMapping("/changePwd")
    @ResponseBody
    public Integer changePwd(HttpSession session,
                             @RequestParam("oldPwd") String oldPwd,
                             @RequestParam("newPwd") String newPwd,
                             @RequestParam("confirmPwd") String confirmPwd) {
        System.out.println(oldPwd);
        System.out.println(newPwd);
        System.out.println(confirmPwd);
        Student student = (Student) session.getAttribute("loginUser");
        System.out.println(student);
        if (!student.getPassword().equals(oldPwd)) {
            return 1;   //旧密码错误
        } else if (!newPwd.equals(confirmPwd)) {
            return 2;   //两次密码不一致
        } else {
            student.setPassword(newPwd);
            Integer update = studentService.update(student);
            if (update >= 1) {
                session.setAttribute("loginUser", student);
                return 3;   //修改成功
            }
            return 4;
        }
    }
}
