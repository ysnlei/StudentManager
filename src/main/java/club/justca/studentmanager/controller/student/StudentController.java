package club.justca.studentmanager.controller.student;

import club.justca.studentmanager.VO.RewardAndPunishVO;
import club.justca.studentmanager.entity.*;
import club.justca.studentmanager.service.*;
import club.justca.studentmanager.utils.ScoreUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private SchoolYearService schoolYearService;

    @GetMapping({"/index", "/"})
    public String index(Model model) {
        model.addAttribute("page", "index");
        List<Notification> noticeList = notificationService.findByPage(1, 8);
        model.addAttribute("noticeList", noticeList);
        List<SchoolYear> schoolYearList = schoolYearService.findByPage(1, 8);
        model.addAttribute("schoolYearList", schoolYearList);
        return "student/index";
    }
}
