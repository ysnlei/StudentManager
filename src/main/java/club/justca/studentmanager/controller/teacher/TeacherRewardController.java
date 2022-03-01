package club.justca.studentmanager.controller.teacher;

import club.justca.studentmanager.VO.RewardAndPunishVO;
import club.justca.studentmanager.entity.RewardAndPunish;
import club.justca.studentmanager.entity.RewardList;
import club.justca.studentmanager.entity.Student;
import club.justca.studentmanager.entity.Teacher;
import club.justca.studentmanager.service.RewardAndPunishService;
import club.justca.studentmanager.service.RewardListService;
import club.justca.studentmanager.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/teacher")
public class TeacherRewardController {
    @Autowired
    private RewardListService rewardListService;
    @Autowired
    private RewardAndPunishService rewardAndPunishService;
    @Autowired
    private StudentService studentService;

    @GetMapping("/rewardCreate")
    public String rewardCreate(Model model,HttpSession session) {
        Teacher teacher = (Teacher) session.getAttribute("loginUser");
        model.addAttribute("major",teacher.getManageMajor());
        addPageInfo("rewardCreate", model);
        return "teacher/reward/reward_create";
    }

    @GetMapping("/rewardUpdate")
    public String rewardUpdate(Model model,
                               @RequestParam("id") Integer id,
                               HttpSession session) {
        Teacher teacher = (Teacher) session.getAttribute("loginUser");
        model.addAttribute("major",teacher.getManageMajor());
        addPageInfo("rewardManage", model);
        RewardList reward = rewardListService.findById(id);
        model.addAttribute("reward", reward);
        return "teacher/reward/reward_update";
    }

    @GetMapping("/rewardManage")
    public String rewardManage(Model model, HttpSession session) {
        Teacher teacher = (Teacher) session.getAttribute("loginUser");
        model.addAttribute("major",teacher.getManageMajor());
        List<RewardList> rewardList;
        if (teacher.getManageMajor().equals("0")) {
            rewardList = rewardListService.findAll();
        }else {
            rewardList = rewardListService.findByMajor(teacher.getManageMajor());
        }
        addPageInfo("rewardManage", model);
        model.addAttribute("rewardList", rewardList);
        return "teacher/reward/reward_manage";
    }

    @PostMapping("/rewardListAdd")
    @ResponseBody
    public String rewardListAdd(RewardList rewardList) {
        Integer row = rewardListService.insert(rewardList);
        if (row >= 1) {
            return "true";
        } else {
            return "false";
        }
    }

    @PostMapping("/rewardListUpdate")
    @ResponseBody
    public String rewardListUpdate(RewardList rewardList) {
        Integer row = rewardListService.update(rewardList);
        if (row >= 1) {
            return "true";
        } else {
            return "false";
        }
    }

    @GetMapping("/rewardDetail/{rewardId}")
    public String rewardDetail(Model model,
                               @PathVariable("rewardId") Integer id,HttpSession session) {
        Teacher teacher = (Teacher) session.getAttribute("loginUser");
        model.addAttribute("major",teacher.getManageMajor());
        addPageInfo("rewardManage", model);
        String rewardName = rewardListService.findById(id).getName();
        List<RewardAndPunishVO> rewardAndPunishVOList = rewardAndPunishService.findByRewardId(id);
        model.addAttribute("rewardName", rewardName);
        model.addAttribute("rewardAndPunishList", rewardAndPunishVOList);
        return "teacher/reward/reward_detail";
    }

    @PostMapping("/rewardConfirm")
    @ResponseBody
    public String rewardConfirm(RewardAndPunish rewardAndPunish) {
        RewardAndPunish rewardAndPunishConfirm =
                rewardAndPunishService.findByStudentNumAndRewardId(rewardAndPunish.getStudentNum(),
                        rewardAndPunish.getRewardId());
        rewardAndPunishConfirm.setState("同意");
        Integer row = rewardAndPunishService.update(rewardAndPunishConfirm);
        if (row >= 1) {
            return "true";
        } else {
            return "false";
        }
    }

    @PostMapping("/rewardReject")
    @ResponseBody
    public String rewardReject(RewardAndPunish rewardAndPunish) {
        RewardAndPunish rewardAndPunishConfirm =
                rewardAndPunishService.findByStudentNumAndRewardId(rewardAndPunish.getStudentNum(),
                        rewardAndPunish.getRewardId());
        rewardAndPunishConfirm.setState("驳回");
        Integer row = rewardAndPunishService.update(rewardAndPunishConfirm);
        if (row >= 1) {
            return "true";
        } else {
            return "false";
        }
    }

    @GetMapping("/punishManage")
    public String punishManage(Model model,HttpSession session) {
        addPageInfo("punishManage", model);
        List<RewardAndPunish> punishList = rewardAndPunishService.findByType("惩罚");
        List<RewardAndPunishVO> punishVOList = new ArrayList<>();
        Teacher teacher = (Teacher) session.getAttribute("loginUser");
        model.addAttribute("major",teacher.getManageMajor());
        String major = teacher.getManageMajor();
        for (RewardAndPunish rewardAndPunish : punishList) {
            Student student = studentService.findByStudentNum(rewardAndPunish.getStudentNum());
            if (student != null && major.equals(student.getMajor())) {
                RewardAndPunishVO rewardAndPunishVO = new RewardAndPunishVO();
                rewardAndPunishVO.setRewardAndPunish(rewardAndPunish);
                rewardAndPunishVO.setStuName(student.getName());
                punishVOList.add(rewardAndPunishVO);
            }
        }
        model.addAttribute("punishList", punishVOList);
        List<Student> studentList = studentService.findAll();
        model.addAttribute("studentList", studentList);
        return "teacher/punish/punish_manage";
    }

    @PostMapping("/addPunish")
    @ResponseBody
    public String addPunish(RewardAndPunish rewardAndPunish) {
        rewardAndPunish.setRewardId(-1);
        rewardAndPunish.setType("惩罚");
        Integer row = rewardAndPunishService.insert(rewardAndPunish);
        if (row >= 1) {
            return "true";
        } else {
            return "false";
        }
    }

    @PostMapping("/deletePunish")
    @ResponseBody
    public String deletePunish(Integer id) {
        Integer row = rewardAndPunishService.deleteById(id);
        if (row >= 1) {
            return "true";
        } else {
            return "false";
        }
    }

    private void addPageInfo(String page, Model model) {
        model.addAttribute("menu", "reward");
        model.addAttribute("page", page);
    }
}
