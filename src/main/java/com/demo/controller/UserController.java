package com.demo.controller;


import com.demo.controller.result.Code;
import com.demo.controller.result.JsonResult;
import com.demo.pojo.User;
import com.demo.pojo.query.UserQuery;
import com.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    //注册
    @PostMapping("/register")
    public JsonResult<Boolean> register(@RequestBody User user){

        boolean register = userService.register(user);

        return new JsonResult<>(register? Code.SAVE_OK:Code.SAVE_ERR,register);
    }

    //登录
    @PostMapping("/login")
    public JsonResult<User> login(@RequestBody User user){

        User loginUser = userService.login(user);

        Integer code = loginUser!=null?Code.GET_OK:Code.GET_ERR;
        String msg = loginUser!=null?"success":"failed";

        return new JsonResult<>(code,loginUser,msg);
    }

    //用户名查询
    @GetMapping("/findUserByName")
    public JsonResult<User> findUserByName(@RequestBody User user){

        User userByName = userService.findUserByName(user);

        Integer code = userByName!=null?Code.GET_OK:Code.GET_ERR;
        String msg = userByName!=null?"success":"failed";

        return new JsonResult<>(code,userByName,msg);
    }

    //用户Id查询
    @GetMapping("/{id}")
    public JsonResult<User> findUserById(@PathVariable Long id){

        User user = userService.findUserById(id);

        Integer code = user!=null?Code.GET_OK:Code.GET_ERR;
        String msg = user!=null?"success":"failed";

        return new JsonResult<>(code,user,msg);
    }

    //查询年龄段在A与B之间的用户
    @GetMapping("/findUsersBetweenAge")
    public JsonResult<List<User>> findUsersBetweenAge(@RequestBody UserQuery userQuery){

        List<User> usersBetweenAge = userService.findUsersBetweenAge(userQuery);

        Integer code = usersBetweenAge!=null?Code.GET_OK:Code.GET_ERR;
        String msg = usersBetweenAge!=null?"success":"failed";

        return new JsonResult<>(code,usersBetweenAge,msg);
    }

    //删除用户
    @DeleteMapping("/{id}")
    public JsonResult<Boolean> deleteUser(@PathVariable Long id){

        boolean bool = userService.deleteUser(id);

        return new JsonResult<>(bool?Code.DELETE_OK:Code.DELETE_ERR,bool);
    }

    //修改用户信息(性别、年龄、头像、删除状态、禁用状态、生日)
    @PutMapping
    public JsonResult<Boolean> updateUser(@RequestBody User user){

        boolean bool = userService.updateUser(user);

        return new JsonResult<>(bool?Code.DELETE_OK:Code.DELETE_ERR,bool);
    }

}
