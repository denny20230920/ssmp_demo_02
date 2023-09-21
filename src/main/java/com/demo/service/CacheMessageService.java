package com.demo.service;

import com.demo.pojo.SmsCode;
import org.apache.http.HttpResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CacheMessageService {

    //获取缓存数据
    public String sendCha(String telephone);

    //验证缓存数据
    public boolean checkCha(SmsCode smsCode);

    //获取缓存数据-换成memcached工具实现
    public String sendCha1(String telephone);

    //验证缓存数据-换成memcached工具实现
    public boolean checkCha1(SmsCode smsCode);

//    //获取缓存数据-换成jetcache+redis工具实现
//    public String sendCha2(String telephone);
//
//    //验证缓存数据-换成jetcache+redis 工具实现
//    public boolean checkCha2(SmsCode smsCode);

    //获取图片验证码
    public void imgCapcha(HttpServletRequest request, HttpServletResponse response);

    //校验图片验证码
    boolean checkImgCapcha(HttpServletRequest request,String code);

}
