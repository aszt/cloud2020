package com.atguigu.springcloud.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

//    将此方法标注为sentinel的资源。value=资源名
    @SentinelResource("dosomething")
    public void dosomething() {
        System.out.println("do something");
    }

}
