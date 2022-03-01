package club.justca.studentmanager.controller.student;

import club.justca.studentmanager.entity.Leave;
import club.justca.studentmanager.entity.Student;
import club.justca.studentmanager.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentLeaveController {
    @Autowired
    private LeaveService leaveService;

    @GetMapping("/leaveList")
    public String leaveList(Model model, HttpSession session) {
        model.addAttribute("page", "leave");
        Student student = (Student) session.getAttribute("loginUser");
        List<Leave> leaveList = leaveService.findByStudentNum(student.getStudentNum());
        model.addAttribute("leaveList", leaveList);
        return "student/leaveList";
    }

    @GetMapping("/leaveUpdate/{id}")
    public String leaveUpdate(Model model,
                              @PathVariable("id") Integer id) {
        model.addAttribute("page", "leave");
        Leave leave = leaveService.findById(id);
        model.addAttribute("leave", leave);
        return "student/leaveUpdate";
    }

    @PostMapping("/update_leave")
    @ResponseBody
    public String updateLeave(Leave leave) {
        Integer id = leave.getId();
        Leave oldLeave = leaveService.findById(id);
        oldLeave.setStartTime(leave.getStartTime());
        oldLeave.setEndTime(leave.getEndTime());
        oldLeave.setReason(leave.getReason());
        oldLeave.setState("未审批");
        Integer update = leaveService.update(oldLeave);
        if (update >= 1) {
            return "true";
        } else {
            return "false";
        }
    }

    @GetMapping("/leaveCreate")
    public String leaveCreate(Model model) {
        model.addAttribute("page", "leave");
        return "student/leaveCreate";
    }

@PostMapping("/create_leave")
@ResponseBody
public String createLeave(Leave leave) {
    leave.setState("未审批");
    System.out.println(leave);
    Integer row = leaveService.insert(leave);
    if (row >= 1) {
        return "true";
    } else {
        return "false";
    }
}

    @PostMapping("/xiaojia")
    @ResponseBody
    public String xiaojia(Integer id) {
        Leave leave = leaveService.findById(id);
        leave.setState("已销假");
        Integer row = leaveService.update(leave);
        if (row >= 1) {
            return "true";
        } else {
            return "false";
        }
    }
}
