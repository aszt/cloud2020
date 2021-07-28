package com.atguigu.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ribbon 负载均衡规则类
 * 自定义配置类不能放在 @ComponentScan 所扫描的当前包下以及子包下
 * 否则我们自定义的配置类会被所有的Ribbon客户端所共享，达不到特殊定制的目的
 */
@Configuration
public class MySelfRule {


    /**
     *  1、轮询算法 ===》请求次数%实例
     *  每次服务器重启后rest接口计数从1开始
     *  List[0] instances = 127.0.0.1:8002
     *  List[1] instances = 127.0.0.1:8001
     *  1 % 2 = 1 -> index = 1 list.get(index);
     *  2 % 2 = 0 -> index = 0 list.get(index);
     *  3 % 2 = 1 -> index = 1 list.get(index);
     */

    @Bean
    public IRule myRule(){
        // 定义为随机
        return new RandomRule();
    }

}
