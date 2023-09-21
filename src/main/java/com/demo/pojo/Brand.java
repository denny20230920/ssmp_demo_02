package com.demo.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
public class Brand {

    @TableId(type = IdType.AUTO)
    private Integer id; //int auto_increment comment '品牌id',

    @TableField(value = "brand_name")
    private String brandName;   //varchar(20) comment '品牌名称',

    @TableField(value = "company_name")
    private String companyName; //varchar(20) comment '公司名称',

    private Integer ordered;    //int comment '排序值',

    private String description; //varchar(100) comment '品牌描述信息',

    private Integer status; //int comment '状态:1-启用,2-禁用',

}
