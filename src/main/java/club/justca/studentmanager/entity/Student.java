package club.justca.studentmanager.entity;

import lombok.Data;

@Data
public class Student {
    private Integer id;
    private String studentNum;
    private String password;
    private String name;
    private String gender;
    private String major;
    private String telephone;
    private String dormitory;
    private String duty;
    private String email;
}
