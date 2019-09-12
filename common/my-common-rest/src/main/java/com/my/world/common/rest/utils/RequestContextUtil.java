package com.my.world.common.rest.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @program: MyWorld
 * @description: 应用级对象获取工具类
 * @author: xue chi
 * @create: 2019-08-19 16:55
 **/
public class RequestContextUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    public static HttpServletRequest getRequest() {
        return getRequestAttributes().getRequest();
    }

    public static HttpServletResponse getResponse() {
        return getRequestAttributes().getResponse();
    }

    public static HttpSession getSession() {
        return getRequest().getSession();
    }

    public static ServletRequestAttributes getRequestAttributes() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());
    }

    public static ServletContext getServletContext() {
        return ContextLoader.getCurrentWebApplicationContext().getServletContext();
    }

    public static ApplicationContext getApplicationContext() {
        return RequestContextUtil.applicationContext;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        RequestContextUtil.applicationContext = applicationContext;
    }

    public static void setApplicationBootContext(ApplicationContext applicationContext) throws BeansException {
        RequestContextUtil.applicationContext = applicationContext;
    }

    public static <T> T getBean(String name) {
        return (T) RequestContextUtil.applicationContext.getBean(name);
    }

}
