package com.ruge.domain;

import lombok.Data;

/**
 * @author 嘿丷如歌
 * @version V1.0
 * @Description: 用户实体类
 * @date 2020/11/17 21:04
 */
@Data
public class UserDomain {

    private String id;
    private String mobile;
    private String username;
    private String password;
    private String nickname;
}
