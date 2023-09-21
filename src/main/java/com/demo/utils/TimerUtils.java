package com.demo.utils;

import java.util.Timer;
import java.util.TimerTask;


//Timer定时任务的示例
public class TimerUtils {

    public static void main(String[] args) {
        //创建一个定时器
        Timer timer = new Timer();
        //创建一个任务
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("定时任务执行了~");
            }
        };
        //从当前时间的10秒后开始，每隔2秒执行一次任务
        timer.schedule(task,10000,2000);
    }

}
