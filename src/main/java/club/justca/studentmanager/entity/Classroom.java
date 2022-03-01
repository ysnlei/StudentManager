package club.justca.studentmanager.entity;

import lombok.Data;

@Data
public class Classroom {
    private Integer id;
    private Integer buildingId;
    private Integer floor;
    private String classroomName;
    private Integer peopleNum;
    private String orderTime;
}
