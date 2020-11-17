package com.ruge.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 嘿丷如歌
 * @version V1.0
 * @Description:
 * @date 2020/11/5 23:40
 */
@RestController
public class LoginController {
    @GetMapping("login")
    public Map<String, String> login() {
        Map<String, String> map = new HashMap<>();
        return map;
    }
}
