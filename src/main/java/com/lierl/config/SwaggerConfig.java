package com.lierl.config;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author lierl
 * @create 2017-07-10 9:41
 **/
//@Configuration
//@EnableSwagger2
public class SwaggerConfig {

//    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.lierl"))
                .paths(PathSelectors.any())
                .build();
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("供应链金融 RESTful APIs")
                .description("供应链金融")
                .termsOfServiceUrl("https://github.com/dream7319")
                .contact("lierlei0515@163.com")
                .version("1.0")
                .build();
    }
}
