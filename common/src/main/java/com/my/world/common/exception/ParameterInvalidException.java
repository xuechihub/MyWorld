package com.my.world.common.exception;


import com.my.world.common.enums.ResultCode;

/**
 * @program: securitymanagement
 * @description: 参数无效异常
 * @author: xuechi
 * @create: 2019-07-18 10:08
 **/
public class ParameterInvalidException extends BusinessException {

    private static final long serialVersionUID = 215075431588273824L;

    public ParameterInvalidException() {
        super();
    }

    public ParameterInvalidException(Object data) {
        super();
        super.data = data;
    }

    public ParameterInvalidException(ResultCode resultCode) {
        super(resultCode);
    }

    public ParameterInvalidException(ResultCode resultCode, Object data) {
        super(resultCode, data);
    }

    public ParameterInvalidException(String msg) {
        super(msg);
    }

    public ParameterInvalidException(String formatMsg, Object... objects) {
        super(formatMsg, objects);
    }
}
