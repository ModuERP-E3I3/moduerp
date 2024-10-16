package com.e3i3.moduerp.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Configuration
public class AppConfig {

    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setMaxUploadSize(10485760); // 최대 업로드 사이즈 10MB
        resolver.setDefaultEncoding("UTF-8");
        return resolver;
    }

    // 기타 빈 정의
}