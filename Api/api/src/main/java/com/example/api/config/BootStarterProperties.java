package com.example.api.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * class $classname
 *
 * @author haijun
 * @date 2018/5/2, 17:55
 */
@Data
@ConfigurationProperties(prefix = "bootStarter")
public class BootStarterProperties {
}
