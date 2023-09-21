package com.demo.service;

import com.demo.pojo.Wallet;

public interface WalletService {

    //新增钱包
    boolean addWallet(Wallet wallet);

    //用户id查询钱包信息
    Wallet findWalletByUid(Long uid);

}
