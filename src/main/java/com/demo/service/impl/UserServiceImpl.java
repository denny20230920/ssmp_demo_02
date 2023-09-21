package com.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.demo.controller.result.Code;
import com.demo.exception.BusinessException;
import com.demo.mapper.UserMapper;
import com.demo.pojo.User;
import com.demo.pojo.query.UserQuery;
import com.demo.pojo.Wallet;
import com.demo.service.UserService;
import com.demo.service.WalletService;
import com.demo.utils.Md5PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    WalletService walletService;

    @Override
    public boolean register(User user) {

        User userByName = findUserByName(user);

        if ( userByName != null){
            throw new BusinessException(Code.BUSINESS_USER_EXISTS_ERR,"该用户名已注册");
        }

        String salt = UUID.randomUUID().toString().toUpperCase();
        user.setSalt(salt);
        user.setPassword(Md5PasswordUtil.getMd5Password(user.getPassword(),salt));
        user.setDeleted(1);
        user.setDisabled(1);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        user.setCreateTime(simpleDateFormat.format(new Date()));
        user.setCreateUser("administrator");

        int insert = userMapper.insert(user);

        User user2 = findUserByName(user);

        Wallet wallet = new Wallet();
        wallet.setUid(user2.getId());
        wallet.setMoney(0);

        boolean b = walletService.addWallet(wallet);

        return insert>0&&b;
    }

    @Override
    public User login(User user) {

        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getUsername,user.getUsername());

        User user1 = userMapper.selectOne(lqw);

        if (user1 == null){
            throw new BusinessException(Code.BUSINESS_USER_NP_ERR,"用户名或密码错误");
        }

        String password = user.getPassword();
        String salt = user1.getSalt();
        String md5Password = Md5PasswordUtil.getMd5Password(password, salt);

        if (!user1.getPassword().equals(md5Password)){
            throw new BusinessException(Code.BUSINESS_USER_NP_ERR,"用户名或密码错误");
        }

        Wallet walletByUid = walletService.findWalletByUid(user1.getId());

        return user1;
    }

    @Override
    public User findUserByName(User user) {

        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();

        lqw.eq(User::getUsername,user.getUsername());

        User user1 = userMapper.selectOne(lqw);

        return user1;
    }

    @Override
    public User findUserById(Long id) {

        if (id < 0){
            throw new BusinessException(Code.BUSINESS_COMMON_PARAM_ERR,"参数错误！");
        }

        User user = userMapper.selectById(id);

        return user;
    }

    @Override
    public List<User> findUsersBetweenAge(UserQuery userQuery) {

        if (userQuery == null){
            throw new BusinessException(Code.BUSINESS_COMMON_PARAM_ERR,"参数错误！");
        }

        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.between(User::getAge,userQuery.getAge(),userQuery.getAge2());

        List<User> users = userMapper.selectList(lqw);

        return users;
    }

    @Override
    public boolean deleteUser(Long id) {

        if (id<0){
            throw new BusinessException(Code.BUSINESS_COMMON_PARAM_ERR,"参数错误！");
        }

        int i = userMapper.deleteById(id);

        return i>0;
    }

    @Override
    public boolean updateUser(User user) {

        if (user == null){
            throw new BusinessException(Code.BUSINESS_COMMON_PARAM_ERR,"参数错误！");
        }

        int i = userMapper.updateById(user);

        return i>0;
    }
}
