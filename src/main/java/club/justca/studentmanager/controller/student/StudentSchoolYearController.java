package club.justca.studentmanager.controller.student;

import club.justca.studentmanager.entity.SchoolYear;
import club.justca.studentmanager.service.SchoolYearService;
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
public class StudentSchoolYearController {
    @Autowired
    private SchoolYearService schoolYearService;

    @GetMapping("/schoolYearList")
    public String schoolYearList(Model model,
                                 @RequestParam(name = "page", defaultValue = "1") Integer page,
                                 @RequestParam(name = "size", defaultValue = "5") Integer size) {
        model.addAttribute("page", "schoolYear");
        List<SchoolYear> schoolYearList = schoolYearService.findByPage(page, size);
        model.addAttribute("schoolYearList", schoolYearList);
        Integer count = schoolYearService.getCount();
        model.addAttribute("totalArticle", count);
        int totalPage = 0;
        if (count % size != 0) {
            totalPage++;
        }
        totalPage += count / size;
        model.addAttribute("localPage", page);
        model.addAttribute("size", size);
        model.addAttribute("totalPage", totalPage);
        return "student/schoolYear_list";
    }

    @GetMapping("/schoolYear/{id}")
    public String schoolYear(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("page", "schoolYear");
        SchoolYear schoolYear = schoolYearService.findById(id);
        model.addAttribute("schoolYear", schoolYear);
        return "student/schoolYear";
    }
}
