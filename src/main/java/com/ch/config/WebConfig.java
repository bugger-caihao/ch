package com.ch.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName: WebConfig
 * @Description: TODO
 * @Author: caihao
 * @Date: 2019/6/27 21:11
 */
//@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public MyIntegerceptor getMyIntegerceptor(){
        return new MyIntegerceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        InterceptorRegistration interceptor= registry.addInterceptor(new MyIntegerceptor());

        //  配置拦截路径
        interceptor.addPathPatterns("/**");

        //  排除拦截的路径
        interceptor.excludePathPatterns("/");//  不拦截登陆首页
        interceptor.excludePathPatterns("/doLogin");//  不拦截登陆请求
        interceptor.excludePathPatterns("/error");//  不拦截异常处理请求（SpringBoot的异常会默认的交给/error请求处理）
        interceptor.excludePathPatterns("/asset/**");//  不拦截静态资源文件的访问请求
        interceptor.excludePathPatterns("/**.html");//  不拦截静态资源文件html的访问请求
        interceptor.excludePathPatterns("/register");//  不拦截静态资源文件html的访问请求
        interceptor.excludePathPatterns("/doRegister");//  不拦截后台注册的请求
        interceptor.excludePathPatterns("/swagger-resources/**", "/webjars/**");//  不拦swagger请求,"/swagger-ui.html"

    }
}
