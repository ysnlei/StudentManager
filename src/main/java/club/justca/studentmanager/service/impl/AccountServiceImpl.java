package club.justca.studentmanager.service.impl;

import club.justca.studentmanager.entity.Account;
import club.justca.studentmanager.mapper.AccountMapper;
import club.justca.studentmanager.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountMapper accountMapper;

    @Override
    public List<Account> findAll() {
        return accountMapper.findAll();
    }

    @Override
    public Account findByMajor(String major) {
        return accountMapper.findByMajor(major);
    }

    @Override
    public Integer update(Account account) {
        return accountMapper.update(account);
    }

    @Override
    public Integer insert(String major) {
        return accountMapper.insert(major);
    }
}
