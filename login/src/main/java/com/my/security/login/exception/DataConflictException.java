package com.my.security.login.exception;

import com.my.security.login.enums.ResultCode;

/**
 * @program: securitymanagement
 * @description: 数据已经存在异常
 * @author: xuechi
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
