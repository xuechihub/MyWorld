package com.my.world.common.autoconfigure;

import com.my.world.common.rest.utils.RequestContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertyResolver;
import org.springframework.core.io.Resource;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.StandardServletEnvironment;

@Configuration
@ConditionalOnWebApplication
@EnableConfigurationProperties(MyCommonProperties.class)
public class OtherPropertiesAutoConfiguration {

    private static Environment environment;

    @Autowired
    private PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer;

    @Bean
    public RequestContextUtil requestContextUtil() {
        return new RequestContextUtil();
    }

//    @Bean
//    @ConditionalOnMissingBean(PropertySourcesPlaceholderConfigurer.class)
//    @ConditionalOnProperty(prefix = MyCommonProperties.MY_COMMON_PREFIX, name = "enable-other-properties", matchIfMissing = false)
//    public PropertySourcesPlaceholderConfigurer properties() {
//        PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
//        WebApplicationContext currentWebApplicationContext = ContextLoader.getCurrentWebApplicationContext();
//        ApplicationContext applicationContext = RequestContextUtil.getApplicationContext();
//        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
//        Resource[] resources = myCommonProperties.resolveOtherApplicationPropertiesLocations();
//        Resource[] yamlResources = myCommonProperties.resolveOtherApplicationYmlPropertiesLocations();
//        yaml.setResources(yamlResources);
//        pspc.setLocations(resources);
//        pspc.setProperties(yaml.getObject());
//        return pspc;
//    }

    @Configuration
    static class MyWebAppConfigurer implements EnvironmentAware {

        @Override
        public void setEnvironment(Environment environment) {
            MutablePropertySources propertySources = ((StandardServletEnvironment) environment).getPropertySources();
//            propertySources.addLast();
            OtherPropertiesAutoConfiguration.environment = environment;
        }
    }
}
