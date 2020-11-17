package com.ruge.gateway.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author 嘿丷如歌
 * @version V1.0
 * @Description: 用户控制器层
 * @date 2020/11/7 9:27
 */
@RestController
@RequestMapping(value = "ruge-order")
public class OrderController {

    @GetMapping(value = "/id")
    public Map<String, Object> findById(long id) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("name", "张三" + id);
        map.put("order", "张三" + UUID.randomUUID());
        return map;
    }

    @GetMapping(value = "/overtime/id")
    public Map<String, Object> findOvertimeById(long id) {
        Map<String, Object> map = new HashMap<>();
        try {
            Thread.sleep(id);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        map.put("id", id);
        map.put("name", "overtime 张三" + id);
        map.put("order", "overtime 张三" + UUID.randomUUID());
        return map;
    }


    @PostMapping(value = "/file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Map<String, Object> file(@RequestParam("id") String id, @RequestParam("file") MultipartFile file) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("name", file.getName());
        map.put("originalFilename", file.getOriginalFilename());
        return map;
    }

    /**
     * @return 服务熔断
     */
    @GetMapping(value = "/hystrix/id")
    @HystrixCommand(fallbackMethod = "findHystrixByIdFallback")
    public Map<String, Object> findHystrixById(Long id) {
        if (id < 0) {
            throw new RuntimeException("参数异常");
        }
        try {
            Thread.sleep(id);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("name", " 张三" + id);
        map.put("order", " 张三" + UUID.randomUUID());
        return map;
    }

    public Map<String, Object> findHystrixByIdFallback(Long id) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("name", "服务熔断 " + id);
        return map;
    }
}
