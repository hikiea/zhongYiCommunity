package com.example.homework.Configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ImgConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 本地服务器
//        registry.addResourceHandler("/picture/head/**").addResourceLocations("file:" + "F:\\IDEA\\Spring_Boot\\zhongYiCommunity\\src\\main\\resources\\static\\picture\\head\\");
        // 阿里云服务器
        registry.addResourceHandler("/picture/head/**").addResourceLocations("file:" + "C:\\Users\\Administrator\\Desktop\\head\\");
    }
}