package com.zouzanyan.zanmanager.configuration;


import com.zouzanyan.zanmanager.interceptor.AdminInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AdminInterceptorConfiguration implements WebMvcConfigurer {

    @Bean
    public AdminInterceptor adminInterceptor(){
        return new AdminInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        InterceptorRegistration registration = registry.addInterceptor(adminInterceptor());
        registration.addPathPatterns("/**");
        registration.excludePathPatterns(
                "/**/login"
        );
    }
}
