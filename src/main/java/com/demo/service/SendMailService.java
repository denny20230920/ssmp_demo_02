package com.demo.service;

import javax.mail.MessagingException;

public interface SendMailService {

    //发送简单邮件
    void sendMail();

    //发送多部件的邮件(带附件、图片、超链接html等)
    void sendMineMail();

}
