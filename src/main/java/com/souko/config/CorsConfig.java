package com.souko.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 所有路径
                .allowedOriginPatterns("http://localhost:9003") // 允许的前端地址
                .allowedMethods("*") // 所有方法
                .allowedHeaders("*") // 所有请求头
                .allowCredentials(true); // 允许携带 cookie
    }
}

