package com.efostach.employeesmanager.profiles.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@ConfigurationProperties("spring.datasource")
@Slf4j
public class DBConfiguration {

    private String driverClassName;
    private String url;
    private String username;
    private String password;

    @Profile("dev")
    @Bean
    public String devDatabaseConnection() {
        log.info("DB connection to DEV: {}, {}", driverClassName, url);
        return "DB connection to DEV";
    }

    @Profile("prod")
    @Bean
    public String prodDatabaseConnection() {
        log.info("DB connection for PROD: {}, {}", driverClassName, url);
        return "DB connection to PROD";
    }
}
