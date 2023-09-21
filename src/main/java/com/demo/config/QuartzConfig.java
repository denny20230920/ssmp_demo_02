package com.demo.config;


import com.demo.task.MyQuartz;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {

    //定义工作明细，绑定工作
    //@Bean
    public JobDetail jobDetail(){
        //绑定具体的工作
        //如果没有用到会被干掉，对象不使用时需要storeDurably做持久化
        return JobBuilder.newJob(MyQuartz.class).storeDurably().build();
    }

    //定义一个触发器，绑定工作明细
    //@Bean
    public Trigger trigger(){
        //定义一个Schedule
        ScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/20 * * * * ?");
        //绑定具体的工作明细
        return TriggerBuilder.newTrigger().forJob(jobDetail()).withSchedule(scheduleBuilder).build();
    }

}
