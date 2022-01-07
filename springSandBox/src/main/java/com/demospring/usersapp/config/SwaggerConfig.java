package com.demospring.usersapp.config;

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

@Configuration
@EnableSwagger2
public class SwaggerConfig {

  @Bean
  public Docket getDocket() {
    return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(getApiInfo())
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.demospring.usersapp.controllers"))
        .paths(PathSelectors.any())
        .build()
        .useDefaultResponseMessages(false);
  }

  private ApiInfo getApiInfo() {
    return new ApiInfoBuilder()
        .title("Springboot-Demo-Swagger")
        .version("1.0")
        .license("Apache 2.0")
        .contact(new Contact("Mr.Fish", "https://google.com", "tumail85@gmail.com"))
        .build();
  }
}
