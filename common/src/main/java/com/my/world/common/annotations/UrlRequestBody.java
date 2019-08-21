package com.my.world.common.annotations;

import java.lang.annotation.*;

/**
 * @program: MyWorld
 * @description: get请求的requestBody注解
 * @author: xue chi
 * @create: 2019-08-21 14:24
 **/
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UrlRequestBody {
}
