package club.justca.studentmanager.VO;

import lombok.Data;

@Data
public class EmailSendData {
    public EmailSendData(){
        code=0;
    }

    /**
     * code:0正常，1失败
     */
    private int code;
    private String reason;

}
