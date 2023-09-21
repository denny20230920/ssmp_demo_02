package com.demo.service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.demo.Application;
import com.demo.pojo.Book;
import com.demo.pojo.BookCase;
import com.demo.pojo.MyPage;
import com.demo.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootTest(classes = Application.class)
public class BookServiceTest {

    @Autowired
    BookService bookService;

    @Autowired
    BookCase bookCase;

    @Test
    public void dataBookTest(){
        System.out.println(bookCase);
    }

    @Test
    public void addBook(){

        Book book = new Book();
        book.setType("文学");
        book.setName("傲慢与偏见");
        book.setDescription("英国文学经典之一");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        book.setCreateTime(simpleDateFormat.format(new Date()));

        boolean b = bookService.addBook(book);

        System.out.println(b);
    }

    @Test
    public void findBookByNameTest(){

        Book book = new Book();
        book.setName("傲慢与偏见");

        Book bookByName = bookService.findBookByName(book.getName());

        System.out.println(bookByName);

    }

    @Test
    public void findBookByIdTest(){

        Book bookById = bookService.findBookById(1);

        System.out.println(bookById);
    }

    @Test
    public void findBookByPageTest(){

        Book book = new Book();

        IPage<Book> allBooks = bookService.findBookByPage(1,5,book);

        String jsonString = JSON.toJSONString(allBooks);

        System.out.println(jsonString);

    }

}
