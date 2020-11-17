package com.ruge.gateway.config;

import com.ruge.gateway.filter.TokenGlobalFilter;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author 嘿丷如歌
 * @version V1.0
 * @Description:
 * @date 2020/11/18 0:21
 */
@Component
@SpringBootConfiguration
public class BeanConfig {

    @Bean
    public GlobalFilter customFilter() {
        return new TokenGlobalFilter();
    }
}
