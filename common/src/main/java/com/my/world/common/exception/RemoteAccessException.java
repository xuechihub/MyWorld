package com.my.world.common.exception;

import com.my.world.common.enums.ResultCode;

/**
 * @program: securitymanagement
 * @description: 远端访问异常
 * @author: xuechi
 * @create: 2019-07-18 10:08
 **/
public class RemoteAccessException extends BusinessException {

    private static final long serialVersionUID = 3815148064787630269L;

    public RemoteAccessException() {
        super();
    }

    public RemoteAccessException(Object data){
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
