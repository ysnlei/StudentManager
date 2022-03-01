package club.justca.studentmanager.entity;

import lombok.Data;

import java.util.Date;

@Data
public class ClassOrderDetail {
    private Integer id;
    private Integer classroomId;
    private String orderMajor;
    private String orderUser;
    private String orderTime;
    private Date updateTime;
    private String status;
}
