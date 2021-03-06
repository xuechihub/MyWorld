package com.my.world.common.rest.exception;

import com.my.world.common.rest.enums.ResultCode;

/**
 * @program: MyWorld
 * @description: 内部服务异常
 * @author: xue chi
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
