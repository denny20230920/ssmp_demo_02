package com.demo.tools;

import com.alibaba.fastjson.JSON;
import com.demo.mapper.BookMapper;
import com.demo.pojo.Book;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.*;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.xcontent.XContentType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

@SpringBootTest
public class EsTest {

    @Autowired
    BookMapper bookMapper;

    //使用高版本的客户端
    private RestHighLevelClient restHighLevelClient;

    @BeforeEach
    void setUp() {
        HttpHost host = HttpHost.create("http://localhost:9200");
        RestClientBuilder builder = RestClient.builder(host);
        restHighLevelClient = new RestHighLevelClient(builder);
    }

    @AfterEach
    void tearDown() {
        try {
            restHighLevelClient.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void createClientTest(){

        HttpHost host = HttpHost.create("http://localhost:9200");

        RestClientBuilder builder = RestClient.builder(host);

        restHighLevelClient = new RestHighLevelClient(builder);

        try {
            restHighLevelClient.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    public void createIndexTest(){

        try {
            //创建索引
            CreateIndexRequest requestIndex = new CreateIndexRequest("books");

            //请求中设置分词器
            String json = "{\n" +
                    "    \"mappings\":{\n" +
                    "        \"properties\":{\n" +
                    "            \"id\":{\n" +
                    "                \"type\":\"keyword\"\n" +
                    "            },\n" +
                    "            \"name\":{\n" +
                    "                \"type\":\"text\",\n" +
                    "                \"analyzer\":\"ik_max_word\",\n" +
                    "                \"copy_to\":\"all\"\n" +
                    "            },\n" +
                    "            \"type\":{\n" +
                    "                \"type\":\"keyword\"\n" +
                    "            },\n" +
                    "            \"description\":{\n" +
                    "                \"type\":\"text\",\n" +
                    "                \"analyzer\":\"ik_max_word\",\n" +
                    "                \"copy_to\":\"all\"\n" +
                    "            },\n" +
                    "            \"all\":{\n" +
                    "                \"type\":\"text\",\n" +
                    "                \"analyzer\":\"ik_max_word\"\n" +
                    "            }\n" +
                    "        }\n" +
                    "    }\n" +
                    "}";
            requestIndex.source(json, XContentType.JSON);
            //使用默认的请求参数，创建索引
            restHighLevelClient.indices().create(requestIndex,RequestOptions.DEFAULT);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //单个创建文档
    @Test
    public void createDocTest(){

        Book book = bookMapper.selectById(6);
        System.out.println(book);

        try {
            //创建文档，给出需要操作的索引，和生成文档的Id
            IndexRequest indexRequest = new IndexRequest("books").id(book.getId().toString());
            //设置请求体的参数
            indexRequest.source(JSON.toJSONString(book),XContentType.JSON);

            restHighLevelClient.index(indexRequest,RequestOptions.DEFAULT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    //创建多个文档
    public void createBatchsDocTest(){
        try{
            List<Book> books = bookMapper.selectList(null);
            //创建一个批量处理文档的请求
            BulkRequest bulkRequest = new BulkRequest();

            for(Book book:books){
                IndexRequest indexRequest = new IndexRequest("books").id(book.getId().toString());
                indexRequest.source(JSON.toJSONString(book),XContentType.JSON);
                //添加需要批量处理的文档
                bulkRequest.add(indexRequest);
            }
            //批量处理创建文档
            restHighLevelClient.bulk(bulkRequest,RequestOptions.DEFAULT);

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Test
    //查询单个文档
    public void getOneDoc(){
        try{
            GetRequest getRequest = new GetRequest("books","6");
            GetResponse documentFields = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
            String sourceAsString = documentFields.getSourceAsString();
            System.out.println(sourceAsString);
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

    @Test
    //按条件查询文档
    public void getOptionDocTest(){
        try {
            //创建按条件查询索引
            SearchRequest searchRequest = new SearchRequest("books");

            //创建条件查询条件
            SearchSourceBuilder builder = new SearchSourceBuilder();
            //查询name属性中包含“福尔摩斯探案集”
            builder.query(QueryBuilders.termQuery("all","美食"));
            searchRequest.source(builder);

            SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

            SearchHits hits = search.getHits();
            for (SearchHit hit : hits){
                System.out.println(hit.getSourceAsString());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
