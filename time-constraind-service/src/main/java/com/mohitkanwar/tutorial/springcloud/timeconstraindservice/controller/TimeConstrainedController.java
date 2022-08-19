package com.mohitkanwar.tutorial.springcloud.timeconstraindservice.controller;

import com.mohitkanwar.tutorial.springcloud.timeconstraindservice.client.SlowServiceClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")

public class TimeConstrainedController {

    private SlowServiceClient slowServiceClient;
    private String defaultMessage = "This is a default message.";

    public TimeConstrainedController(SlowServiceClient slowServiceClient) {
        this.slowServiceClient = slowServiceClient;
    }

    @GetMapping
    @CircuitBreaker(name = "slow-service" , fallbackMethod = "slowServiceFallback")
    public String getFast(){

        String message = slowServiceClient.getMessage();
        return defaultMessage + "\n"+ message;
    }

    public String slowServiceFallback(Exception e) {
        return defaultMessage + "\n" + "This is a fallback";
    }
}
