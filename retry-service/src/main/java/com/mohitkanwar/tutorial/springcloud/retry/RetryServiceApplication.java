package com.mohitkanwar.tutorial.springcloud.retry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class RetryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RetryServiceApplication.class, args);
    }

}
