package com.ruge.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author 嘿丷如歌
 * @version V1.0
 * @Description: 有token就校验 没有token 就不校验
 * @date 2020/11/5 23:01
 */
@Slf4j
@Component
public class InPublicFilter implements GlobalFilter, Ordered {


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        log.info("==================");
        String url = exchange.getRequest().getPath().pathWithinApplication().value();
        log.info("请求URL:" + url);

        //获取header
        String appKey = exchange.getRequest().getHeaders().getFirst("token");
        log.info("appKey：" + appKey);
        //请求的body
        Flux<DataBuffer> flux = exchange.getRequest().getBody();


        //跳过检测
//        if (exchange.getAttribute(ATTRIBUTE_IGNORE_TEST_GLOBAL_FILTER) != null) {
//            return chain.filter(exchange);
//        } else {
//            //简单增加一个参数
//            URI uri = exchange.getRequest().getURI();
//            String query = uri.getRawQuery();
//            if (StringUtils.hasText(query)) {
//                query = query + "&throwFilter=true";
//            } else {
//                query = query + "?throwFilter=true";
//            }
//
//            URI newUri = UriComponentsBuilder.fromUri(uri)
//                    .replaceQuery(query)
//                    .build(true)
//                    .toUri();
//
//            ServerHttpRequest request = exchange.getRequest().mutate().uri(newUri).build();
//
//            return chain.filter(exchange.mutate().request(request).build());
//        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
//        System.out.println("InPublicFilter   2");
        return 8;
    }
}
