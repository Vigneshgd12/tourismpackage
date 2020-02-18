package com.automic.tourismpackage.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//@Configuration
//@EnableSwagger2
//@ComponentScan(basePackages = {"com.automic.tourismpackage.controller"})
public class SwaggerConfig{

    @Bean
    public Docket enjoymoreAPI() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("HomeCare")
                .select().apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .build();
    }

    //TODO - Need the contact, license info & terms of service for the API doc.
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Tourism Package service")
                .description("API for Tourism package service")
                .version("1.0.0")
                .build();
    }
}
