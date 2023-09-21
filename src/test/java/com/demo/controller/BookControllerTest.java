package com.demo.controller;

import com.alibaba.fastjson.JSON;
import com.demo.pojo.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.ContentResultMatchers;
import org.springframework.test.web.servlet.result.HeaderResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.result.StatusResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Transactional//测试数据控制，回滚测试生成的数据
@Rollback(true)//默认值是true表示回滚，false表示不回滚，写数据到库里
public class BookControllerTest {

    @Test
    public void findBookByIdTest(@Autowired MockMvc mvc) throws Exception {

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/books/10");
        ResultActions action = mvc.perform(builder);

        StatusResultMatchers status = MockMvcResultMatchers.status();
        ResultMatcher resultOk = status.isOk();
        action.andExpect(resultOk);

        ContentResultMatchers content = MockMvcResultMatchers.content();
        ResultMatcher resultJson = content.json("{\"code\":20021,\"msg\":\"success\",\"data\":{\"id\":10,\"type\":\"心理学\",\"name\":\"思考快与慢\",\"description\":\"心理学大师的思考方式\",\"createTime\":\"2023-09-11 04:40:00\"}}");
        action.andExpect(resultJson);

        HeaderResultMatchers header = MockMvcResultMatchers.header();
        ResultMatcher resultString = header.string("Content-Type", "application/json;charset=UTF-8");
        action.andExpect(resultString);
    }

    @Test
    public void addBookTest(@Autowired MockMvc mvc) throws Exception {

        Book book = new Book();
        book.setName("110");
        book.setType("220");
        book.setDescription("330");
        SimpleDateFormat simp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        book.setCreateTime(simp.format(new Date()));

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
                .post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSON.toJSONString(book));
        ResultActions action = mvc.perform(builder);

        StatusResultMatchers status = MockMvcResultMatchers.status();
        ResultMatcher resultOk = status.isOk();
        action.andExpect(resultOk);

        ContentResultMatchers content = MockMvcResultMatchers.content();
        ResultMatcher resultJson = content.json("{\"code\":20011,\"msg\":null,\"data\":true}");
        action.andExpect(resultJson);

        HeaderResultMatchers header = MockMvcResultMatchers.header();
        ResultMatcher resultString = header.string("Content-Type", "application/json;charset=UTF-8");
        action.andExpect(resultString);
    }

    @Test
    public void updateBookTest(@Autowired MockMvc mvc) throws Exception {

        Book book = new Book();
        book.setId(6);
        book.setName("福尔摩斯探案集");
        book.setType("悬疑");
        book.setDescription("神秘案件的侦破");
        SimpleDateFormat simp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        book.setCreateTime(simp.format(new Date()));

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
                .put("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSON.toJSONString(book));
        ResultActions action = mvc.perform(builder);

        StatusResultMatchers status = MockMvcResultMatchers.status();
        ResultMatcher resultOk = status.isOk();
        action.andExpect(resultOk);

        ContentResultMatchers content = MockMvcResultMatchers.content();
        ResultMatcher resultJson = content.json("{\"code\":20031,\"msg\":null,\"data\":true}");
        action.andExpect(resultJson);

        HeaderResultMatchers header = MockMvcResultMatchers.header();
        ResultMatcher resultString = header.string("Content-Type", "application/json;charset=UTF-8");
        action.andExpect(resultString);
    }

}
