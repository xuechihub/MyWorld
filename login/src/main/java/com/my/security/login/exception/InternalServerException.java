package com.my.security.login.exception;

import com.my.security.login.enums.ResultCode;

/**
 * @program: securitymanagement
 * @description: 内部服务异常
 * @author: xuechi
 * @create: 2019-07-18 10:08
 **/
public class InternalServerException extends BusinessException {

    private static final long serialVersionUID = -8979090441490751848L;

    public InternalServerException() {
        super();
    }

    public InternalServerException(ResultCode resultCode) {
        super(resultCode);
    }

    public InternalServerException(ResultCode resultCode, Object data) {
        super(resultCode, data);
    }

    public InternalServerException(String msg) {
        super(msg);
    }

    public InternalServerException(String formatMsg, Object... objects) {
        super(formatMsg, objects);
    }
}
