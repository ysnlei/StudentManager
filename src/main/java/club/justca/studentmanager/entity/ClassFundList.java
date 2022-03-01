package club.justca.studentmanager.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ClassFundList {
    private Integer id;
    private String major;
    private String title;
    private BigDecimal money;
}
