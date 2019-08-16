package com.my.world.common.config;

import com.my.world.common.annotations.CommonRequestBody;
import com.my.world.common.utils.JsonUtil;
import com.my.world.common.vo.DefaultErrorResult;
import com.my.world.common.vo.PlatformResult;
import com.my.world.common.vo.Result;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @program: MyWorld
 * @description: 接口响应体处理器
 * @author: xue chi
 * @create: 2019-08-15 19:17
 **/
@ControllerAdvice
public class ResponseResultAdvice implements ResponseBodyAdvice<Object> {


    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return returnType.hasMethodAnnotation(CommonRequestBody.class) || returnType.getMethod().getDeclaringClass().isAnnotationPresent(CommonRequestBody.class);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        Class<? extends Result> requestBodyClazz;
        if(returnType.hasMethodAnnotation(CommonRequestBody.class)){
            requestBodyClazz = returnType.getMethodAnnotation(CommonRequestBody.class).value();
        }else{
            requestBodyClazz = returnType.getMethod().getDeclaringClass().getAnnotation(CommonRequestBody.class).value();
        }

        if (requestBodyClazz.isAssignableFrom(PlatformResult.class)) {
            if (body instanceof DefaultErrorResult) {
                DefaultErrorResult defaultErrorResult = (DefaultErrorResult) body;
                return PlatformResult.builder()
                        .code(defaultErrorResult.getCode())
                        .msg(defaultErrorResult.getMessage())
                        .data(defaultErrorResult.getErrors())
                        .build();
            }
            return PlatformResult.success(body);
        }

        return body;
    }

}
