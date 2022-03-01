package club.justca.studentmanager.controller.admin;

import club.justca.studentmanager.entity.Student;
import club.justca.studentmanager.entity.Teacher;
import club.justca.studentmanager.service.StudentService;
import club.justca.studentmanager.service.TeacherService;
import com.sun.deploy.net.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/teacher")
public class AdminTeacherController {
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;

    @GetMapping("/teacherManage")
    public String teacherManage(Model model, HttpSession session){
        Teacher teacher = (Teacher) session.getAttribute("loginUser");
        addPageInfo(model);
        List<Teacher> teacherList;
        if(teacher.getManageMajor().equals("0")){   //如果是超级管理员就查看全部
            model.addAttribute("major",teacher.getManageMajor());
            addPageInfo(model);
            teacherList = teacherService.findAllExceptSuperAdmin();
            model.addAttribute("teacherList",teacherList);
            return "teacher/teacherInfo/teacherList";
        }else {
            return "redirect:/teacher/index";
        }
    }

    @GetMapping("/teacherUpdate/{teacherNum}")
    public String teacherUpdate(Model model,
                                HttpSession session,
                                @PathVariable("teacherNum")String teacherNum){
        Teacher teacher = (Teacher) session.getAttribute("loginUser");
        addPageInfo(model);
        List<Teacher> teacherList;
        if(teacher.getManageMajor().equals("0")){   //如果是超级管理员就查看全部
            model.addAttribute("major",teacher.getManageMajor());   //用于前端判断是否是超级管理员
            addPageInfo(model);
            Teacher teacherUpdate = teacherService.findByTeacherNum(teacherNum);
            model.addAttribute("teacherUpdate",teacherUpdate);
            return "teacher/teacherInfo/teacherUpdate";
        }else {
            return "redirect:/teacher/index";
        }
    }

    @GetMapping("/teacherAdd")
    public String teacherAdd(Model model,
                                HttpSession session){
        Teacher teacher = (Teacher) session.getAttribute("loginUser");
        addPageInfo(model);
        if(teacher.getManageMajor().equals("0")){   //如果是超级管理员就查看全部
            model.addAttribute("major",teacher.getManageMajor());   //用于前端判断是否是超级管理员
            addPageInfo(model);
            return "teacher/teacherInfo/teacherAdd";
        }else {
            return "redirect:/teacher/index";
        }
    }

    @PostMapping("/insertTeacher")
    @ResponseBody
    public String insertTeacher(Teacher teacher,HttpSession session){
        Teacher loginTeacher = (Teacher) session.getAttribute("loginUser");
        if(loginTeacher.getManageMajor().equals("0")){   //如果是超级管理员就可以修改
            System.out.println(teacher);
            teacher.setPassword("123456");
            Integer row = teacherService.insert(teacher);
            if(row>0){
                return "true";
            }else {
                return "false";
            }
        }else {
            return "false";
        }
    }

    @PostMapping("/updateTeacher")
    @ResponseBody
    public String updateTeacher(Teacher teacher,HttpSession session){
        Teacher loginTeacher = (Teacher) session.getAttribute("loginUser");
        if(loginTeacher.getManageMajor().equals("0")){   //如果是超级管理员就可以修改
            Teacher oldTeacher = teacherService.findByTeacherNum(teacher.getTeacherNum());
            teacher.setId(oldTeacher.getId());
            teacher.setPassword(oldTeacher.getPassword());
            Integer row = teacherService.update(teacher);
            if(row>0){
                return "true";
            }else {
                return "false";
            }
        }else {
            return "false";
        }
    }

    @PostMapping("/deleteTeacher")
    @ResponseBody
    public String deleteTeacher(String teacherNum,HttpSession session){
        Teacher loginTeacher = (Teacher) session.getAttribute("loginUser");
        if(loginTeacher.getManageMajor().equals("0")){   //如果是超级管理员就可以修改
            Integer row = teacherService.deleteByTeacherNum(teacherNum);
            if(row>0){
                return "true";
            }
        }
        return "false";
    }

    @PostMapping("/resetTeacherPwd")
    @ResponseBody
    public String resetTeacherPwd(String teacherNum,HttpSession session){
        Teacher loginTeacher = (Teacher) session.getAttribute("loginUser");
        if(loginTeacher.getManageMajor().equals("0")){   //如果是超级管理员就可以修改
            Teacher teacher = teacherService.findByTeacherNum(teacherNum);
            if(teacher!=null){
                teacher.setPassword("123456");
                Integer row = teacherService.update(teacher);
                if(row>0){
                    return "true";
                }
            }
        }
        return "false";
    }

    private void addPageInfo(Model model) {
        model.addAttribute("menu", "teacherInfo");
    }
}
