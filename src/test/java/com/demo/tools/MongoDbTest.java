package com.demo.tools;

import com.demo.pojo.BookCase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

@SpringBootTest
public class MongoDbTest {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    BookCase bookCase;

    @Test
    public void saveTest(){
        //新增数据
        mongoTemplate.save(bookCase);
    }

    @Test
    public void updateTest(){
        //修改数据
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(616006433));

        Update update = new Update();
        update.set("name","张三");

        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, BookCase.class);
        System.out.println(updateResult);

    }

    @Test
    public void removeTest(){
        //删除数据
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(1138667667));
        DeleteResult remove = mongoTemplate.remove(query, BookCase.class);
        System.out.println(remove);

    }


    @Test
    public void findTest(){
//        //查询全部数据
//        List<BookCase> all = mongoTemplate.findAll(BookCase.class);
//        for (BookCase book : all){
//            System.out.println(book);
//        }

        //按数据ID查询
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(-906073446));
        List<BookCase> bookCases = mongoTemplate.find(query, BookCase.class);
        for (BookCase bookCase2 : bookCases){
            System.out.println(bookCase2);
        }
    }

}
