package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//开启定时任务功能
@EnableScheduling
//开启缓存 springboot自带的缓存框架
@EnableCaching
//开启注解使用缓存 jetcache缓存框架
//@EnableCreateCacheAnnotation
public class Application {

    public static void main(String[] args) {
//        String[] arg = new String[1];
//        arg[0] = "--server.port=8082";
//        //可以在启动Springboot程序时断开读取外部临时属性的配置入口
//        //SpringApplication.run(Application.class);
        //System.setProperty("spring.devtools.restart.enabled","false");//停止热更新
        SpringApplication.run(Application.class, args);
    }

}
