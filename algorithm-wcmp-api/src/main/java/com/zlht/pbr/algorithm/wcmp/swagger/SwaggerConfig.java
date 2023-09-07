package com.zlht.pbr.algorithm.wcmp.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author zi jian Wang
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket createAdminRestApi() {
        return new Docket(DocumentationType.OAS_30)
                .groupName("admin")
                .apiInfo(admin())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.zlht.pbr.algorithm.wcmp.controller.admin"))
                .build();
    }

    private ApiInfo admin() {
        return new ApiInfoBuilder()
                .title("AI体态识别系统-机构管理员")
                .description("AI Body Recognition System - Agency Administrator")
                .version("1.0.0")
                .build();
    }

    @Bean
    public Docket createUserRestApi() {
        return new Docket(DocumentationType.OAS_30)
                .groupName("user")
                .apiInfo(user())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.zlht.pbr.algorithm.wcmp.controller.user"))
                .build();
    }

    private ApiInfo user() {
        return new ApiInfoBuilder()
                .title("AI体态识别系统-小程序用户端")
                .description("AI body recognition system-algorithm-  Mini program client ")
                .version("1.0.0")
                .build();
    }
}