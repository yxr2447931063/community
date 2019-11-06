package com.yxr.config;

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

/**
 * Swagger配置
 *
 * @author wq
 * @since 2017-05-16
 */
//@EnableWebMvc   //这简直是个坑吖   加了之后就报错  "No mapping for GET /swagger-ui.html"
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.yxr.controller")) // 注意修改此处的包名
                .apis(RequestHandlerSelectors.any()) // 注意修改此处的包名
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * @Description: 构建api文档的信息
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 设置页面标题
                .title("标题")
                // 设置联系人
                .contact(
                        new Contact("昵称", "网址链接", "邮箱"))
                // 描述
                .description("描述信息")
                // 定义版本号
                .version("版本号").build();
    }


}









