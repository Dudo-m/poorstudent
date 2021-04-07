package com.edu.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/*")
                .addPathPatterns("/teacher/*")
                .addPathPatterns("/admin/*")
                .excludePathPatterns("/")
                .excludePathPatterns("/login")
                .excludePathPatterns("/codeImg")
                .excludePathPatterns("/register")
                .excludePathPatterns("/submitRegister")
                .excludePathPatterns("/userName");
    }
}
