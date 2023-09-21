package com.demo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.pojo.Book;
import com.demo.pojo.MyPage;

import java.util.List;

public interface BookService {

    //新增书籍
    boolean addBook(Book book);

    //按书籍名称查询
    Book findBookByName(String bookName);

    //按书籍Id查询
    Book findBookById(Integer id);

    //分页查询(条件分页查询、全部分页查询)
    IPage<Book> findBookByPage(Integer currentPage, Integer pageSize,Book book);

    //删除图书
    boolean deleteBook(Integer id);

    //编辑图书
    boolean updateBook(Book book);

}
