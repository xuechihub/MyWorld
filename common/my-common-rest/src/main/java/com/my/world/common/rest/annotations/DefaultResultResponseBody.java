package com.my.world.common.rest.annotations;

import com.my.world.common.rest.vo.PlatformResult;
import com.my.world.common.rest.vo.Result;

import java.lang.annotation.*;

/**
 * @program: MyWorld
 * @description: 接口返回结果增强  会通过拦截器拦截后放入标记，在 ResponseResultHandler 进行结果处理
 * @author: xue chi
 * @create: 2019-07-18 16:33
 **/
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DefaultResultResponseBody {
    Class<? extends Result> value() default PlatformResult.class;
}
