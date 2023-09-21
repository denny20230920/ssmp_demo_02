package com.demo.service;

import com.demo.pojo.User;
import com.demo.pojo.query.UserQuery;

import java.util.List;

public interface UserService {

    //注册
    boolean register(User user);

    //登录
    User login(User user);

    //用户名查询
    User findUserByName(User user);

    //用户Id查询
    User findUserById(Long id);

    //查询年龄段在A与B之间的用户
    List<User> findUsersBetweenAge(UserQuery userQuery);

    //删除用户
    boolean deleteUser(Long id);

    //修改用户信息(性别、年龄、头像、删除状态、禁用状态、生日)
    boolean updateUser(User user);

}
