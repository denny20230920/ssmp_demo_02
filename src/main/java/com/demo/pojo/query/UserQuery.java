package com.demo.pojo.query;

import com.demo.pojo.User;
import lombok.Data;

import java.util.Date;


@Data
public class UserQuery extends User {

    private Integer age2;

    private Date birthday2;

    private String createTime2;

    private String updateTime2;

}
