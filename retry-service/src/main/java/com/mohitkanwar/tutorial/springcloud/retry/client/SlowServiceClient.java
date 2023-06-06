package com.mohitkanwar.tutorial.springcloud.retry.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "slow-service")
public interface SlowServiceClient {
    @GetMapping("/")
    String getMessage();
}
