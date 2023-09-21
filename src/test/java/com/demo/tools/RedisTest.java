//package com.demo.tools;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.redis.core.HashOperations;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.data.redis.core.ValueOperations;
//
//import java.util.Set;
//
//@SpringBootTest
//public class RedisTest {
//
//    @Autowired
//    private RedisTemplate redisTemplate;
//
//    @Autowired
//    private StringRedisTemplate stringRedisTemplate;
//
//    //以对象为操作基础
//    @Test
//    public void setTest(){
//
//        ValueOperations ops = redisTemplate.opsForValue();
//        ops.set("name","张三");
//        ops.set("age","10");
//
//    }
//
//    @Test
//    public void getTest(){
//        ValueOperations ops = redisTemplate.opsForValue();
//        Object object = ops.get("name");
//        System.out.println(object);
//        Object object1 = ops.get("age");
//        System.out.println(object1);
//    }
//
//    @Test
//    public void hsetTest(){
//
//        HashOperations hashOperations = redisTemplate.opsForHash();
//        hashOperations.put("user1","name","王五");
//        hashOperations.put("user1","age","20");
//        hashOperations.put("user1","sex","男");
//
//    }
//
//    @Test
//    public void hgetTest(){
//        HashOperations hashOperations = redisTemplate.opsForHash();
//        Object object = hashOperations.get("user2", "name");
//        System.out.println(object);
//
//    }
//
//    //以字符串为操作基础
//    @Test
//    public void setStringTest(){
//        ValueOperations<String, String> stringValueOperations = stringRedisTemplate.opsForValue();
//        stringValueOperations.set("value1","100");
//        stringValueOperations.set("value2","200");
//
//    }
//
//    @Test
//    public void getStringTest(){
//        ValueOperations<String, String> stringValueOperations = stringRedisTemplate.opsForValue();
//        String s = stringValueOperations.get("value1");
//        System.out.println(s);
//        String s1 = stringValueOperations.get("value2");
//        System.out.println(s1);
//        String s2 = stringValueOperations.get("value3");
//        System.out.println(s2);
//    }
//
//    @Test
//    public void hsetStringTest(){
//        HashOperations<String, Object, Object> soop = stringRedisTemplate.opsForHash();
//        soop.put("user0","name","李四");
//        soop.put("user0","age","32");
//        soop.put("user0","sex","女");
//        Object object = soop.get("user0", "name");
//        System.out.println(object);
//        Set<Object> user0 = soop.keys("user0");
//    }
//
//
//
//
//}
