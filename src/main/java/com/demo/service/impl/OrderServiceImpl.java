package com.demo.service.impl;

import com.demo.service.MessageService;
import com.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private MessageService messageService;

    /**
     * @param orderId
     * 处理订单接口实现
     */
    @Override
    public void dealOrder(String orderId) {

        //一系列操作包含各种服务的调用，处理各种业务

        //订单的处理开始
        System.out.println("---订单的处理开始---");

        //短信消息处理
        messageService.sendMessage(orderId);

        //订单的处理结束
        System.out.println("---订单的处理结束---");


    }
}
