package com.demo.pojo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;

@Data
@Validated//开启当前类的属性校验
public class SmsCode {

    @Length(min = 10,max = 20,message = "手机号码在{min}和{max}之间")
    private String telephone;

    private String code;

}
