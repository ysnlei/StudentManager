package club.justca.studentmanager.VO;

import lombok.Data;

@Data
public class LoginError {
    private String errorResult;
    private String username;
    private Integer role;
}
