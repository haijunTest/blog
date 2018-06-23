package com.example.api.config.swager;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * class $classname
 *
 * @author haijun
 * @date 2018/5/2, 15:15
 */
@Data
@ConfigurationProperties(prefix = "swagger",ignoreUnknownFields = false)
public class SwagerProperties {
    private List<String> defaultIncludePattern;

    private String title;

    private String description;

    private String version;

    private String termsOfServiceUrl;

    private String contactName;

    private String contactUrl;

    private String contactEmail;

    private String license;

    private String licenseUrl;
}
