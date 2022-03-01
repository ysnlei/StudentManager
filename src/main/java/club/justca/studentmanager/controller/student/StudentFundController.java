package club.justca.studentmanager.controller.student;

import club.justca.studentmanager.entity.ClassFundDetail;
import club.justca.studentmanager.entity.ClassFundList;
import club.justca.studentmanager.entity.Student;
import club.justca.studentmanager.service.ClassFundDetailService;
import club.justca.studentmanager.service.ClassFundListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentFundController {
    @Autowired
    ClassFundListService classFundListService;
    @Autowired
    ClassFundDetailService classFundDetailService;

    @GetMapping("/fundList")
    public String fundList(Model model, HttpSession session) {
        Student student = (Student) session.getAttribute("loginUser");
        model.addAttribute("page", "fund");
        List<ClassFundList> classFundLists = classFundListService.findByMajor(student.getMajor());
        List<Integer> pay = new ArrayList<>();
        //查看该学生是否支付，已支付为1，未支付为0
        for (ClassFundList classFundList : classFundLists) {
            ClassFundDetail classFundDetail = classFundDetailService.findByClassFundListIdAndStudentNum(
                    classFundList.getId(),
                    student.getStudentNum());
            if (classFundDetail != null && "已支付".equals(classFundDetail.getStatus())) {
                pay.add(1);
            } else {
                pay.add(0);
            }
        }
        model.addAttribute("pay", pay);
        model.addAttribute("classFundLists", classFundLists);
        return "student/fundList";
    }

    @GetMapping("/fundDetail")
    public String fundDetail(Model model, HttpSession session) {
        Student student = (Student) session.getAttribute("loginUser");
        model.addAttribute("page", "fund");

        List<ClassFundDetail> classFundDetailList = classFundDetailService.findByStudentNum(student.getStudentNum());
        List<String> titleList = new ArrayList<>();
        for (ClassFundDetail classFundDetail : classFundDetailList) {
            String title = classFundListService.findById(classFundDetail.getClassFundListId()).getTitle();
            titleList.add(title);
        }

        model.addAttribute("classFundDetailList",classFundDetailList);
        model.addAttribute("titleList",titleList);
        return "student/fundDetail";
    }
}
