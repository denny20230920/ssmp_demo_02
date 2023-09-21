package com.demo.controller;


import com.demo.controller.result.Code;
import com.demo.controller.result.JsonResult;
import com.demo.exception.BusinessException;
import com.demo.pojo.SmsCode;
import com.demo.service.CacheMessageService;
import com.demo.utils.CheckCodeUtil;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

@RestController
@RequestMapping("/capcha")
public class CacheMessageController {

    @Autowired
    CacheMessageService cacheMessageService;

    @GetMapping("/{telephone}")
    public JsonResult<String> sendCha(@PathVariable String telephone){

        String code = cacheMessageService.sendCha(telephone);

        return new JsonResult<>(Code.GET_OK,code);
    }

    @GetMapping
    public JsonResult<Boolean> checkCha(SmsCode smsCode){

        boolean bool = cacheMessageService.checkCha(smsCode);

        return new JsonResult<>(bool?Code.GET_OK:Code.GET_ERR,bool);
    }

    @GetMapping("/v1/{telephone}")
    public JsonResult<String> sendCha1(@PathVariable String telephone){

        String code = cacheMessageService.sendCha1(telephone);

        return new JsonResult<>(Code.GET_OK,code);
    }

    @GetMapping("/v1")
    public JsonResult<Boolean> checkCha1(SmsCode smsCode){

        boolean bool = cacheMessageService.checkCha1(smsCode);

        return new JsonResult<>(bool?Code.GET_OK:Code.GET_ERR,bool);
    }

//    @GetMapping("/v2")
//    public JsonResult<Boolean> checkCha2(SmsCode smsCode){
//
//        boolean bool = cacheMessageService.checkCha2(smsCode);
//
//        return new JsonResult<>(bool?Code.GET_OK:Code.GET_ERR,bool);
//    }
//
//    @GetMapping("/v2/{telephone}")
//    public JsonResult<String> sendCha2(@PathVariable String telephone){
//
//        String code = cacheMessageService.sendCha2(telephone);
//
//        return new JsonResult<>(Code.GET_OK,code);
//    }


    @GetMapping("/img")
    public void imgCapcha(HttpServletRequest request, HttpServletResponse response){

        cacheMessageService.imgCapcha(request,response);

    }

    @GetMapping("/check/{code}")
    public JsonResult<Boolean> checkImgCapcha(HttpServletRequest request,
                                              @PathVariable String code){

        boolean bool = cacheMessageService.checkImgCapcha(request, code);

        return new JsonResult<>(bool?Code.GET_OK:Code.GET_ERR,bool);
    }

}
