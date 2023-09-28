package com.example.appli.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@Data
// Bien mettre dans les () le même nom qu'on a mis dans le fichier appilcation.properties
@ConfigurationProperties(prefix = "com.example.appli")
public class CustomProperties {
    private String apiURL;
}