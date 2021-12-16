package com.cos.photogramstart.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer { // 웹 설정 파일

    // 사진 호출 관련 설정

    @Value("${file.path}")
    private String uploadFolder;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        WebMvcConfigurer.super.addResourceHandlers(registry);

        // file:////Users/yoon/Desktop/springbootwork/upload/
        registry
                .addResourceHandler("/upload/**") // jsp페이지에서 /upload/** 이런 주소 패턴이 나오면 발동시킨다라는 뜻
                .addResourceLocations("file:///" + uploadFolder)
                .setCachePeriod(60 * 10 * 6) // 1시간 동안 이미지 캐싱
                .resourceChain(true)
                .addResolver(new PathResourceResolver());
    }
}