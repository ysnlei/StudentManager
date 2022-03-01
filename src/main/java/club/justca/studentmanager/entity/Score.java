package club.justca.studentmanager.entity;

import lombok.Data;

/**
 * 对应学生成绩，学生上传成绩单或者从教务获取成绩进行html解析
 */
@Data
public class Score {
    private String id;
    private String studentNum;         //学号
    private String courseTerm;          //开课学期
    private String courseNum;          //课程号
    private String courseName;          //课程号
    private Integer score;              //分数
    private String courseCredit;       //学分
    private String courseHour;         //学时
    private String examination;        //考核方式  考试、考查
    private String courseNature;       //课程性质
}
