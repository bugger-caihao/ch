package com.ch.config;

import com.alibaba.druid.sql.visitor.functions.Concat;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * @ClassName: SwaggerConfig
 * @Description: Swagger接口文档api
 * @Author: caihao
 * @Date: 2019/7/31 15:21
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {


    /**
     *   配置多个分组，往容器中注入多个 Docker ,设置不同的 groupName 即可
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("dev")
                //.ignoredParameterTypes(HttpServletRequest.class) //配置了这个，方法中有request的参数时，生成的接口文档中不带这个request类型的参数
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ch.controller"))
                .paths(PathSelectors.any())//  any()是扫描所有 ant("")扫描路径
                .build();
    }

    @Bean
    public Docket student() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("student")
                //.ignoredParameterTypes(HttpServletRequest.class) //配置了这个，方法中有request的参数时，生成的接口文档中不带这个request类型的参数
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ch.controller"))
                .paths(PathSelectors.ant("/student/**"))//  any()是扫描所有 ant("")扫描路径
                .build();
    }

    @Bean
    public Docket teacher() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("teacher")
                //.ignoredParameterTypes(HttpServletRequest.class) //配置了这个，方法中有request的参数时，生成的接口文档中不带这个request类型的参数
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ch.controller"))
                .paths(PathSelectors.ant("/teacher/**"))//  any()是扫描所有 ant("")扫描路径
                .build();
    }

    private ApiInfo apiInfo() {
        /*return new ApiInfoBuilder()
                .title("SpringBoot项目中使用Swagger生成接口文档")
                .description("项目接口文档")
                .termsOfServiceUrl("http://localhost:8080/")
                .contact("sunf")
                .version("1.0")
                .build();*/
        Contact contact = new Contact("蔡浩", "https://www.baidu.com/", "519753290@qq.com");
        return new ApiInfo(
                "ch项目接口文档Swagger2",                 //  标题
                "测试使用Swagger2生成接口文档",      //  描述
                "v1.0",                               //  版本号
                "http://localhost:8080/",     //  链接
                 contact,                                     //  联系人信息
                null,                                  //  许可
                null,                                //  许可链接
                new ArrayList<>());                            //  扩展
    }


}
