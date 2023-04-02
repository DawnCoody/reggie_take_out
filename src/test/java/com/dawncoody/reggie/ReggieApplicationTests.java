package com.dawncoody.reggie;

import com.dawncoody.reggie.service.impl.MailServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

@SpringBootTest
@Slf4j
class ReggieApplicationTests {
    @Autowired
    MailServiceImpl mailService;
    private String createCode() {
        StringBuilder code = new StringBuilder();
        Random random = new Random();
        for (int i = 0 ; i < 5; i++) {
            int ran = random.nextInt(10);
            code.append(ran);
        }
        return code.toString();
    }

    @Test
    void testSendMail() {
        mailService.sendEmail("3050511039@qq.com","dawntruecool@gmail.com","test",createCode());
    }


}
