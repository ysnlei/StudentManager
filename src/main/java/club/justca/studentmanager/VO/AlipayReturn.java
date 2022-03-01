package club.justca.studentmanager.VO;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class AlipayReturn {
    private String trade_no;        //支付宝订单号
    private String out_trade_no;    //商户订单号
    private String seller_id;
    private String total_amount;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date timestamp;
}
