package club.justca.studentmanager.controller.student;

import club.justca.studentmanager.entity.Notification;
import club.justca.studentmanager.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentNoticeController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/noticeList")
    public String noticeList(Model model,
                             @RequestParam(name = "page", defaultValue = "1") Integer page,
                             @RequestParam(name = "size", defaultValue = "5") Integer size) {
        model.addAttribute("page", "notice");
        List<Notification> notificationList = notificationService.findByPage(page, size);
        model.addAttribute("noticeList", notificationList);
        Integer count = notificationService.getCount();
        int totalPage = 0;
        if (count % size != 0) {
            totalPage++;
        }
        totalPage += count / size;
        model.addAttribute("localPage", page);
        model.addAttribute("size", size);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("totalArticle", count);
        return "student/notice_list";
    }

    @GetMapping("/notice/{id}")
    public String notice(Model model,
                         @PathVariable("id") Integer id) {
        model.addAttribute("page", "notice");
        Notification notice = notificationService.findById(id);
        model.addAttribute("notice", notice);
        return "student/notice";
    }

}
