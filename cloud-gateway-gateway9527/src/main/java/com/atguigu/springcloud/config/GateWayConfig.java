package com.atguigu.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 编码方式
 * 实现gateway网关进行路由映射配置
 */
@Configuration
public class GateWayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder) {
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        // https://news.baidu.com/guonei
        routes.route("path_route_atguigu",
                r -> r.path("/guonei").uri("https://news.baidu.com/guonei")).build();
        return routes.build();
    }

}
