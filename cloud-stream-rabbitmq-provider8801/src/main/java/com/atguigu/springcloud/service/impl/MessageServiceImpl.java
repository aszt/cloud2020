package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.service.IMessageService;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;
import java.util.UUID;

// 定义消息的推送管道
@EnableBinding(Source.class)
public class MessageServiceImpl implements IMessageService {

    // 消息发送管道
    @Resource
    private MessageChannel output;

    @Override
    public String send() {
        String serial = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(serial).build());
        System.out.println("***********serial:" + serial);
        return serial;
    }

}
