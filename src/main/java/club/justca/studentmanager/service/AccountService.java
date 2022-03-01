package club.justca.studentmanager.service;

import club.justca.studentmanager.entity.Account;

import java.util.List;

public interface AccountService {
    List<Account> findAll();

    Account findByMajor(String major);

    Integer update(Account account);

    Integer insert(String major);
}
