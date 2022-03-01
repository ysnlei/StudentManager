package club.justca.studentmanager.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Notification {
    private Integer id;
    private String title;
    private String author;
    private String article;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
    private String attachment;

    public Date getCreate_time(){
        return createTime;
    }
}
