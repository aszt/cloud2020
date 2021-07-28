package com.atguigu.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {

    @Bean
    // 手写负载均衡算法，去除自带
//    @LoadBalanced //使用此注解赋予rs负载均衡的能力
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

}
