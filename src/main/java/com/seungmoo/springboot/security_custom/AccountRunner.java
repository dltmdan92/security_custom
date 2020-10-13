package com.seungmoo.springboot.security_custom;

import com.seungmoo.springboot.security_custom.account.Account;
import com.seungmoo.springboot.security_custom.account.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AccountRunner implements ApplicationRunner {
    @Autowired
    AccountService accountService;

    Logger logger = LoggerFactory.getLogger(AccountRunner.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Account seungmoo = accountService.createAccount("seungmoo", "1234");
        // password는 인코더로 인해 bcrypt형식으로 바뀔 것임
        // 참고로 패스워드는 절대 로그에서 찍지 말자
        logger.info(seungmoo.getUsername() + " password : " + seungmoo.getPassword());
    }
}
