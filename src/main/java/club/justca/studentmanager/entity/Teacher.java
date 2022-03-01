package club.justca.studentmanager.entity;

import lombok.Data;

@Data
public class Teacher {
    private Integer id;
    private String teacherNum;
    private String password;
    private String name;
    private String gender;
    private String manageMajor;
    private String email;
}
