package com.my.world.common.rest.exception.handler;

import com.my.world.common.rest.enums.ResultCode;
import com.my.world.common.rest.exception.BusinessException;
import com.my.world.common.rest.vo.DefaultErrorResult;
import com.my.world.common.rest.vo.ParameterInvalidItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.List;

/**
 * @program: MyWorld
 * @description: 全局异常基础类
 * @author: xue chi
 * @create: 2019-07-18 15:51
 **/
@Slf4j
public class BaseGlobalExceptionHandler {

    /**
     * 违反约束异常
     *
     * @param e
     * @param request
     * @return
     */
    protected DefaultErrorResult handleConstraintViolationException(ConstraintViolationException e, HttpServletRequest request) {
        log.warn("handleConstraintViolationException start,uri:{},caused by:", request.getRequestURI(), e);
        List<ParameterInvalidItem> parameterInvaildItemList = ParameterInvalidItemHelper.convertCVSetToParameterInvalidItemList(e.getConstraintViolations());
        return DefaultErrorResult.failure(request.getRequestURI(), ResultCode.PARAM_IS_INVALID, e, HttpStatus.BAD_REQUEST, parameterInvaildItemList);
    }

    /**
     * 处理验证参数封装错误时异常
     */
    protected DefaultErrorResult handleConstraintViolationException(HttpMessageNotReadableException e, HttpServletRequest request) {
        log.warn("handleConstraintViolationException start, uri:{}, caused by: ", request.getRequestURI(), e);
        return DefaultErrorResult.failure(request.getRequestURI(), ResultCode.PARAM_IS_INVALID, e, HttpStatus.BAD_REQUEST);
    }

    /**
     * 处理参数绑定时异常（反400错误码）
     */
    protected DefaultErrorResult handleBindException(BindException e, HttpServletRequest request) {
        log.warn("handleBindException start, uri:{}, caused by: ", request.getRequestURI(), e);
        List<ParameterInvalidItem> parameterInvalidItemList = ParameterInvalidItemHelper.convertBindingResultToMapParameterInvalidItemList(e.getBindingResult());
        return DefaultErrorResult.failure(request.getRequestURI(), ResultCode.PARAM_IS_INVALID, e, HttpStatus.BAD_REQUEST, parameterInvalidItemList);
    }

    /**
     * 处理使用@Validated注解时，参数验证错误异常（反400错误码）
     */
    protected DefaultErrorResult handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        log.warn("handleMethodArgumentNotValidException start, uri:{}, caused by: ", request.getRequestURI(), e);
        List<ParameterInvalidItem> parameterInvalidItemList = ParameterInvalidItemHelper.convertBindingResultToMapParameterInvalidItemList(e.getBindingResult());
        return DefaultErrorResult.failure(request.getRequestURI(), ResultCode.PARAM_IS_INVALID, e, HttpStatus.BAD_REQUEST, parameterInvalidItemList);
    }

    /**
     * 处理通用自定义业务异常
     */
    protected ResponseEntity<DefaultErrorResult> handleBusinessException(BusinessException e, HttpServletRequest request) {
        log.warn("handleBusinessException start, uri:{}, exception:{}, caused by: {}", request.getRequestURI(), e.getClass(), e.getMessage());
        DefaultErrorResult defaultErrorResult = DefaultErrorResult.failure(request.getRequestURI(), e);
        return ResponseEntity
                .status(HttpStatus.valueOf(defaultErrorResult.getStatus()))
                .body(defaultErrorResult);
    }

    /**
     * 处理未预测到的其他错误（反500错误码）
     */
    protected DefaultErrorResult handleThrowable(Throwable e, HttpServletRequest request) {
        log.error("handleThrowable start, uri:{}, caused by: ", request.getRequestURI(), e);
        return DefaultErrorResult.failure(request.getRequestURI(), ResultCode.SYSTEM_INNER_ERROR, e, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
