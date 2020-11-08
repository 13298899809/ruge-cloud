package com.ruge.controller;

import com.ruge.dao.OrderFeign;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author 嘿丷如歌
 * @version V1.0
 * @Description: 用户控制器层
 * @date 2020/11/7 9:27
 * <p>
 * 健康检查 http://localhost:8081/actuator/health
 */
@RestController
@RequestMapping(value = "ruge-user")
public class UserController {
    @Resource
    private RestTemplate restTemplate;
    @Resource(name = "lbRestTemplate")
    private RestTemplate lbRestTemplate;

    @Resource
    private DiscoveryClient discoveryClient;

    @Resource
    private LoadBalancerClient loadBalancerClient;

    @Resource
    private OrderFeign orderFeign;

    /**
     * @param id http://localhost:8081/ruge-user/2
     */
    @GetMapping(value = "/{id}")
    public Map<String, Object> findById(@PathVariable Long id) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("name", "张三" + id);
        return map;
    }

    /**
     * @param id http://localhost:8081/ruge-user/rest/2
     */
    @GetMapping(value = "/rest/{id}")
    public Map<String, Object> getOrderByRest(@PathVariable Long id) {
        ResponseEntity<Map> responseEntity = restTemplate.getForEntity("http://localhost:8082/ruge-order/" + id, Map.class);
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("name", "张三" + id);
        map.put("rest", responseEntity);
        return map;
    }

    /**
     * @return http://localhost:8081/ruge-user/discovery
     */
    @GetMapping(value = "/discovery")
    public Map<String, Object> getDiscovery() {
        Map<String, Object> map = new HashMap<>();
        List<ServiceInstance> serviceInstances = new ArrayList<>();
        discoveryClient.getServices().forEach(e -> {
            serviceInstances.addAll(discoveryClient.getInstances(e));
        });

        map.put("id", UUID.randomUUID());
        map.put("services", discoveryClient.getServices());
        map.put("serviceInstances", serviceInstances);
        map.put("name", "张三" + UUID.randomUUID());
        return map;
    }


    /**
     * @param id http://localhost:8081/ruge-user/lb/2
     */
    @GetMapping(value = "/lb/{id}")
    public Map<String, Object> getOrderByLB(@PathVariable Long id) {
        ServiceInstance choose = loadBalancerClient.choose("ruge-order");
        System.out.println(choose);
        ResponseEntity<Map> responseEntity = lbRestTemplate.getForEntity("http://ruge-order/ruge-order/" + id, Map.class);
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("name", "张三" + id);
        map.put("rest", responseEntity);
        return map;
    }

    /**
     * @return http://localhost:8081/ruge-user/feignBase/666
     */
    @GetMapping(value = "/feignBase/{id}")
    public Map<String, Object> getOrderByFeignBase(@PathVariable long id) {
        return orderFeign.findById(id);
    }

    /**
     * @return http://localhost:8081/ruge-user/feignFile
     */
    @PostMapping(value = "/feignFile")
    public Map<String, Object> getOrderByFeignFile(@RequestParam("id") String id, @RequestParam("file") MultipartFile file) {
        return orderFeign.file(id, file);
    }
}
