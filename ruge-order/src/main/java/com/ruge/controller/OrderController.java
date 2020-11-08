package com.ruge.controller;

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

    @PostMapping(value = "/file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Map<String, Object> file(@RequestParam("id") String id, @RequestParam("file") MultipartFile file) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("name", file.getName());
        map.put("originalFilename", file.getOriginalFilename());
        return map;
    }
}
