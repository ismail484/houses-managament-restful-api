package com.task.houseManagement.config;


import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.util.Collections;

@Configuration
@EnableSwagger2
@ApiResponses(value = {
                        @ApiResponse(code =400, message = "this is a bad request , please follow the API documentation."),
                        @ApiResponse(code =401, message = "due to security constrains you are not allowed to access the api."),
                        @ApiResponse(code =500, message = "server is doen ...please try again later")

})
public class SwaggerConfig {


    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false);
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Location API",
                "This API returns a list of Houses for House management .",
                "1.0",
                "http://www.udacity.com/tos",
                new Contact("Mohamed Ismail", "www.Home.com", "moh_ahmed484@Hotmail.com.com"),
                "License of API", "http://www.Home.com/license", Collections.emptyList());
    }

}
