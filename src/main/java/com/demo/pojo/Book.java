package com.demo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Book {

    @TableId(type= IdType.AUTO)
    private Integer id;//bigint comment '书籍id',

    private String type;//varchar(20) comment '类别',

    private String name;//varchar(100) unique comment '名称',

    private String description;//varchar(200) comment '描述信息',

    @TableField(value = "create_time")
    private String createTime;

}
