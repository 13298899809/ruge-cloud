package com.ruge.dao;

import com.ruge.dao.impl.OrderFeignImpl;
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
@FeignClient(value = "ruge-order", fallback = OrderFeignImpl.class)
public interface OrderFeign {

    @GetMapping(value = "ruge-order/id")
    Map<String, Object> findById(@RequestParam("id") Long id);

    @GetMapping(value = "ruge-order/overtime/id")
    Map<String, Object> findOvertimeById(@RequestParam("id") Long id);

    @PostMapping(value = "ruge-order/file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    Map<String, Object> file(@RequestParam("id") String id, @RequestPart("file") MultipartFile file);

    @GetMapping(value = "ruge-order/hystrix/id")
    Map<String, Object> getOrderByFeignHystrix(@RequestParam("id") long id);
}
