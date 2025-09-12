package com.souko.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
//クロスドメイン
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 所有路径
                .allowedOriginPatterns("*") // 允许的前端地址
                .allowedMethods("*") // 所有方法
                .allowedHeaders("*")
              //  .allowedHeaders("Authorization", "Content-Type", "X-Requested-With") // 所有请求头
               // .exposedHeaders("*")
                .allowCredentials(true); // 允许携带 cookie
    }
}

