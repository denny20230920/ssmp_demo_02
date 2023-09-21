package com.demo.service.impl;

import com.demo.controller.result.Code;
import com.demo.exception.BusinessException;
import com.demo.pojo.SmsCode;
import com.demo.service.CacheMessageService;
import com.demo.utils.CheckCodeUtil;
import com.demo.utils.CodeUtil;
import net.rubyeye.xmemcached.MemcachedClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.util.concurrent.TimeUnit;

@Service
public class CacheMessageServiceImpl implements CacheMessageService {

    @Autowired
    CodeUtil codeUtil;

    @Autowired
    MemcachedClient memcachedClient;

//    //area不配置时，使用默认的缓存空间，配置后需要读取配置文件中配置的缓存空间，名称要保持一样
//    //name表示和key一起生成唯一key，expire表示配置key的时效性，默认秒，timeUnit可不配置
//    @CreateCache(area = "sms",name = "jetCache_",expire = 10,timeUnit = TimeUnit.SECONDS)
//    Cache<String,String> jetCache;
//
//    @Override
//    public String sendCha2(String telephone) {
//        String code = codeUtil.generator(6);
//        jetCache.put(telephone,code);
//        return code;
//    }
//
//    @Override
//    public boolean checkCha2(SmsCode smsCode) {
//        String code = smsCode.getCode();
//        String queryCode = jetCache.get(smsCode.getTelephone());
//        return code.equals(queryCode);
//    }

    /**
     * 获取缓存数据
     * @param telephone
     * @return
     */
    @Override
    @CachePut(value = "smsMessage",key = "#telephone")
    public String sendCha(String telephone) {
        String code = codeUtil.generator(6);
        return code;
    }

    /**
     * 校验缓存数据
     * @param smsCode
     * @return
     */
    @Override
    public boolean checkCha(SmsCode smsCode) {
        //System.out.println("请求验证码数据:"+smsCode.getCode());
        String queryCode = codeUtil.getCode(smsCode.getTelephone());
        //System.out.println("获取缓存的数据:"+queryCode);
        return smsCode.getCode().equals(queryCode);
    }

    /**
     * @param telephone
     * @return
     */
    @Override
    public String sendCha1(String telephone) {
        String code = codeUtil.generator(6);
        try {
            memcachedClient.set(telephone,10,code);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return code;
    }

    /**
     * @param smsCode
     * @return
     */
    @Override
    public boolean checkCha1(SmsCode smsCode) {

        String code = null;

        try {
           code = memcachedClient.get(smsCode.getTelephone().toString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return smsCode.getCode().equals(code);
    }

    /**
     * 生成图片验证码
     * @param response
     */
    @Override
    public void imgCapcha(HttpServletRequest request, HttpServletResponse response) {

        try{
            //图片验证码存入到Session中，方便登录或注册时校验
            HttpSession session = request.getSession();

            //设置响应头，防止缓存
            response.setHeader("Cache-Control","no-store,no-cache");
            //设置响应内容格式
            response.setContentType("image/png;charset=UTF-8");
            OutputStream outputStream = response.getOutputStream();
            //生成图片验证码
            String code = CheckCodeUtil.outputVerifyImage(100,50,outputStream,4);
            //System.out.println("验证码信息:"+code);

            //设置图片验证码
            session.setAttribute("checkCodeGen",code);

        }catch (Exception e){
            //生成图片验证码失败
            throw new BusinessException(Code.BUSINESS_CAPTCHA_ERR,"图片验证码生成错误,请重试！");
        }

    }

    /**
     * @param code
     * @return
     */
    @Override
    public boolean checkImgCapcha(HttpServletRequest request,String code) {

        //获取程序中已生成的图片验证码
        HttpSession session = request.getSession();
        String checkCodeGen = (String)session.getAttribute("checkCodeGen");

        return code.toLowerCase().equals(checkCodeGen.toLowerCase());
    }

}
