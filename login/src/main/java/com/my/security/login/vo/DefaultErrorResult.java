package com.my.security.login.vo;

import com.my.security.login.enums.ExceptionEnum;
import com.my.security.login.enums.ResultCode;
import com.my.security.login.exception.BusinessException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: securitymanagement
 * @description: 默认全局错误返回格式
 * @author: xuechi
 * @create: 2019-07-17 16:48
 **/
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DefaultErrorResult implements Result{

    private static final long serialVersionUID = -5170776430064629484L;

    /**
     * HTTP状态码
     */
    private Integer status;

    /**
     * Http响应状态码英文提示
     */
    private String error;

    /**
     * 异常堆栈简要信息
     */
    private String message;

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 调用接口路径
     */
    private String path;

    /**
     * 异常名字
     */
    private String exception;

    /**
     * 异常信息传递数据
     */
    private Object errors;

    /**
     * 时间戳
     */
    private Date timestamp;

    public static DefaultErrorResult failure(ResultCode resultCode, Throwable throwable, HttpStatus httpStatus, Object errors) {
        DefaultErrorResult result = DefaultErrorResult.failure(resultCode, throwable, httpStatus);
        result.setErrors(errors);
        return result;
    }

    public static DefaultErrorResult failure(ResultCode resultCode, Throwable throwable, HttpStatus httpStatus) {
        DefaultErrorResult result = new DefaultErrorResult();
        result.setCode(resultCode.code());
        result.setMessage(resultCode.message());
        result.setStatus(httpStatus.value());
        result.setError(httpStatus.getReasonPhrase());
        result.setException(resultCode.getClass().getName());
        result.setTimestamp(new Date());
        return result;
    }

    public static DefaultErrorResult failure(BusinessException e) {
        ExceptionEnum ee = ExceptionEnum.getByEClass(e.getClass());
        if (ee != null) {
            return DefaultErrorResult.failure(ee.getResultCode(), e, ee.getHttpStatus(), e.getData());
        }

        DefaultErrorResult defaultErrorResult = DefaultErrorResult.failure(e.getResultCode() == null ? ResultCode.SUCCESS : e.getResultCode(), e, HttpStatus.OK, e.getData());
        if (e.getMessage()!=null&&!"".equals(e.getMessage().trim())) {
            defaultErrorResult.setMessage(e.getMessage());
        }
        return defaultErrorResult;
    }

}
