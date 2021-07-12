package com.santander;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class SantanderWebfluxClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(SantanderWebfluxClientApplication.class, args);
    }

}
