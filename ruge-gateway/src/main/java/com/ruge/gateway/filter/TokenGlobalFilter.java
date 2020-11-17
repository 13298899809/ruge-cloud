package com.ruge.gateway.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author 嘿丷如歌
 * @version V1.0
 * @Description: token 过滤器
 * @date 2020/11/15 21:17
 * <p>
 * JWT 鉴权机制
 * 注：每次请求头和响应头都会存储当前有效的token,白名单接口除外
 * 1、JWT-Filter对登录/注册不鉴权，成功后将用户的JWT生成的Token作为k、v存储到cache缓存里面(这时候k、v值一样)
 * 2、当该用户这次请求JWTToken值还在生命周期内，且该token对应cache中的k存在，则会通过重新PUT的方式k、v都为Token值，缓存中的token值生命周期时间重新计算(这时候k、v值一样)
 * 3、当该用户这次请求JWTToken值还在生命周期内，但该token对应cache中的k不存在，返回用户信息已失效，请重新登录。
 * 4、当该用户这次请求jwt生成的token值已经超时，但该token对应cache中的k还是存在，则表示该用户一直在操作只是JWT的token失效了，程序会给token对应的k映射的v值重新生成JWTToken并覆盖v值，该缓存生命周期重新计算
 * 5、当该用户这次请求jwt生成的token值已经超时，且该token对应cache中的k不存在，则表示该用户账户空闲超时，返回用户信息已失效，请重新登录。
 */
@Slf4j
public class TokenGlobalFilter implements GlobalFilter, Ordered {
    private static final String AUTHORIZE_TOKEN = "Authorization";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        HttpHeaders headers = request.getHeaders();
        String token = headers.getFirst(AUTHORIZE_TOKEN);
        JWTVerifier jwtVerifier = null;
        try {
            jwtVerifier = JWT.require(Algorithm.HMAC256("ruge.wu")).build();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        DecodedJWT jwt = null;
        try {
            jwt = jwtVerifier.verify(token.substring(7));
        } catch (JWTVerificationException e) {
            e.printStackTrace();
        }
        log.info("过期时间:" + jwt.getExpiresAt().toString());
        jwt.getClaims().forEach((k, v) -> {
            log.info("token key {} , value {}", k, v.asString());
        });
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -100;
    }
}