package com.demo.pojo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "testcase.book")
public class BookCase {

    private Integer id;//: ${random.int}
    private Integer id2;//: ${random.int(10)} #0~10的随机数
    private Integer id3;//: ${random.int(1-10)} #1~10的随机数
    private String name;//: ${random.value}
    private String type;//: ${random.value}
    private String description;//: ${random.uuid}

}
