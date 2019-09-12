package com.my.world.common.rest.advice;

import com.my.world.common.rest.annotations.DefaultResultResponseBody;
import com.my.world.common.rest.utils.JsonUtil;
import com.my.world.common.rest.vo.DefaultErrorResult;
import com.my.world.common.rest.vo.PlatformResult;
import com.my.world.common.rest.vo.Result;
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
public class ResultResponseAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return returnType.hasMethodAnnotation(DefaultResultResponseBody.class) || returnType.getMethod().getDeclaringClass().isAnnotationPresent(DefaultResultResponseBody.class);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        Class<? extends Result> requestBodyClazz;
        if (returnType.hasMethodAnnotation(DefaultResultResponseBody.class)) {
            requestBodyClazz = returnType.getMethodAnnotation(DefaultResultResponseBody.class).value();
        } else {
            requestBodyClazz = returnType.getMethod().getDeclaringClass().getAnnotation(DefaultResultResponseBody.class).value();
        }

        if (requestBodyClazz.isAssignableFrom(PlatformResult.class)) {
            if (body instanceof DefaultErrorResult) {
                DefaultErrorResult defaultErrorResult = (DefaultErrorResult) body;
                return PlatformResult.builder()
                        .code(defaultErrorResult.getCode())
                        .msg(defaultErrorResult.getMessage())
                        .data(defaultErrorResult.getErrors())
                        .build();
            }else if (body instanceof String) {
                return JsonUtil.object2Json(PlatformResult.success(body));
            }
            return PlatformResult.success(body);
        }

        return body;
    }

}
