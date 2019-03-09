package com.chenahua.jta.conf;


import com.chenahua.jta.intercepter.OrderIntercepter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class OrderWebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        OrderIntercepter interceptor = new OrderIntercepter();
        registry.addInterceptor(interceptor).addPathPatterns("/order/**");
    }



}
