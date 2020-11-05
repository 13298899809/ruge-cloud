package com.ruge.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author 嘿丷如歌
 * @version V1.0
 * @Description: 网关配置项
 * @date 2020/11/5 22:11
 */
@Data
@Component
@ConfigurationProperties(prefix = "check")
public class ApiGatewayProperties {
    /**
     * 防重放配置
     */
    private CheckTimestampConfig checkTimestamp;
    /*签名服务器地址*/
    private String checkAppKeyService;
    /*签名校验白名单*/
    private List<String> checkAppKeyWhiteList;
    /*token校验服务地址*/
    private String checkUserTokenService;
    /*token校验白名单*/
    private List<String> checkUserTokenWhiteList;
    /*admin token 校验地址*/
    private Map<String, String> checkAdminTokenService;
    /*admin token 校验白名单*/
    private List<String> checkAdminTokenWhiteList;
    /*配置统一登录校验adminToken的appkey*/
    private List<String> checkAdminTokenFromUnified;
}
