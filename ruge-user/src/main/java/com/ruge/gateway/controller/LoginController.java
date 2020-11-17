package com.ruge.gateway.controller;

import com.ruge.domain.UserDomain;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 嘿丷如歌
 * @version V1.0
 * @Description: 登陆
 * @date 2020/11/17 21:03
 */

@RestController
@RequestMapping(value = "user")
public class LoginController {

    @PostMapping(value = "/login")
    public Map<String, Object> findById(@RequestBody UserDomain userDomain) {
        Map<String, Object> header = new HashMap<>();
        Map<String, Object> claims = new HashMap<>();
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.SECOND, 20);

        String compact = Jwts.builder()
                .setHeader(header)
                .signWith(SignatureAlgorithm.RS256, "ruge.wu")
                .addClaims(claims)
                .setExpiration(instance.getTime()) /*过期时间*/
                .compact();
        Map<String, Object> map = new HashMap<>();
        map.put("token", compact);
        return map;
    }
}
