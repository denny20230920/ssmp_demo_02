package com.demo.service.impl.activemq;

import com.demo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MessageServiceActiveMQImpl implements MessageService {

    @Autowired
    private JmsMessagingTemplate messagingTemplate;

    /**
     * @param id
     * 发送消息
     */
    @Override
    public void sendMessage(String id) {
        System.out.println("待发送短息的订单，已纳入消息处理队列，Id:"+id);
        messagingTemplate.convertAndSend("order.queue.id",id);//执行消息队列的名称，名称规则化
    }

    /**
     * @return
     * 处理消息
     */
    @Override
    public String doMessage() {
        String id = messagingTemplate.receiveAndConvert("order.queue.id",String.class);
        System.out.println("已完成短信业务发送，Id:"+id);
        return id;
    }
}
