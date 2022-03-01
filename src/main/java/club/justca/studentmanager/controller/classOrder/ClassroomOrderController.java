package club.justca.studentmanager.controller.classOrder;

import club.justca.studentmanager.entity.*;
import club.justca.studentmanager.service.BuildingService;
import club.justca.studentmanager.service.ClassOrderDetailService;
import club.justca.studentmanager.service.ClassroomService;
import org.apache.ibatis.javassist.expr.NewArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/teacher")
public class ClassroomOrderController {
    @Autowired
    private BuildingService buildingService;
    @Autowired
    private ClassroomService classroomService;
    @Autowired
    private ClassOrderDetailService classOrderDetailService;

    @GetMapping("/buildingManage")
    public String buildingManage(Model model) {
        addPageInfo("buildingManage", model);

        List<Building> buildingList = buildingService.findAll();
        List<Integer> classroomNum = new ArrayList<>();
        for (Building building : buildingList) {
            List<Classroom> classroomList = classroomService.findByBuildingId(building.getId());
            classroomNum.add(classroomList.size());
        }
        model.addAttribute("buildingList", buildingList);
        model.addAttribute("classroomNum", classroomNum);
        return "teacher/classroom/buildingManage";
    }

    @ResponseBody
    @PostMapping("/deleteBuilding")
    public String deleteBuilding(Integer id) {
        Integer row = buildingService.deleteById(id);
        if (row >= 1) {
            return "true";
        } else {
            return "false";
        }
    }


    @ResponseBody
    @PostMapping("/updateBuilding")
    public String updateBuilding(Building building) {
        Integer row = buildingService.update(building);
        if (row >= 1) {
            return "true";
        } else {
            return "false";
        }
    }

    @ResponseBody
    @PostMapping("/addBuilding")
    public String addBuilding(Building building) {
        Integer row = buildingService.insert(building);
        if (row >= 1) {
            return "true";
        } else {
            return "false";
        }
    }

    //教室管理页面，根据传来的楼号进行查询
    @GetMapping("/classroomManage")
    public String classroomManage(Model model, Integer buildingId) {
        addPageInfo("buildingManage", model);
        List<Classroom> classroomList = classroomService.findByBuildingId(buildingId);
        String buildingName = buildingService.findById(buildingId).getBuildingName();
        List<Building> buildingList = buildingService.findAll();
        System.out.println(buildingList);
        model.addAttribute("classroomList", classroomList)
                .addAttribute("buildingId", buildingId)
                .addAttribute("buildingName", buildingName)
                .addAttribute("buildingList", buildingList);
        return "teacher/classroom/roomManage";
    }

    @PostMapping("/deleteClassroom")
    @ResponseBody
    public String deleteClassroom(Integer id) {
        Integer row = classroomService.deleteById(id);
        if (row >= 1) {
            return "true";
        }
        return "false";
    }

    @ResponseBody
    @PostMapping("/updateClassroom")
    public String updateClassroom(Classroom classroom) {
        Integer row = classroomService.update(classroom);
        if (row >= 1) {
            return "true";
        }
        return "false";
    }

    @ResponseBody
    @PostMapping("/addClassroom")
    public String addClassroom(Classroom classroom) {
        Integer row = classroomService.insert(classroom);
        if (row >= 1) {
            return "true";
        }
        return "false";
    }

    @GetMapping("/orderDetail")
    public String orderDetail(Model model, @RequestParam(value = "status", defaultValue = "0") Integer status) {
        addPageInfo("orderDetail", model);
        List<ClassOrderDetail> classOrderDetailList = new ArrayList<>();
        if (status == 0) {
            classOrderDetailList = classOrderDetailService.findByStatus("待审核");
        } else if (status == 1) {
            classOrderDetailList = classOrderDetailService.findByStatus("审核通过");
        } else if (status == 2) {
            classOrderDetailList = classOrderDetailService.findByStatus("驳回");
        } else if (status == 3) {
            classOrderDetailList = classOrderDetailService.findByStatus("撤销");
        } else if (status == 4) {
            classOrderDetailList = classOrderDetailService.findAll();
        }
        List<String> buildingNameList = new ArrayList<>();
        List<String> classroomNameList = new ArrayList<>();
        for (ClassOrderDetail classOrderDetail : classOrderDetailList) {
            Classroom classroom = classroomService.findById(classOrderDetail.getClassroomId());
            classroomNameList.add(classroom.getClassroomName());
            buildingNameList.add(buildingService.findById(classroom.getBuildingId()).getBuildingName());
        }
        model.addAttribute("classOrderDetailList", classOrderDetailList)
                .addAttribute("buildingNameList", buildingNameList)
                .addAttribute("classroomNameList", classroomNameList)
                .addAttribute("status", status);
        return "teacher/classroom/classroomDetail";
    }

    @ResponseBody
    @PostMapping("/acceptOrder")
    public String acceptOrder(Integer id) {
        ClassOrderDetail classOrderDetail = classOrderDetailService.findById(id);
        classOrderDetail.setStatus("审核通过");
        Integer row = classOrderDetailService.update(classOrderDetail);
        if (row >= 1) {
            return "true";
        }
        return "false";
    }


    @ResponseBody
    @PostMapping("/refuseOrder")
    public String refuseOrder(Integer id) {
        ClassOrderDetail classOrderDetail = classOrderDetailService.findById(id);
        classOrderDetail.setStatus("驳回");
        Integer row = classOrderDetailService.update(classOrderDetail);
        if (row >= 1) {
            return "true";
        }
        return "false";
    }

    @GetMapping("/classroomOrder")
    public String classroomOrder(Model model, HttpSession session) {
        addPageInfo("classroomOrder", model);
        Teacher teacher = (Teacher) session.getAttribute("loginUser");

        List<ClassOrderDetail> classOrderDetailList = classOrderDetailService.findByMajor(teacher.getManageMajor());
        List<String> buildingNameList = new ArrayList<>();
        List<String> classroomNameList = new ArrayList<>();
        for (ClassOrderDetail classOrderDetail : classOrderDetailList) {
            Classroom classroom = classroomService.findById(classOrderDetail.getClassroomId());
            classroomNameList.add(classroom.getClassroomName());
            buildingNameList.add(buildingService.findById(classroom.getBuildingId()).getBuildingName());
        }

        model.addAttribute("classOrderDetailList", classOrderDetailList)
                .addAttribute("buildingNameList", buildingNameList)
                .addAttribute("classroomNameList", classroomNameList);
        return "teacher/classroom/classroomOrder";
    }

    @ResponseBody
    @PostMapping("/deleteOrder")
    public String deleteOrder(Integer id) {
        ClassOrderDetail classOrderDetail = classOrderDetailService.findById(id);
        classOrderDetail.setStatus("撤销");
        Integer row = classOrderDetailService.update(classOrderDetail);
        if (row >= 1) {
            return "true";
        }
        return "false";
    }

@GetMapping("/orderNew")
public String orderNew(Model model, HttpSession session,
                       @RequestParam(value = "orderTime", defaultValue = "0") String orderTime) {
    addPageInfo("orderNew", model);
    Teacher teacher = (Teacher) session.getAttribute("loginUser");
    if (!orderTime.equals("0")) {
        List<Classroom> classroomList = classroomService.findAll();
        List<Classroom> classroomCanOrderList = new ArrayList<>();
        List<String> buildingName = new ArrayList<>();
        for (Classroom classroom : classroomList) {
            ClassOrderDetail classOrderDetail = classOrderDetailService.findByClassroomIdAndOrderTime(classroom.getId(), orderTime);
            if (classOrderDetail != null) {
                if (classOrderDetail.getStatus().equals("待审核") || classOrderDetail.getStatus().equals("审核通过")) {
                    continue;
                }
            }
            classroomCanOrderList.add(classroom);
            buildingName.add(buildingService.findById(classroom.getBuildingId()).getBuildingName());
        }
        model.addAttribute("classroomCanOrderList", classroomCanOrderList)
                .addAttribute("buildingName", buildingName)
                .addAttribute("orderTime", orderTime);
    }

    return "teacher/classroom/orderNew";
}

    @ResponseBody
    @PostMapping("/addNewOrder")
    public String addNewOrder(ClassOrderDetail classOrderDetail, HttpSession session) {
        Teacher teacher = (Teacher) session.getAttribute("loginUser");
        classOrderDetail.setOrderMajor(teacher.getManageMajor());
        classOrderDetail.setUpdateTime(new Date());
        classOrderDetail.setStatus("待审核");
        classOrderDetail.setOrderUser(teacher.getName());
        Integer row = classOrderDetailService.insert(classOrderDetail);
        if (row >= 1) {
            return "true";
        }
        return "false";
    }

    private void addPageInfo(String page, Model model) {
        model.addAttribute("menu", "classOrderManage");
        model.addAttribute("page", page);
    }
}
