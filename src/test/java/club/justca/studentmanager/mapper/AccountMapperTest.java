package club.justca.studentmanager.mapper;

import club.justca.studentmanager.entity.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AccountMapperTest {
    @Autowired
    AccountMapper accountMapper;

    @Test
    public void insertTest() {
        if(accountMapper.findByMajor("软件工程")==null){
            //Account row = accountMapper.insert("软件工程");
            //System.out.println(row);
        }
    }

}
