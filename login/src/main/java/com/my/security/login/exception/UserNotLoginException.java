package com.my.security.login.exception;

import com.my.security.login.enums.ResultCode;

/**
 * @program: securitymanagement
 * @description: 用户未登录异常
 * @author: xuechi
 * @create: 2019-07-18 10:08
 **/
public class UserNotLoginException extends BusinessException {

    private static final long serialVersionUID = 3815148064787630269L;

    public UserNotLoginException() {
        super();
    }

    public UserNotLoginException(String msg) {
        super(msg);
    }

    public UserNotLoginException(String formatMsg, Object... objects) {
        super(formatMsg, objects);
    }
}
