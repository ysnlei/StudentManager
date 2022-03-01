package club.justca.studentmanager.controller.teacher;

import club.justca.studentmanager.VO.LeaveVO;
import club.justca.studentmanager.entity.Leave;
import club.justca.studentmanager.entity.Student;
import club.justca.studentmanager.entity.Teacher;
import club.justca.studentmanager.service.LeaveService;
import club.justca.studentmanager.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/teacher")
public class TeacherLeaveController {
    @Autowired
    private LeaveService leaveService;
    @Autowired
    private StudentService studentService;

    /**
     * @param state 0--未审批 1--审批通过，未销假 2--已销假 3--未通过  4--查找全部
     */
    @GetMapping("/leaveManage")
    public String leaveManage(Model model,
                              @RequestParam(value = "state", defaultValue = "0") int state,
                              HttpSession session) {
        Teacher teacher = (Teacher) session.getAttribute("loginUser");
        model.addAttribute("major",teacher.getManageMajor());
        addPageInfo(model);
        model.addAttribute("state", state);
        List<Leave> leaveList = null;
        if (state == 0) {
            leaveList = leaveService.findByState("未审批");
        } else if (state == 1) {
            leaveList = leaveService.findByState("审批通过");
        } else if (state == 2) {
            leaveList = leaveService.findByState("已销假");
        } else if (state == 3) {
            leaveList = leaveService.findByState("未通过");
        } else if (state == 4) {
            leaveList = leaveService.findAll();
        }
        List<LeaveVO> leaveVOList = new ArrayList<>();
        if (leaveList != null) {
            for (Leave leave : leaveList) {
                Student student = studentService.findByStudentNum(leave.getStudentNum());
                if(student!=null){
                    LeaveVO leaveVO = new LeaveVO();
                    leaveVO.setLeave(leave);
                    leaveVO.setName(student.getName());
                    leaveVOList.add(leaveVO);
                }
            }
        }
        model.addAttribute("leaveVOList", leaveVOList);
        return "teacher/leave/leave";
    }

    @GetMapping("/leaveSuccess")
    public String leaveSuccess(@RequestParam("id") Integer id) {
        Leave leave = leaveService.findById(id);
        if (leave.getState().equals("未审批")) {
            leave.setState("审批通过");
            leaveService.update(leave);
        }
        return "redirect:/teacher/leaveManage";
    }

    @RequestMapping("/leaveFail")
    @ResponseBody
    public String leaveFile(@RequestParam("id") Integer id,
                            @RequestParam("refuseReason") String refuseReason) {
        Leave leave = leaveService.findById(id);
        if (leave.getState().equals("未审批")) {
            leave.setState("未通过");
            leave.setRefuseReason(refuseReason);
            Integer row = leaveService.update(leave);
            if (row >= 1) {
                return "true";
            }
        }
        return "false";
    }
    private void addPageInfo(Model model) {
        model.addAttribute("menu", "leave");
    }
}
