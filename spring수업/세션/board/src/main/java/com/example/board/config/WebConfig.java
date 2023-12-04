package com.example.board.config;

import com.example.board.interceptor.SessionCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SessionCheckInterceptor())
                .order(1).addPathPatterns("/**")
                .excludePathPatterns("/")
                .excludePathPatterns("/login");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("file:src/main/resources/static/upload/")
                .addResourceLocations("file:///C:/upload/");
    }
}
