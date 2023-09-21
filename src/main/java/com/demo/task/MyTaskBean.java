package com.demo.task;

import com.demo.service.impl.SendMailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MyTaskBean {

    @Autowired
    SendMailServiceImpl sendMailService;

    //每20秒执行一次打印
    //@Scheduled(cron = "0/20 * * * * ?")//表示从0秒开始每20秒执行一次
    public void print(){
        System.out.println("线程:"+Thread.currentThread().getName()+",在执行定时任务~"+System.currentTimeMillis());
    }

    //每隔1分钟发送一封邮件
    //@Scheduled(cron = "0/60 * * * * ?")//每隔一分钟发送一封邮件
    public void timeSendMail(){
        sendMailService.sendMail();
    }

}
