package com.my.world.common.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

@ConfigurationProperties(prefix = MyCommonProperties.MY_COMMON_PREFIX)
public class MyCommonProperties {

    public static final String MY_COMMON_PREFIX = "my.world.common";

    private static final ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();

    /**
     * 是否开启统一异常处理
     */
    private boolean enableGlobalException;

    /**
     * 配置hibernate Validator为快速失败返回模式
     */
    private boolean isHibernateValidatorFailFast;

    /**
     * 是否加载指定属性文件到spring
     */
    private boolean enableOtherProperties;

    /**
     * 要加载的属性文件路径,支持yml和properties类型文件
     */
    private String[] otherApplicationPropertiesLocations;

    public Resource[] resolveOtherApplicationPropertiesLocations() {
        return Stream.of(Optional.ofNullable(this.otherApplicationPropertiesLocations).orElse(new String[0]))
                .flatMap(location -> Stream.of(getResources(location)))
                .filter(resource -> {
                    String filename = resource.getFilename();
                    return filename != null && filename.endsWith(".properties");
                })
                .toArray(Resource[]::new);
    }

    public Resource[] resolveOtherApplicationYmlPropertiesLocations() {
        return Stream.of(Optional.ofNullable(this.otherApplicationPropertiesLocations).orElse(new String[0]))
                .flatMap(location -> Stream.of(getResources(location)))
                .filter(resource -> {
                    String filename = resource.getFilename();
                    return filename != null && (filename.endsWith(".yml") || filename.endsWith(".yaml"));
                })
                .toArray(Resource[]::new);
    }

    private Resource[] getResources(String location) {
        try {
            return resourceResolver.getResources(location);
        } catch (IOException e) {
            return new Resource[0];
        }
    }

    public boolean isEnableGlobalException() {
        return enableGlobalException;
    }

    public void setEnableGlobalException(boolean enableGlobalException) {
        this.enableGlobalException = enableGlobalException;
    }

    public boolean isHibernateValidatorFailFast() {
        return isHibernateValidatorFailFast;
    }

    public void setHibernateValidatorFailFast(boolean hibernateValidatorFailFast) {
        isHibernateValidatorFailFast = hibernateValidatorFailFast;
    }

    public boolean isEnableOtherProperties() {
        return enableOtherProperties;
    }

    public void setEnableOtherProperties(boolean enableOtherProperties) {
        this.enableOtherProperties = enableOtherProperties;
    }

    public String[] getOtherApplicationPropertiesLocations() {
        return otherApplicationPropertiesLocations;
    }

    public void setOtherApplicationPropertiesLocations(String[] otherApplicationPropertiesLocations) {
        this.otherApplicationPropertiesLocations = otherApplicationPropertiesLocations;
    }
}
