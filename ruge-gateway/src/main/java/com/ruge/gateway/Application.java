package com.ruge.gateway;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 嘿丷如歌
 * @version V1.0
 * @Description: 网关启动类
 * @date 2020/11/5 22:04
 */
@Slf4j
@SpringBootApplication
public class Application {

    public static final String SIGN = "ruge.wu";

    public static void main(String[] args) {
//        testJavaEE();
        SpringApplication.run(Application.class, args);
    }

    public static void testJavaEE() {
        Map<String, String> map = new HashMap<>();
        map.put("aid", "1");
        map.put("mobile", "13222223333");
        String token = testGenJavaEE(map);
        System.out.println(token);
        testValidJavaEE(token);

    }

    /**
     * @param map claim
     * @return 生成token
     */
    public static String testGenJavaEE(Map<String, String> map) {

        Map<String, Object> header = new HashMap<>();
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE, 7);
        JWTCreator.Builder builder = JWT.create().withHeader(header);
        map.forEach((k, v) -> {
            builder.withClaim(k, v);
        });
        String sign = builder.withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256(SIGN));

        return sign;
    }

    /**
     * @param token token合法性校验
     */
    public static DecodedJWT testValidJavaEE(String token) {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("ruge.wu")).build();
        DecodedJWT jwt = jwtVerifier.verify(token);
        log.info("过期时间:" + jwt.getExpiresAt().toString());
        jwt.getClaims().forEach((k, v) -> {
            log.info("token key {} , value {}", k, v.asString());
        });
        return jwt;
    }
}
