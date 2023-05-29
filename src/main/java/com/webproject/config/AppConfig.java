package com.webproject.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.webproject.Filter.XSSReponseInterceptor;
@Configuration
public class AppConfig implements WebMvcConfigurer {
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new XSSReponseInterceptor())
                .addPathPatterns("/**");
    }
}
