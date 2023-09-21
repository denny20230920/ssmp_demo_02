package com.demo.utils;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class CodeUtil {



    //生成n位验证码，带有字母的简单算法
    public String generator(int n){

        String chars = "0123456789";
        String chars1 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        StringBuffer stringBuffer = new StringBuffer();

        Random random = new Random();

        for (int i=0;i < n;i++){
            //根据字符串的长度大小，随机生成一个数
            int index = random.nextInt(chars.length());
            //根据生成的随机数，获取字符串中对应的字符数据,并把它追加到定义的字符串中
            stringBuffer.append(chars.charAt(index));
        }

        return stringBuffer.toString();
    }

    @Cacheable(value = "smsMessage",key = "#telephone")
    public String getCode(String telephone){
        return null;
    }

}
