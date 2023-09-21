package com.demo.service.impl.activemq.listener;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    @JmsListener(destination = "order.queue.id")//将队列中的数据传给监听方法进行处理
    @SendTo("order.other.queue.id")//获取下面方法的返回值，重新生成MQ队列
    public String recive(String id){
        //使用activeMQ的监听，自动处理消息队里
        System.out.println("已完成短信业务发送，Id:"+id);
        return  id;
    }

    @JmsListener(destination = "order.other.queue.id")//将队列中的数据传给监听方法进行处理
    public void towRecive(String id){
        //使用activeMQ的监听，自动处理消息队里的二次生成消息
        System.out.println("短信业务发送完成后，又再次处理了其他业务，Id:"+id);
    }

}
