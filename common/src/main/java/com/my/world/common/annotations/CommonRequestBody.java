package com.my.world.common.annotations;


import com.my.world.common.vo.PlatformResult;
import com.my.world.common.vo.Result;

import java.lang.annotation.*;

/**
 * @program: securitymanagement
 * @description: 接口返回结果增强  会通过拦截器拦截后放入标记，在 ResponseResultHandler 进行结果处理
 * @author: xuechi
 * @create: 2019-07-18 16:33
 **/
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CommonRequestBody {
    Class<? extends Result> value() default PlatformResult.class;
}
