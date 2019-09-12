package com.my.world.securitymanagement.api.user.test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @program: MyWorld
 * @description: springboot测试专用启动类
 * @author: xue chi
 * @create: 2019-08-16 08:46
 **/
@SpringBootApplication
@ComponentScan(basePackages = {"com.my.world.securitymanagement.api.user"})
@MapperScan("com.my.world.*.*.mapper")
public class TestApplication {
    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class);
    }
}
