package com.ruge.config.git.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 嘿丷如歌
 * @version V1.0
 * @Description:
 * @date 2020/11/22 1:27
 * @RefreshScope 配合actuator实现自动刷新配置中心
 */
@RefreshScope
@RestController
public class ConfigGitController {
    /**
     * #POST 请求 即可刷新配置
     * http://localhost:7881/actuator/refresh
     */
    @Value("${name}")
    private String name;

    @GetMapping("init")
    public String name() {
        return name;
    }

}
