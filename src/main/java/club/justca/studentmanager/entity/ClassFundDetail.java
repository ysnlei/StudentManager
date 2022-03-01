package club.justca.studentmanager.entity;

import lombok.Data;

import java.util.Date;

@Data
public class ClassFundDetail {
    private Integer id;
    private Integer classFundListId;
    private String tradeNo;
    private String outTradeNo;      //UUID
    private String studentNum;
    private String status;
    private Date payTime;
}
