package com.demo.service;

public interface MessageService {

    //消息发送
    void sendMessage(String id);

    //消息处理
    String doMessage();

}
