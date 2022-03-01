package club.justca.studentmanager.controller.student;

import club.justca.studentmanager.entity.Score;
import club.justca.studentmanager.entity.Student;
import club.justca.studentmanager.service.ScoreService;
import club.justca.studentmanager.utils.ScoreUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentScoreController {
    @Autowired
    private ScoreService scoreService;

    @GetMapping("/score")
    public String scoreList(Model model, HttpSession session) {
        model.addAttribute("page", "score");
        Student student = (Student) session.getAttribute("loginUser");
        String studentNum = student.getStudentNum();
        List<Score> scoreList = scoreService.findByStudentNum(studentNum);
        model.addAttribute("scoreList", scoreList);
        model.addAttribute("listSize", scoreList.size());
        if (scoreList.size() != 0) {
            Double gpa = ScoreUtil.getGPA(scoreList);
            model.addAttribute("gpa", gpa);
        }
        return "student/scoreList";
    }

    @PostMapping("/getScore")
    @ResponseBody
    public String getScore(HttpSession session, @RequestParam("jwpwd") String jwpwd) {
        Student student = (Student) session.getAttribute("loginUser");
        String studentNum = student.getStudentNum();
        Integer integer = scoreService.updateScore(studentNum, jwpwd);
        if (integer != null && integer >= 1) {
            return "true";
        } else {
            return "false";
        }
    }
}
