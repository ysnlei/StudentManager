package club.justca.studentmanager.mapper;

import club.justca.studentmanager.entity.Account;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountMapper {
    List<Account> findAll();

    Account findByMajor(String major);

    Integer update(Account account);

    Integer insert(String major);
}
