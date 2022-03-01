package club.justca.studentmanager.controller.teacher;

import club.justca.studentmanager.entity.Notification;
import club.justca.studentmanager.entity.Teacher;
import club.justca.studentmanager.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/teacher")
public class TeacherNoticeController {
    @Autowired
    private NotificationService notificationService;

    @GetMapping("/noticeManage")
    public String noticeManage(Model model, HttpSession session) {
        Teacher teacher = (Teacher) session.getAttribute("loginUser");
        model.addAttribute("major",teacher.getManageMajor());
        addPageInfo("notice", "noticeManage", model);
        List<Notification> noticeList = notificationService.findAll();
        model.addAttribute("noticeList", noticeList);
        return "teacher/notice/notice_manage";
    }

    @GetMapping("/noticeCreate")
    public String noticeCreate(Model model,HttpSession session) {
        Teacher teacher = (Teacher) session.getAttribute("loginUser");
        model.addAttribute("major",teacher.getManageMajor());
        addPageInfo("notice", "noticeCreate", model);
        return "teacher/notice/notice_create";
    }

    @PostMapping("/deleteNotice")
    @ResponseBody
    public String deleteNotice(@RequestParam("noticeId") Integer noticeId) {
        Integer row = notificationService.deleteById(noticeId);
        if (row >= 1) {
            return "true";
        } else {
            return "false";
        }
    }

    @GetMapping("/previewNotice/{noticeId}")
    public String previewNotice(Model model,
                                @PathVariable("noticeId") Integer noticeId,
                                HttpSession session) {
        Teacher teacher = (Teacher) session.getAttribute("loginUser");
        model.addAttribute("major",teacher.getManageMajor());
        addPageInfo("notice", "noticeManage", model);
        Notification notice = notificationService.findById(noticeId);
        model.addAttribute("notice", notice);
        return "/teacher/notice/notice_preview";
    }

    @GetMapping("/updateNotice/{noticeId}")
    public String updateNotice(Model model,
                               @PathVariable(value = "noticeId") Integer noticeId,
                               HttpSession session) {
        Teacher teacher = (Teacher) session.getAttribute("loginUser");
        model.addAttribute("major",teacher.getManageMajor());
        addPageInfo("notice", "noticeManage", model);
        Notification notice = notificationService.findById(noticeId);
        model.addAttribute("notice", notice);
        return "teacher/notice/notice_update";
    }

    @PostMapping("/saveNotice")
    @ResponseBody
    public String saveNotice(Notification notice) {
        Integer row = notificationService.insert(notice);
        if (row >= 1) {
            return "true";
        } else {
            return "false";
        }
    }

    @PostMapping("/noticeUpdate")
    @ResponseBody
    public String noticeUpdate(Notification notice) {
        System.out.println(notice);
        Integer row = notificationService.update(notice);
        if (row >= 1) {
            return "true";
        } else {
            return "false";
        }
    }

    private void addPageInfo(String menu, String page, Model model) {
        model.addAttribute("menu", menu);
        model.addAttribute("page", page);
    }
}
