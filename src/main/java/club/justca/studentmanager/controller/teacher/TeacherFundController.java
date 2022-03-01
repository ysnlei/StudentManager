package club.justca.studentmanager.controller.teacher;

import club.justca.studentmanager.VO.FundDetailVO;
import club.justca.studentmanager.entity.*;
import club.justca.studentmanager.service.AccountService;
import club.justca.studentmanager.service.ClassFundDetailService;
import club.justca.studentmanager.service.ClassFundListService;
import club.justca.studentmanager.service.StudentService;
import club.justca.studentmanager.utils.AlipayUtil;
import club.justca.studentmanager.utils.MailSendUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/teacher")
public class TeacherFundController {
    @Autowired
    ClassFundListService classFundListService;
    @Autowired
    ClassFundDetailService classFundDetailService;
    @Autowired
    AccountService accountService;
    @Autowired
    StudentService studentService;
    @Autowired
    AlipayUtil alipayUtil;
    @Autowired
    MailSendUtil mailSendUtil;

    @GetMapping("/fundManage")
    public String fundManage(Model model, HttpSession session) {
        Teacher teacher = (Teacher) session.getAttribute("loginUser");
        List<ClassFundList> classFundLists;
        if (teacher.getManageMajor().equals("0")) {   //如果是超级管理员就查看全部
            classFundLists = classFundListService.findAll();
        } else {
            classFundLists = classFundListService.findByMajor(teacher.getManageMajor());
            Account account = accountService.findByMajor(teacher.getManageMajor());
            if (account == null) {
                account = new Account();
                account.setMajor(teacher.getManageMajor());
                accountService.insert(teacher.getManageMajor());
            }
            model.addAttribute("account", account);
        }
        model.addAttribute("classFundLists", classFundLists);
        model.addAttribute("menu", "fund");
        model.addAttribute("major", teacher.getManageMajor());
        return "teacher/fund/fundManage";
    }

    @GetMapping("/fundDetail/{classFundListId}")
    public String fundDetail(Model model, HttpSession session, @PathVariable("classFundListId") Integer classFundListId) {
        Teacher teacher = (Teacher) session.getAttribute("loginUser");
        List<FundDetailVO> fundDetailVOList = new ArrayList<>();
        String major = classFundListService.findById(classFundListId).getMajor();
        if (teacher.getManageMajor().equals("0") ||     //如果是超级管理员或本系管理员就可以查看
                major.equals(teacher.getManageMajor())) {
            List<Student> studentList = studentService.findStudentByMajor(major);
            for (Student student : studentList) {
                FundDetailVO fundDetailVO = new FundDetailVO();
                fundDetailVO.setStudent(student);
                fundDetailVO.setClassFundDetail(classFundDetailService.findByClassFundListIdAndStudentNum(classFundListId,
                        student.getStudentNum()));
                fundDetailVOList.add(fundDetailVO);
            }
        } else {
            return "redirect:/teacher/manageList";
        }
        model.addAttribute("fundDetailVOList", fundDetailVOList);    //当前fundID的学生信息和缴费信息
        model.addAttribute("menu", "fund");
        model.addAttribute("major", teacher.getManageMajor());
        return "teacher/fund/fundDetail";
    }

    @PostMapping("/addFundList")
    @ResponseBody
    public String addFundList(HttpSession session, ClassFundList classFundList) {
        Teacher teacher = (Teacher) session.getAttribute("loginUser");
        if (teacher == null || teacher.getManageMajor().equals("0")) {
            return "false";
        } else {
            classFundList.setMajor(teacher.getManageMajor());
            Integer row = classFundListService.insert(classFundList);
            if (row > 0) {
                return "true";
            } else {
                return "false";
            }
        }
    }

    @PostMapping("/withdraw")
    @ResponseBody
    public String withdraw(HttpSession session, String alipayAccount, String name, String money) {
        Teacher teacher = (Teacher) session.getAttribute("loginUser");
        if (teacher == null || teacher.getManageMajor().equals("0")) {
            return "false";
        } else {
            Account account = accountService.findByMajor(teacher.getManageMajor());
            BigDecimal oldMoney = account.getAccount();
            BigDecimal newMoney = oldMoney.subtract(new BigDecimal(money));
            if (Double.parseDouble(newMoney.toString()) < 0) {
                return "false";
            } else {
                account.setAccount(newMoney);
                Boolean withdrew = alipayUtil.withdrew(alipayAccount, name, money);
                if (withdrew) {
                    accountService.update(account);
                    return "true";
                } else {
                    return "false";
                }
            }
        }
    }

    /**
     * 单独发送催缴邮件
     *
     */
    @PostMapping("/sendFundNotice")
    @ResponseBody
    public String sendFundNotice(String studentNum, HttpSession session) {
        Student student = studentService.findByStudentNum(studentNum);
        mailSendUtil.sendFund(student.getEmail());
        return "true";
    }
}
