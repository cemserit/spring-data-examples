package com.cemserit.cassandra.configuration;

import com.cemserit.core.configuration.SwaggerConfig;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("com.cemserit")
@EnableAutoConfiguration
@Import({SwaggerConfig.class})
public class ApplicationConfig {
}