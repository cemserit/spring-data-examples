package com.cemserit.cassandra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.cemserit.cassandra.service")
@EnableAutoConfiguration
public class CassandraApplication {

    public static void main(String[] args) {
        SpringApplication.run(CassandraApplication.class, args);
    }
}
