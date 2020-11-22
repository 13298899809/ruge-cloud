package com.ruge.gateway.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Mono;

/**
 * @author 嘿丷如歌
 * @version V1.0
 * @Description: 限流
 * @date 2020/11/18 9:21
 */
@SpringBootConfiguration
public class RateLimiteConfig {
    /**
     * 功能描述: 基于ip限流
     *
     * @param: []
     * @return: org.springframework.cloud.gateway.filter.ratelimit.KeyResolver
     * @qq: 8416837
     * @author: cc
     * @date: 2020/6/10 11:56
     */
//    @Bean("ipKeyResolver")
//    public KeyResolver ipKeyResolver() {
//        System.out.println("ipKeyResolver 限流解析器");
//        return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getHostName());
//    }
    @Bean("pathKeyResolver")
    public KeyResolver pathKeyResolver() {
        System.out.println("pathKeyResolver 限流解析器");
        return exchange -> Mono.just(exchange.getRequest().getURI().getPath());
    }

//    @Bean
//    RedisRateLimiter redisRateLimiter() {
//        return new RedisRateLimiter(1, 2); // yaml配置文件已经有了，那么这里就省了。9,16对应replenishRate、burstCapacity
//    }
}
