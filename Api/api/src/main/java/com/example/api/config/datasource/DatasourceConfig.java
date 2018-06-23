package com.example.api.config.datasource;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * class $classname
 *
 * @author haijun
 * @date 2018/4/19, 11:03
 */
@Configuration
public class DatasourceConfig {

    @Bean("readwriteDataSourceProperties")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.hikari.readwrite")
    public DataSourceProperties readwriteDataSourceProperties(){
        return new DataSourceProperties();
    }

    @Bean("readwriteDataSource")
    @Qualifier("readwriteDataSourceProperties")
    @Primary
    public DataSource readwriteDataSource(DataSourceProperties dataSourceProperties) {
        return dataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }
}
