package com.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.controller.result.Code;
import com.demo.exception.BusinessException;
import com.demo.mapper.BookMapper;
import com.demo.pojo.Book;
import com.demo.pojo.MyPage;
import com.demo.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookMapper bookMapper;

    @Override
    public boolean addBook(Book book) {


        if (book == null){
            log.error("参数错误，数据:"+book);
            throw new BusinessException(Code.BUSINESS_COMMON_PARAM_ERR,"参数错误！");
        }

        if (book.getName()==null||book.getName()==""){
            log.error("图书名称不能为空，数据:"+book);
            throw new BusinessException(Code.BUSINESS_BOOK_MNAME_NULL,"图书名称不能为空!");
        }

        //新增图书前查询是否已新增
        Book bookByName = findBookByName(book.getName());

        if (bookByName != null){
            throw new BusinessException(Code.BUSINESS_BOOK_EXISTS_ERR,"该图书名称已存在");
        }

        //强行将传过来的数据变成null，这样就会按照MP的自定义规则走
        book.setId(null);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        book.setCreateTime(simpleDateFormat.format(new Date()));

        int insert =  bookMapper.insert(book);

        return insert>0;
    }

    //通过书籍名称查询
    @Override
    public Book findBookByName(String bookName) {

        if (bookName == null){
            throw new BusinessException(Code.BUSINESS_COMMON_PARAM_ERR,"参数错误！");
        }

        LambdaQueryWrapper<Book> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Book::getName,bookName);

        Book book1 = bookMapper.selectOne(lqw);

        return book1;
    }

    @Override
    public Book findBookById(Integer id) {

        if (id < 0){
            throw new BusinessException(Code.BUSINESS_COMMON_PARAM_ERR,"参数错误！");
        }

        Book book = bookMapper.selectById(id);

        return book;
    }

    @Override
    public IPage<Book> findBookByPage(Integer currentPage, Integer pageSize,Book book) {

        IPage<Book> page = new Page<>(currentPage,pageSize);

        LambdaQueryWrapper<Book> lqw = new LambdaQueryWrapper<>();

        if (book != null) {
            lqw.like(null != book.getName(), Book::getName, book.getName());
            lqw.like(null != book.getType(), Book::getType, book.getType());
            lqw.like(null != book.getDescription(), Book::getDescription, book.getDescription());
        }
        //按创建时间的倒序排列
        lqw.orderByDesc(Book::getCreateTime);

        IPage<Book> bookIPage = bookMapper.selectPage(page, lqw);

        //请求的当前页码大于查询结果的总页数，需要重新发起请求，且回到首页
        if (currentPage > bookIPage.getPages()){
            currentPage = (int)bookIPage.getPages();
            bookIPage = findBookByPage(currentPage,pageSize,book);
        }

        return bookIPage;
    }

    @Override
    public boolean deleteBook(Integer id) {

        if (id < 0){
            throw new BusinessException(Code.BUSINESS_COMMON_PARAM_ERR,"参数错误！");
        }

        Book book1 = findBookById(id);
        if (book1 == null){
            throw new BusinessException(Code.BUSINESS_BOOK_NOEXISTS_ERR,"图书不存在,请重试！");
        }

        int i = bookMapper.deleteById(id);

        return i>0;
    }

    @Override
    public boolean updateBook(Book book) {

        if (book == null){
            throw new BusinessException(Code.BUSINESS_COMMON_PARAM_ERR,"参数错误！");
        }

        Book book1 = findBookById(book.getId());
        if (book1 == null){
            throw new BusinessException(Code.BUSINESS_BOOK_NOEXISTS_ERR,"图书不存在,请重试！");
        }

        int i = bookMapper.updateById(book);

        return i>0;
    }
}
