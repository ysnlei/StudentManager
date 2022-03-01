package club.justca.studentmanager.controller.teacher;

import club.justca.studentmanager.entity.Score;
import club.justca.studentmanager.entity.Student;
import club.justca.studentmanager.entity.Teacher;
import club.justca.studentmanager.service.ScoreService;
import club.justca.studentmanager.service.StudentService;
import club.justca.studentmanager.utils.ScoreUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/teacher")
public class TeacherScoreController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private ScoreService scoreService;

    @GetMapping("/studentScore")
    public String studentScore(Model model, HttpSession session) {
        Teacher teacher = (Teacher) session.getAttribute("loginUser");
        model.addAttribute("major",teacher.getManageMajor());
        addPageInfo(model);
        List<Student> studentList;
        if(teacher.getManageMajor().equals("0")){
            studentList = studentService.findAll();
        }else {
            studentList= studentService.findStudentByMajor(teacher.getManageMajor());
        }
        List<Double> stuGPA = new ArrayList<>();
        for (Student student : studentList) {
            List<Score> scoreList = scoreService.findByStudentNum(student.getStudentNum());
            Double gpa = ScoreUtil.getGPA(scoreList);
            stuGPA.add(gpa);
        }
        model.addAttribute("studentGPA", stuGPA);
        model.addAttribute("studentList", studentList);
        return "teacher/stuInfo/studentScoreList";
    }

    @GetMapping("/studentScoreDetail/{stuNum}")
    public String studentScoreDetail(Model model,
                                     @PathVariable(value = "stuNum") String stuNum,
                                     HttpSession session) {
        Teacher teacher = (Teacher) session.getAttribute("loginUser");
        model.addAttribute("major",teacher.getManageMajor());
        addPageInfo(model);
        List<Score> scoreList = scoreService.findByStudentNum(stuNum);
        model.addAttribute("scoreList", scoreList);
        return "teacher/stuInfo/scoreDetail";
    }

    private void addPageInfo(Model model) {
        model.addAttribute("menu", "stuInfo");
        model.addAttribute("page", "studentScore");
    }

}
