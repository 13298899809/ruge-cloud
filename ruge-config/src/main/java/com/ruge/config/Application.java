package com.ruge.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author 嘿丷如歌
 * @version V1.0
 * @Description: 配置中心 启动类
 * @date 2020/11/21 19:57
 *
 * /{application}/{profile}[/{label}]
 * /{application}-{profile}.yml
 * /{label}/{application}-{profile}.yml
 * /{application}-{profile}.properties
 * /{label}/{application}-{profile}.properties
 *
 *
 * http://localhost:7878/ruge-user.properties
 * http://localhost:7878/ruge-user.json
 * http://localhost:7878/ruge-user.yml
 *
 * http://localhost:7878/ruge-user-master.yml
 *
 * http://localhost:7878/ruge-user/master
 */
@EnableConfigServer  // 开启配置服务
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
