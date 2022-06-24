package com.atguigu.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class FlowLimitController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/testA")
    public String testA() {
        // 暂停毫秒
//        try { TimeUnit.MILLISECONDS.sleep(800); } catch (InterruptedException e) { e.printStackTrace(); }
        return "--------testA";
    }

    @GetMapping("/testB")
    public String testB() {
        log.info(Thread.currentThread().getName() + "\t" + "...testB");
        return "--------testB";
    }

    // 1、直接流控模式（根据阈值类型设置即可生效）

    // 2、关联流控模式（当资源b达到阈值，资源a也不能访问，例如：支付有问题，下单也暂停）

    // 3、测试链路流控模式
    // 链路流控模式指的是，当从某个接口过来的资源达到限流条件时，开启限流
    // 资源名是由@SentinelResource注解标注，入口资源是需要限流的请求路径，其他使用该资源的接口不受影响
    @GetMapping("/order/message1")
    public String message1() {
        orderService.dosomething();
        return "message1";
    }

    @GetMapping("/order/message2")
    public String message2() {
        orderService.dosomething();
        return "message2";
    }

    // Warm Up(预热，公式：阈值除以冷加载因子coldFactor(默认值为3),经过预热时长后才会达到阈值)

    // 排队等待，只能选择QPS阈值类型，超时时间毫秒


    // 熔断规则

    /**
     * 1、慢调用比例
     * 最大RT：200ms    响应时间（毫秒），超过该值则计为慢调用
     * 比例阈值： 1      [0.0,1.0] 即0%~100%
     * 熔断时长：1s
     * 最小请求数：5     触发熔断的最小请求数目，若当前统计窗口内的请求数小于此值，即使达到熔断条件规则也不会出发
     * 统计时长：1000ms
     * 总结：当1秒内有大于等于5个请求的响应100%大于0.2秒时，服务熔断1秒
     */
    @GetMapping("/testD")
    public String testD() {
        // 暂停几秒钟线程
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("testD 测试RT");
        return "--------testD";
    }

    /**
     * 2、异常比例，同慢调用比例，根据比例阈值来判断
     *
     * @return
     */
    @GetMapping("/testE")
    public String testE() {
        log.info("testE 测试异常比例");
        int age = 10 / 0;
        return "--------testE";
    }

    /**
     * 热点限流规则
     * 参数索引：0
     * 单机阈值：1
     * 统计窗口时长：1s
     * 方法testHotKey里面第一个参数只要QPS超过每秒1次，马上降级处理，使用我们的兜底方法deal_testHotKey
     * blockHandler未填则返回Error Page
     *
     * 参数例外项
     * VIP客户：根据参数值，设定限流阈值
     */
    @GetMapping("/testHotkey")
    @SentinelResource(value = "testHotkey", blockHandler = "deal_testHotKey")
    public String testHotkey(@RequestParam(value = "p1", required = false) String p1,
                             @RequestParam(value = "p2", required = false) String p2) {
        return "--------testHotkey";
    }

    public String deal_testHotKey(String p1, String p2, BlockException exception) {
        // sentinel系统默认提示：Blocked by Sentinel (flow limiting)
        return "--------deal_testHotKey,/(ㄒoㄒ)/~~";
    }
}
