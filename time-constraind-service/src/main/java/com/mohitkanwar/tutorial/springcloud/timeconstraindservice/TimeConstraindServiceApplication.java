package com.mohitkanwar.tutorial.springcloud.timeconstraindservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TimeConstraindServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TimeConstraindServiceApplication.class, args);
    }

}
