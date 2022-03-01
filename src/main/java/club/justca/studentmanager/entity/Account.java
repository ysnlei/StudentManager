package club.justca.studentmanager.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Account {
    private Integer id;
    private String major;
    private BigDecimal account;
}
