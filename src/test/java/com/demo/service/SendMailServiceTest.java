package com.demo.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SendMailServiceTest {

    @Autowired
    SendMailService sendMailService;

    @Test
    public void sendMailServiceTest(){

        sendMailService.sendMail();

    }

    //发送多部件文件
    @Test
    public void mimeMailTest(){
        sendMailService.sendMineMail();
    }

}
