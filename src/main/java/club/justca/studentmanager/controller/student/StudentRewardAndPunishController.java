package club.justca.studentmanager.controller.student;

import club.justca.studentmanager.VO.RewardAndPunishVO;
import club.justca.studentmanager.entity.RewardAndPunish;
import club.justca.studentmanager.entity.RewardList;
import club.justca.studentmanager.entity.Score;
import club.justca.studentmanager.entity.Student;
import club.justca.studentmanager.service.RewardAndPunishService;
import club.justca.studentmanager.service.RewardListService;
import club.justca.studentmanager.service.ScoreService;
import club.justca.studentmanager.utils.ScoreUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/student")
public class StudentRewardAndPunishController {
    @Autowired
    private RewardListService rewardListService;
    @Autowired
    private RewardAndPunishService rewardAndPunishService;
    @Autowired
    private ScoreService scoreService;
    @GetMapping("/rewardList")
    public String rewardList(Model model, HttpSession session) {
        Student student = (Student) session.getAttribute("loginUser");
        model.addAttribute("page", "reward");
        List<RewardList> rewardList = rewardListService.findAll();
        model.addAttribute("rewardList", rewardList);
        Date date = new Date();
        List<Integer> show = new ArrayList<>();
        for (RewardList list : rewardList) {
            if(list.getEndTime().before(date)||
                    rewardAndPunishService.findByStudentNumAndRewardId(student.getStudentNum(),list.getId())!=null){
                show.add(0);
            }else {//        如果已经申请了           如果超过时间
                show.add(1);
            }
        }
        model.addAttribute("show",show);
        return "student/rewardList";
    }

    /**
     * 查看奖励明细
     */
    @GetMapping("/rewardDetail")
    public String rewardDetail(Model model, HttpSession session) {
        model.addAttribute("page", "reward");
        Student student = (Student) session.getAttribute("loginUser");
        List<RewardAndPunish> rewardAndPunishList = rewardAndPunishService.findByStudentNum(student.getStudentNum());
        List<RewardAndPunishVO> rewardAndPunishVOList = new ArrayList<>();
        for (RewardAndPunish rewardAndPunish : rewardAndPunishList) {
            if(rewardAndPunish.getType().equals("奖励")){
                RewardAndPunishVO rewardAndPunishVO = new RewardAndPunishVO();
                rewardAndPunishVO.setRewardAndPunish(rewardAndPunish);
                RewardList rewardList = rewardListService.findById(rewardAndPunish.getRewardId());
                if(rewardList!=null){
                    rewardAndPunishVO.setRewardList(rewardList);
                    rewardAndPunishVO.setStuName(student.getStudentNum());
                    List<Score> scoreList = scoreService.findByStudentNum(student.getStudentNum());
                    Double gpa = ScoreUtil.getGPA(scoreList);
                    rewardAndPunishVO.setGpa(gpa);
                    rewardAndPunishVOList.add(rewardAndPunishVO);
                }
            }
        }
        Date date = new Date();
        List<Integer> show = new ArrayList<>();
        for (RewardAndPunishVO rewardAndPunishVO : rewardAndPunishVOList) {
            RewardList list = rewardAndPunishVO.getRewardList();
            if(list.getEndTime().before(date)||
                    !Objects.equals(rewardAndPunishVO.getRewardAndPunish().getState(), "待审核")||
                    !Objects.equals(rewardAndPunishVO.getRewardAndPunish().getState(), "奖励")
            ){
                show.add(0);
            }else {//        如果已经审核           如果超过时间
                show.add(1);
            }
        }
        model.addAttribute("rewardAndPunishList", rewardAndPunishVOList);
        model.addAttribute("show", show);
        return "/student/rewardDetail";
    }

    @GetMapping("/punishDetail")
    public String punishDetail(Model model,HttpSession session){
        model.addAttribute("page", "reward");
        List<RewardAndPunish> punishList1 = rewardAndPunishService.findByType("惩罚");
        Student student = (Student) session.getAttribute("loginUser");
        List<RewardAndPunish> punishList = new ArrayList<>();
        for (RewardAndPunish rewardAndPunish : punishList1) {
            if(Objects.equals(rewardAndPunish.getStudentNum(), student.getStudentNum())){
                punishList.add(rewardAndPunish);
            }
        }
        model.addAttribute("punishList",punishList);
        return "/student/punishDetail";
    }

    @PostMapping("/applyReward")
    @ResponseBody
    public Integer applyReward(HttpSession session, RewardAndPunish rewardAndPunish) {
        Student student = (Student) session.getAttribute("loginUser");
        rewardAndPunish.setStudentNum(student.getStudentNum());
        rewardAndPunish.setState("待审核");
        rewardAndPunish.setType("奖励");
        Date date = new Date();
        RewardList rewardList = rewardListService.findById(rewardAndPunish.getRewardId());
        if(rewardList.getEndTime().before(date)){
            return 4;
        }
        RewardAndPunish judge = rewardAndPunishService.findByStudentNumAndRewardId(student.getStudentNum(), rewardAndPunish.getRewardId());
        if(judge!=null){
            return 3;   //申请过，不能再申请
        }else {
            Integer row = rewardAndPunishService.insert(rewardAndPunish);
            if (row>=1){
                return 1;   //申请成功
            }else {
                return 2;   //申请失败
            }
        }
    }
}
