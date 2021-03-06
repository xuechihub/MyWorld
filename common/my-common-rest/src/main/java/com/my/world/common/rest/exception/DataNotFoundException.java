package com.my.world.common.rest.exception;

import com.my.world.common.rest.enums.ResultCode;

/**
 * @program: MyWorld
 * @description: 数据没有找到异常
 * @author: xue chi
 * @create: 2019-07-18 10:08
 **/
public class DataNotFoundException extends BusinessException {

    private static final long serialVersionUID = -6575131733776531239L;

    public DataNotFoundException() {
        super();
    }

    public DataNotFoundException(Object data) {
        super();
        super.data = data;
    }

    public DataNotFoundException(ResultCode resultCode) {
        super(resultCode);
    }

    public DataNotFoundException(ResultCode resultCode, Object data) {
        super(resultCode, data);
    }

    public DataNotFoundException(String msg) {
        super(msg);
    }

    public DataNotFoundException(String formatMsg, Object... objects) {
        super(formatMsg, objects);
    }
}
