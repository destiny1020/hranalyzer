package com.destiny1020.hranalyzer.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("jdbc")
public class ConnectionSettings {
    private String driver;
    private String url;
    private String username;
    private String password;

    public ConnectionSettings() {
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
