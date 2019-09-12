package com.my.world.common.rest.exception;

import com.my.world.common.rest.enums.ResultCode;

/**
 * @program: MyWorld
 * @description: 远端访问异常
 * @author: xue chi
 * @create: 2019-07-18 10:08
 **/
public class RemoteAccessException extends BusinessException {

    private static final long serialVersionUID = 3815148064787630269L;

    public RemoteAccessException() {
        super();
    }

    public RemoteAccessException(Object data) {
        super.data = data;
    }

    public RemoteAccessException(ResultCode resultCode) {
        super(resultCode);
    }

    public RemoteAccessException(ResultCode resultCode, Object data) {
        super(resultCode, data);
    }

    public RemoteAccessException(String msg) {
        super(msg);
    }

    public RemoteAccessException(String formatMsg, Object... objects) {
        super(formatMsg, objects);
    }
}
