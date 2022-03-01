package club.justca.studentmanager.controller.teacher;

import club.justca.studentmanager.entity.*;
import club.justca.studentmanager.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/teacher")
public class TeacherStudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private ScoreService scoreService;
    @Autowired
    private RewardAndPunishService rewardAndPunishService;
    @Autowired
    private LeaveService leaveService;

@GetMapping({"/", "/index","/studentList"})
public String studentList(Model model,HttpSession session) {
    addPageInfo(model);
    List<Student> studentList;
    List<String> allMajor;
    Teacher teacher = (Teacher) session.getAttribute("loginUser");
    if(teacher.getManageMajor().equals("0")){   //如果是超级管理员就查看全部
        studentList = studentService.findAll();
        allMajor = studentService.findAllMajor();
        model.addAttribute("allMajor",allMajor);
    }else {
        studentList = studentService.findStudentByMajor(teacher.getManageMajor());
    }
    model.addAttribute("studentList", studentList);
    model.addAttribute("major",teacher.getManageMajor());
    return "teacher/stuInfo/studentList";
}

@PostMapping("/studentDelete")
@ResponseBody
public String deleteStudent(@RequestParam("studentNum") String studentNum) {
    Integer row = studentService.deleteByStudentNum(studentNum);
    if (row >= 1) {
        scoreService.deleteByStudentNum(studentNum);
        leaveService.findByStudentNum(studentNum);
        rewardAndPunishService.deleteByStudentNum(studentNum);
        return "true";
    } else {
        return "false";
    }
}

@GetMapping("/studentUpdate/{studentNum}")
public String studentUpdate(Model model,
                            @PathVariable("studentNum") String studentNum,
                            HttpSession session) {
    Teacher teacher = (Teacher) session.getAttribute("loginUser");
    model.addAttribute("major",teacher.getManageMajor());
    addPageInfo(model);
    Student student = studentService.findByStudentNum(studentNum);
    model.addAttribute("student", student);
    return "teacher/stuInfo/studentUpdate";
}

@PostMapping("/updateStudent")
@ResponseBody
public String updateStudent(Student student) {
    String studentNum = student.getStudentNum();
    Student studentBad = studentService.findByStudentNum(studentNum);
    student.setId(studentBad.getId());
    student.setPassword(studentBad.getPassword());
    Integer row = studentService.update(student);
    if (row >= 1) {
        return "true";
    } else {
        return "false";
    }
}

    @PostMapping("/resetPwd")
    @ResponseBody
    public String resetPwd(String studentNum,HttpSession session){
        Teacher teacher = (Teacher) session.getAttribute("loginUser");
        Student student = studentService.findByStudentNum(studentNum);
        Integer row = 0;
        if(teacher.getManageMajor().equals("0")){   //如果是超级管理员就查看全部
            row = studentService.resetPassword(studentNum);
        }else {
            if(teacher.getManageMajor().equals(student.getMajor())){
                row = studentService.resetPassword(studentNum);
            }else {
                return "false";
            }
        }
        if (row >= 1) {
            return "true";
        } else {
            return "false";
        }
    }

    private void addPageInfo(Model model) {
        model.addAttribute("menu", "stuInfo");
        model.addAttribute("page", "studentList");
    }
}
