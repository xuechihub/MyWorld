package com.my.world.common.rest.exception;


import com.my.world.common.rest.enums.ResultCode;

/**
 * @program: MyWorld
 * @description: 数据已经存在异常
 * @author: xue chi
 * @create: 2019-07-18 10:08
 **/
public class DataConflictException extends BusinessException {

    private static final long serialVersionUID = -185428227671106354L;

    public DataConflictException() {
        super();
    }

    public DataConflictException(Object data) {
        super.data = data;
    }

    public DataConflictException(ResultCode resultCode) {
        super(resultCode);
    }

    public DataConflictException(ResultCode resultCode, Object data) {
        super(resultCode, data);
    }

    public DataConflictException(String msg) {
        super(msg);
    }

    public DataConflictException(String formatMsg, Object... objects) {
        super(formatMsg, objects);
    }
}
