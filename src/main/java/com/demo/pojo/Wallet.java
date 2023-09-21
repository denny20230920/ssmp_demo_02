package com.demo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

@Data
public class Wallet {

    @TableId(type= IdType.ASSIGN_ID)
    private Long id;//bigint comment '钱包id',

    private Long uid;//bigint comment '用户id',

    private Integer freeze;//int comment '冻结金额',

    private Integer available;//int comment '可用余额',

    private Integer money;//int default 0 comment '总余额',

    @Version
    private Integer version;//int default 1 comment '乐观锁',

}
