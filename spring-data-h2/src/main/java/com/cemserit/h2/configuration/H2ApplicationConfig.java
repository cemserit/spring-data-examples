package com.cemserit.h2.configuration;

import com.cemserit.core.configuration.SwaggerConfig;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@EnableAutoConfiguration
@ComponentScan("com.cemserit.h2")
@EntityScan("com.cemserit.h2.model")
@Import({SwaggerConfig.class})
public class H2ApplicationConfig {
}