package com.demo.service.impl.base;

import com.demo.service.MessageService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

//@Service
public class MessageServiceImpl implements MessageService {

    ArrayList<String> arrayList = new ArrayList<>();

    /**
     * @param id
     * 发送消息
     */
    @Override
    public void sendMessage(String id) {
        System.out.println("待发送短息的订单，已纳入消息处理队列，Id:"+id);
        arrayList.add(id);
    }

    /**
     * @return
     * 处理消息
     */
    @Override
    public String doMessage() {
        String id = arrayList.remove(0);//从第一个开始删除已处理的Id
        System.out.println("已完成短信业务发送，Id:"+id);
        return id;
    }
}
