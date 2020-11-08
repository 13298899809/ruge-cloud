package com.ruge.dao;

import com.ruge.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @author 嘿丷如歌
 * @version V1.0
 * @Description: feign
 * @date 2020/11/8 23:13
 */
@FeignClient(value = "ruge-order",configuration = FeignConfig.class)
public interface OrderFeign {


    @GetMapping(value = "ruge-order/id")
    Map<String, Object> findById(@RequestParam("id") Long id);

    @PostMapping(value = "ruge-order/file",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    Map<String, Object> file(@RequestParam("id") String id, @RequestPart("file") MultipartFile file);
}
