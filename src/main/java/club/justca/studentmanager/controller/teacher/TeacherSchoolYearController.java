package club.justca.studentmanager.controller.teacher;

import club.justca.studentmanager.entity.SchoolYear;
import club.justca.studentmanager.entity.Teacher;
import club.justca.studentmanager.service.SchoolYearService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/teacher")
public class TeacherSchoolYearController {
    @Autowired
    private SchoolYearService schoolYearService;

    @GetMapping("/schoolYearManage")
    public String schoolYearManage(Model model, HttpSession session) {
        Teacher teacher = (Teacher) session.getAttribute("loginUser");
        model.addAttribute("major",teacher.getManageMajor());
        addPageInfo("schoolYearManage", model);
        List<SchoolYear> schoolYearList = schoolYearService.findAll();
        model.addAttribute("schoolYearList", schoolYearList);
        return "teacher/schoolYear/schoolYear_manage";
    }

    @GetMapping("schoolYearCreate")
    public String schoolYearCreate(Model model,HttpSession session) {
        Teacher teacher = (Teacher) session.getAttribute("loginUser");
        model.addAttribute("major",teacher.getManageMajor());
        addPageInfo("schoolYearCreate", model);
        return "teacher/schoolYear/schoolYear_create";
    }

    @PostMapping("/saveSchoolYear")
    @ResponseBody
    public String saveSchoolYear(SchoolYear schoolYear) {
        Integer row = schoolYearService.insert(schoolYear);
        if (row >= 1) {
            return "true";
        } else {
            return "false";
        }
    }

    @GetMapping("/updateSchoolYear/{id}")
    public String updateSchoolYear(Model model,
                                   @PathVariable("id") Integer id,
                                   HttpSession session) {
        Teacher teacher = (Teacher) session.getAttribute("loginUser");
        model.addAttribute("major",teacher.getManageMajor());
        addPageInfo("schoolYearCreate", model);
        SchoolYear schoolYear = schoolYearService.findById(id);
        model.addAttribute("schoolYear", schoolYear);
        return "teacher/schoolYear/schoolYear_update";
    }

    @PostMapping("/schoolYearUpdate")
    @ResponseBody
    public String schoolYearUpdate(SchoolYear schoolYear) {
        Integer row = schoolYearService.update(schoolYear);
        if (row >= 1) {
            return "true";
        } else {
            return "false";
        }
    }

    @PostMapping("/schoolYearDelete")
    @ResponseBody
    public String schoolYearDelete(@RequestParam("id") Integer id) {
        Integer row = schoolYearService.deleteById(id);
        if (row >= 1) {
            return "true";
        } else {
            return "false";
        }
    }


    private void addPageInfo(String page, Model model) {
        model.addAttribute("menu", "schoolYear");
        model.addAttribute("page", page);
    }
}
