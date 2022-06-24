package com.atguigu.springcloud.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;

/**
 * 创建CustomerBlockHandler类用于自定义限流处理逻辑
 */
public class CustomerBlockHandler {

    public static CommonResult handleException(BlockException exception){
        return new CommonResult(4444, "按客户自定义,global handleException----1");
    }

    public static CommonResult handleException2(BlockException exception){
        return new CommonResult(4444, "按客户自定义,global handleException----2");
    }
}
