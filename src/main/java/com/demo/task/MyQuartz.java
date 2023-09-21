package com.demo.task;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

//定义一个工作对象
public class MyQuartz extends QuartzJobBean {
    /**
     * @param context
     * @throws JobExecutionException
     * quartz定时任务的工作
     */
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

        System.out.println("quartz task run...");

    }
}
