package com.my.world.common.rest.exception;

import com.my.world.common.rest.enums.ResultCode;

/**
 * @program: MyWorld
 * @description: 权限不足异常
 * @author: xue chi
 * @create: 2019-07-18 10:08
 **/
public class PermissionForbiddenException extends BusinessException {

    private static final long serialVersionUID = -7779930727656784291L;

    public PermissionForbiddenException() {
        super();
    }

    public PermissionForbiddenException(Object data) {
        super.data = data;
    }

    public PermissionForbiddenException(ResultCode resultCode) {
        super(resultCode);
    }

    public PermissionForbiddenException(ResultCode resultCode, Object data) {
        super(resultCode, data);
    }

    public PermissionForbiddenException(String msg) {
        super(msg);
    }

    public PermissionForbiddenException(String formatMsg, Object... objects) {
        super(formatMsg, objects);
    }
}
