package com.example.api.config.swager;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 * class $classname
 *
 * @author haijun
 * @date 2018/5/2, 15:09
 */
@Configuration
@EnableSwagger2
public class SwagerConfig {

    private SwagerProperties swaggerProperties;

    public SwagerConfig(SwagerProperties swaggerProperties) {
        this.swaggerProperties = swaggerProperties;
    }

    @Bean
    public Docket productApi() {
        Predicate<String> predicate = swaggerProperties.getDefaultIncludePattern()
                .stream()
                .map(x -> regex(x))
                .reduce((x, y) -> Predicates.or(x, y)).orElse(Predicates.alwaysTrue());

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.api.controller"))
                .paths(predicate)
                .build()
                .apiInfo(metaData());
    }

    private ApiInfo metaData() {
        return new ApiInfo(
                swaggerProperties.getTitle(),
                swaggerProperties.getDescription(),
                swaggerProperties.getVersion(),
                swaggerProperties.getTermsOfServiceUrl(),
                new Contact(swaggerProperties.getContactName(), swaggerProperties.getContactUrl(), swaggerProperties.getContactEmail()),
                swaggerProperties.getLicense(),
                swaggerProperties.getLicenseUrl()
                , new ArrayList<>());
    }
}
