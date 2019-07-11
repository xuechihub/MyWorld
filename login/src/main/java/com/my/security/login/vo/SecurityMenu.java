package com.my.security.login.vo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @program: securitymanagement
 * @description: 安全导航栏菜单
 * @author: xuechi
 * @create: 2019-06-20 11:23
 **/
@Component
@ConfigurationProperties(prefix = "security")
@PropertySource(value = "classpath:config/HeaderMenu.yml")
public class SecurityMenu {

    private String id;
    private String name;
    private String type;
    private Map<String, Map<String, String>>[] item;

    public Map<String, Map<String, String>>[] getItem() {
        return item;
    }

    public void setItem(Map<String, Map<String, String>>[] item) {
        this.item = item;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "SecurityMenu{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
