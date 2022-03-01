package club.justca.studentmanager.controller.backend;

import club.justca.studentmanager.VO.FileUpload;
import club.justca.studentmanager.VO.WangEditor;
import club.justca.studentmanager.VO.WangEditorData;
import club.justca.studentmanager.entity.Student;
import club.justca.studentmanager.service.StudentService;
import club.justca.studentmanager.utils.ExcelUtil;
import club.justca.studentmanager.utils.FormatUtil;
import club.justca.studentmanager.utils.ScoreUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/backend/upload")
@CrossOrigin
@Slf4j
public class FileUploadController {

    /**
     * 图片上传
     */
    @ResponseBody
    @RequestMapping("/upload-img")
    public WangEditor uploadImg(@RequestParam("upload") MultipartFile upload,     //文件上传核心代码1
                                HttpServletRequest request) throws IOException {
        String dirName = FormatUtil.getFormatImgDirName();
        File file = new File("E:\\upload\\" + dirName);
        if (!file.exists()) {
            boolean mkdirs = file.mkdirs();
            if (mkdirs) {
                log.info("首次创建{}文件夹", file.getPath());
            } else {
                log.error("文件夹{}创建错误", file.getPath());
                throw new RuntimeException("上传文件夹" + dirName + "创建失败！");
            }
        }
        if (upload != null && upload.getSize() != 0) {
            String originalFilename = upload.getOriginalFilename();
            String suffix = "";
            if (originalFilename != null) {
                suffix = FormatUtil.getSuffix(originalFilename);
            }
            if (".jpg".equals(suffix) || ".png".equals(suffix)) {
                String fileName = FormatUtil.getFormatImgName() + suffix;
                upload.transferTo(new File(file, fileName));
                WangEditorData wangEditorData = new WangEditorData();
                wangEditorData.appendUrl(dirName, fileName);
                //以下代码跨域访问时要用，本地访问要注释掉
                //String url = wangEditorData.getUrl();
                //wangEditorData.setUrl("http://localhost:88"+url);
                //------------------
                List<WangEditorData> wangEditorDataList = new ArrayList<>();
                wangEditorDataList.add(wangEditorData);
                return new WangEditor(wangEditorDataList);
            }
        }
        return new WangEditor();
    }

    /**
     * 附件上传
     */
    @ResponseBody
    @PostMapping("/attachment_upload")
    public FileUpload uploadAttachment(@RequestParam("fileUpload") MultipartFile fileUpload) throws IOException {
        String dirName = FormatUtil.getFormatFileDirName();
        File file = new File("E:\\upload\\file\\" + dirName);
        if (!file.exists()) {
            boolean mkdirs = file.mkdirs();
            if (mkdirs) {
                log.info("首次创建{}文件夹", file.getPath());
            } else {
                log.error("创建文件夹{}失败", file.getPath());
                throw new RuntimeException("创建文件夹" + file.getPath() + "失败");
            }
        }
        if (fileUpload != null && fileUpload.getSize() != 0) {
            String originalFilename = fileUpload.getOriginalFilename();
            if (originalFilename != null) {
                String suffix = FormatUtil.getSuffix(originalFilename);
                String fileName = FormatUtil.getFormatFileName() + suffix;
                fileUpload.transferTo(new File(file, fileName));
                return new FileUpload(dirName, fileName);
            }
        }
        return new FileUpload();
    }

    @Autowired
    StudentService studentService;

    /**
     * excel上传
     */
    @ResponseBody
    @PostMapping("/excel_upload")
    public FileUpload uploadExcel(@RequestParam("fileUpload") MultipartFile fileUpload) throws IOException {
        String dirName = FormatUtil.getFormatFileDirName();
        File file = new File("E:\\upload\\file\\" + dirName);
        if (!file.exists()) {
            boolean mkdirs = file.mkdirs();
            if (mkdirs) {
                log.info("首次创建{}文件夹", file.getPath());
            } else {
                log.error("创建文件夹{}失败", file.getPath());
                throw new RuntimeException("创建文件夹" + file.getPath() + "失败");
            }
        }
        if (fileUpload != null && fileUpload.getSize() != 0) {
            String originalFilename = fileUpload.getOriginalFilename();
            if (originalFilename != null) {
                String suffix = FormatUtil.getSuffix(originalFilename);
                if (".xls".equals(suffix) || ".xlsx".equals(suffix)) {
                    String fileName = FormatUtil.getFormatFileName() + suffix;
                    fileUpload.transferTo(new File(file, fileName));
                    String filePath = file + "\\" + fileName;
                    List<Student> studentList = ExcelUtil.getStudentListFromExcel(filePath);
                    if (studentList != null) {
                        for (Student student : studentList) {
                            student.setPassword("123456");
                            studentService.insert(student);
                        }
                        return new FileUpload(dirName, fileName);
                    }
                }
            }
        }
        return new FileUpload();
    }
}
