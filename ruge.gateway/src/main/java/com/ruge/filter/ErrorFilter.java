package com.ruge.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.support.NotFoundException;

import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;



/**
 * @author 嘿丷如歌
 * @version V1.0
 * @Description: 统一异常过滤器
 * @date 2020/11/5 22:29
 */
@Slf4j
//@Order(-1)
@Configuration
public class ErrorFilter implements ErrorWebExceptionHandler, Ordered {

    @Override
    public Mono<Void> handle(ServerWebExchange serverWebExchange, Throwable throwable) {
        ServerHttpResponse response = serverWebExchange.getResponse();

        if (serverWebExchange.getResponse().isCommitted()) {
            return Mono.error(throwable);
        }
        String msg;

        if (throwable instanceof NotFoundException) {
            msg = "服务未找到";
        } else if (throwable instanceof ResponseStatusException) {
            ResponseStatusException responseStatusException = (ResponseStatusException) throwable;
            msg = responseStatusException.getMessage();
        } else {
            msg = "内部服务器错误";
        }
        log.error("[网关异常处理]请求路径:{},异常信息:{}", serverWebExchange.getRequest().getPath(), throwable.getMessage());
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        response.setStatusCode(HttpStatus.OK);
        return response.writeWith(Mono.fromSupplier(() -> {
            DataBufferFactory bufferFactory = response.bufferFactory();
            return bufferFactory.wrap(msg.getBytes());
        }));
    }

    @Override
    public int getOrder() {
        System.out.println("ErrorFilter   -1");
        return -1;
    }
}
