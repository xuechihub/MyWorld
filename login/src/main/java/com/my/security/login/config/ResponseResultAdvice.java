package com.my.security.login.config;

import com.my.security.login.annotations.CommonRequestBody;
import com.my.security.login.vo.DefaultErrorResult;
import com.my.security.login.vo.PlatformResult;
import com.my.security.login.vo.Result;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author zhumaer
 * @desc 接口响应体处理器
 * @since 4/1/2018 3:00 PM
 */
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
//			else if (body instanceof String) {
//				return JsonUtil.object2Json(PlatformResult.success(body));
//			}

            return PlatformResult.success(body);
        }

        return body;
    }

}
