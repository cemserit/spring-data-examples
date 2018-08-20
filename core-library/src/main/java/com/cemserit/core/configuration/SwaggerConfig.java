package com.cemserit.core.configuration;

import org.springframework.beans.factory.annotation.Value;
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

import java.util.Arrays;
import java.util.HashSet;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${swagger.schemes:http,https}")
    private String schemas;
    @Value("${swagger.info.description:}")
    private String description;
    @Value("${swagger.info.version:}")
    private String version;
    @Value("${swagger.info.title:}")
    private String title;
    @Value("${swagger.info.termsOfService:}")
    private String termsOfService;
    @Value("${swagger.info.contact.email:}")
    private String contactEmail;
    @Value("${swagger.info.contact.name:}")
    private String contactName;
    @Value("${swagger.info.contact.url:}")
    private String contactUrl;
    @Value("${swagger.info.contact.license.name:}")
    private String licenseName;
    @Value("${swagger.info.contact.license.url:}")
    private String licenseUrl;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .protocols(new HashSet<>(Arrays.asList(schemas.split(","))))
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                .termsOfServiceUrl(termsOfService)
                .contact(new Contact(contactName, contactUrl, contactEmail))
                .license(licenseName)
                .licenseUrl(licenseUrl)
                .version(version)
                .build();
    }
}