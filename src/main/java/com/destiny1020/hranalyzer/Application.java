package com.destiny1020.hranalyzer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.destiny1020.hranalyzer.config.ConnectionSettings;

@Configuration
@ComponentScan({ "com.destiny1020.hranalyzer" })
@EnableAutoConfiguration
@EnableAspectJAutoProxy
@EnableConfigurationProperties(ConnectionSettings.class)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
