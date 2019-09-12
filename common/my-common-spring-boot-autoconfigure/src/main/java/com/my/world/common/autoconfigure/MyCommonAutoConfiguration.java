package com.my.world.common.autoconfigure;

import com.my.world.common.rest.advice.GlobalExceptionAdvice;
import com.my.world.common.rest.advice.ResultResponseAdvice;
import com.my.world.common.rest.resolver.UrlRequestBodyMethodArgumentResolver;
import org.hibernate.validator.HibernateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;

@Configuration
@ConditionalOnWebApplication
@EnableConfigurationProperties(MyCommonProperties.class)
public class MyCommonAutoConfiguration {

    @Autowired
    private MyCommonProperties myCommonProperties;

    @Bean
    public Validator validator() {
        ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
                .configure()
                .failFast(myCommonProperties.isHibernateValidatorFailFast())
                .buildValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        return validator;
    }

    @Configuration
    @ConditionalOnMissingBean(annotation = {ControllerAdvice.class, RestControllerAdvice.class})
    static class RequestResponseAutoConfiguration {

        @Bean
        @ConditionalOnProperty(prefix = MyCommonProperties.MY_COMMON_PREFIX, name = "enable-global-exception", matchIfMissing = true)
        public GlobalExceptionAdvice globalExceptionAdvice() {
            return new GlobalExceptionAdvice();
        }

        @Bean
        public ResultResponseAdvice resultResponseAdvice() {
            return new ResultResponseAdvice();
        }
    }

    @Configuration
    static class SpringMvcConfigurer implements WebMvcConfigurer {

        @Override
        public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
            argumentResolvers.add(new UrlRequestBodyMethodArgumentResolver());
        }
    }
}
