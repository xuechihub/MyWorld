package com.my.world.common.rest.exception;

import com.my.world.common.rest.enums.ExceptionEnum;
import com.my.world.common.rest.enums.ResultCode;
import lombok.Data;
import org.springframework.util.StringUtils;

/**
 * @program: MyWorld
 * @description: 业务异常类
 * @author: xue chi
 * @create: 2019-07-18 09:08
 **/
@Data
public abstract class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 1551528984194407211L;

    protected String code;

    protected String message;

    protected ResultCode resultCode;

    protected Object data;

    public BusinessException() {
        ExceptionEnum exceptionEnum = ExceptionEnum.getByEClass(this.getClass());
        if (exceptionEnum != null) {
            resultCode = exceptionEnum.getResultCode();
            code = exceptionEnum.getResultCode().code().toString();
            message = exceptionEnum.getResultCode().message();
        }
    }

    public BusinessException(String message) {
        this();
        this.message = message;
    }

    public BusinessException(String format, Object... objects) {
        this();
        format = StringUtils.replace(format, "{}", "%s");
        this.message = String.format(format, objects);
    }

    public BusinessException(ResultCode resultCode, Object data) {
        this(resultCode);
        this.data = data;
    }

    public BusinessException(ResultCode resultCode) {
        this.resultCode = resultCode;
        this.code = resultCode.code().toString();
        this.message = resultCode.message();
    }


}
