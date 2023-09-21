package com.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class MyPage<T> {

    private Integer currentPage;

    private Integer pageSize;

    private Integer total;

    private T data;

}
