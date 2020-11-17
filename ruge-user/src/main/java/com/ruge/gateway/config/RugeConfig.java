package com.ruge.gateway.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author 嘿丷如歌
 * @version V1.0
 * @Description: 配置类
 * @date 2020/11/7 10:30
 */
@SpringBootConfiguration
public class RugeConfig {
    /**
     * @return 注入 restTemplate
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean(name = "lbRestTemplate")
    @LoadBalanced
    public RestTemplate lbRestTemplate() {
        return new RestTemplate();
    }
}
