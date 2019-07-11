package com.my.security.login.config;

import com.my.security.login.vo.Test;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.SearchStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

/**
 * @program: securitymanagement
 * @description: Yml解析配置类
 * @author: xuechi
 * @create: 2019-06-20 19:14
 **/
@Configuration
public class YmlConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer properties() {
        PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
        yaml.setResources(new ClassPathResource[]{
                new ClassPathResource("config/HeaderMenu.yml"),
        });
        pspc.setProperties(yaml.getObject());
        return pspc;
    }

    @Bean
    @ConditionalOnMissingBean(search = SearchStrategy.CURRENT)
    public static Test test(){
        Test test = new Test();
        test.setTest("Test");
        return test;
    }


}
