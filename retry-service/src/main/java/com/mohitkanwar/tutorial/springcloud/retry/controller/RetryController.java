package com.mohitkanwar.tutorial.springcloud.retry.controller;

import com.mohitkanwar.tutorial.springcloud.retry.client.SlowServiceClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")

public class RetryController {

    private SlowServiceClient slowServiceClient;
    private String defaultMessage = "This is a default message.";
    private int counter = 1;

    public RetryController(SlowServiceClient slowServiceClient) {
        this.slowServiceClient = slowServiceClient;
    }

    @GetMapping
    @Retry(name = "slow-service", fallbackMethod = "slowServiceFallback")
    public String getFast(){
        System.out.println("Try Count ::" + ++counter);
        String message = slowServiceClient.getMessage();

        return defaultMessage + "\n"+ message;
    }

    public String slowServiceFallback(Exception e) {
        return defaultMessage + "\n" + "This is a fallback";
    }
}
