package com.my.world.common.rest.exception;

import com.my.world.common.rest.enums.ResultCode;

/**
 * @program: MyWorld
 * @description: 方法不允许异常
 * @author: xue chi
 * @create: 2019-07-18 10:08
 **/
public class MethodNotAllowException extends BusinessException {

    private static final long serialVersionUID = 8320369450551131586L;

    public MethodNotAllowException() {
        super();
    }

    public MethodNotAllowException(Object data) {
        super.data = data;
    }

    public MethodNotAllowException(ResultCode resultCode) {
        super(resultCode);
    }

    public MethodNotAllowException(ResultCode resultCode, Object data) {
        super(resultCode, data);
    }

    public MethodNotAllowException(String msg) {
        super(msg);
    }

    public MethodNotAllowException(String formatMsg, Object... objects) {
        super(formatMsg, objects);
    }
}
