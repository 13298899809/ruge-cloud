package com.ruge.gateway.handler;

import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author 嘿丷如歌
 * @version V1.0
 * @Description: token过期
 * @date 2020/11/15 21:12
 */
public class TokenExpiredException extends ResponseStatusException {

    public TokenExpiredException(@Nullable String reason) {
        super(HttpStatus.UNAUTHORIZED, reason);
    }
}