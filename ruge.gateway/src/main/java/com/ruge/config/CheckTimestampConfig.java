package com.ruge.config;

import lombok.Data;

/**
 * @author 嘿丷如歌
 * @version V1.0
 * @Description: 防止接口重复校验
 * @date 2020/11/5 22:14
 */
@Data
public class CheckTimestampConfig {
    /*时间戳和nonce校验开关, 若校验时间戳则nonce有效性强制校验*/
    private Boolean enable;
    /*时间戳校验阈值, 单位毫秒*/
    private Long threshold;
}
