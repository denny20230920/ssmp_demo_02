package com.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.demo.controller.result.Code;
import com.demo.exception.BusinessException;
import com.demo.mapper.WalletMapper;
import com.demo.pojo.Wallet;
import com.demo.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletServiceImpl implements WalletService {

    @Autowired
    WalletMapper walletMapper;

    @Override
    public boolean addWallet(Wallet wallet) {

        int insert = walletMapper.insert(wallet);

        return insert>0;
    }

    @Override
    public Wallet findWalletByUid(Long uid) {

        if (uid < 0){
            throw new BusinessException(Code.BUSINESS_COMMON_PARAM_ERR,"参数错误！");
        }

        LambdaQueryWrapper<Wallet> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Wallet::getUid,uid);

        Wallet wallet1 = walletMapper.selectOne(lqw);

        return wallet1;
    }
}
