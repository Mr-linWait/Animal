package com.hellen.base.config;

import com.hellen.base.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                // 设置允许跨域请求的源（origin）
                .allowedOriginPatterns("*")
                // 设置允许的请求方法
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("X-User-ID")
                // 设置允许的请求头
                .allowedHeaders("*") // 允许所有头，或明确列出所需的头，如"X-User-ID", "Authorization"
                // 是否允许携带Cookie
                .allowCredentials(true)
                // 预检请求的有效期，单位为秒。在这个时间内，浏览器不会对相同的跨域请求再次发起预检请求
                .maxAge(3600); // 1小时
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/animalInfo/hotAnimal")//热门宠物
                .excludePathPatterns("/animalInfo/hotSearchAnimal")
                .excludePathPatterns("/animalInfo/animalInfoList/**")
                .excludePathPatterns("/animalInfo/getSendAnimalInfoList/**")
                .excludePathPatterns("/animalInfo/getSearchAnimalInfoList/**")
                .excludePathPatterns("/code/**")
                .excludePathPatterns("/user/login/**")
                .excludePathPatterns("/user/logOut")
                .excludePathPatterns("/user/regist")
                .excludePathPatterns("/**/*.jpg", "/static/**", "/resources/**", "/webjars/**", "/swagger-ui.html**", "/v2/api-docs**")
                .excludePathPatterns("/animalInfo/hotSearchAnimal")
                .excludePathPatterns("/animalInfo/hotSearchAnimal");
    }





}
