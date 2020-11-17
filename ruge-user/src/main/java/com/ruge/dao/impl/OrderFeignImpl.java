package com.ruge.dao.impl;

import com.ruge.dao.OrderFeign;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 嘿丷如歌
 * @version V1.0
 * @Description:
 * @date 2020/11/14 16:17
 */
@Component
public class OrderFeignImpl implements OrderFeign {
    @Override
    public Map<String, Object> findById(Long id) {
        return null;
    }

    @Override
    public Map<String, Object> findOvertimeById(Long id) {
        return null;
    }

    @Override
    public Map<String, Object> file(String id, MultipartFile file) {
        return null;
    }

    @Override
    public Map<String, Object> getOrderByFeignHystrix(long id) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("name", "张三" + id);
        map.put("result", "服务降级");
        return map;
    }
}
