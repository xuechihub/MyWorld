package com.my.world.securitymanegement.website;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @program: MyWorld
 * @description: 安全管理
 * @author: xuechi
 * @create: 2019-08-08 16:28
 **/
@SpringBootApplication
@ComponentScan(basePackages = {"com.my.world"})
@MapperScan("com.my.world.securitymanagement.*.mapper")
public class SecurityManagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(SecurityManagementApplication.class, args);
    }
}
