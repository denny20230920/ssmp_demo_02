package com.demo.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.demo.controller.result.Code;
import com.demo.controller.result.JsonResult;
import com.demo.pojo.Book;
import com.demo.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    BookService bookService;


    @PostMapping
    public JsonResult<Boolean> addBook(@RequestBody Book book){

        log.info("请求参数:"+book);

        boolean bool = bookService.addBook(book);

        JsonResult<Boolean> jsonResult = new JsonResult<>(bool? Code.SAVE_OK:Code.SAVE_ERR,bool);

        log.info("响应数据："+JSON.toJSONString(jsonResult));

        return jsonResult;
    }

    //按书本名称查询
    @GetMapping
    public JsonResult<Book> findBookByName(@RequestParam String bookName){

        log.info("请求数据:"+bookName);

        Book bookByName = bookService.findBookByName(bookName);

        Integer code = bookByName!=null?Code.GET_OK:Code.GET_ERR;

        String msg = bookByName!=null?"success":"failed";

        JsonResult<Book> jsonResult = new JsonResult<>(code,bookByName,msg);

        log.info("响应数据:"+JSON.toJSONString(jsonResult));

        return jsonResult;
    }

    //按书本id查询
    @GetMapping("/{id}")
    public JsonResult<Book> findBookById(@PathVariable Integer id){

        log.info("请求数据:"+id);

        Book bookById = bookService.findBookById(id);

        Integer code = bookById!=null?Code.GET_OK:Code.GET_ERR;

        String msg = bookById!=null?"success":"failed";

        JsonResult<Book> jsonResult = new JsonResult<>(code, bookById, msg);

        log.info("响应数据:"+JSON.toJSONString(jsonResult));

        return jsonResult;
    }

    //分页查询（按条件查询、按全部查询）
    @GetMapping("/{currentPage}/{pageSize}")
    public JsonResult<IPage<Book>> findBookByPage(@PathVariable Integer currentPage,
                                                  @PathVariable Integer pageSize,
                                                  Book book){

        IPage<Book> bookByPage = bookService.findBookByPage(currentPage,pageSize,book);

        Integer code = bookByPage!=null?Code.GET_OK:Code.GET_ERR;

        String msg = bookByPage.getRecords() != null ? "success" : "failed";

        return new JsonResult<>(code,bookByPage,msg);
    }

    //删除图书
    @DeleteMapping("/{id}")
    public JsonResult<Boolean> deleteBook(@PathVariable Integer id){

        boolean bool = bookService.deleteBook(id);

        return new JsonResult<>(bool? Code.DELETE_OK:Code.DELETE_ERR,bool);
    }

    @PutMapping
    public JsonResult<Boolean> updateBook(@RequestBody Book book){

        boolean bool = bookService.updateBook(book);

        return new JsonResult<>(bool? Code.UPDATE_OK:Code.UPDATE_ERR,bool);
    }

}
