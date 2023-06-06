package com.mohitkanwar.tutorials.springcloud.slowservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("/")
@RefreshScope
public class SlowController {
    @Value("${slow.time.minimum:1}")
    private int minimumTime;

    @Value("${slow.time.maximum:30}")
    private int maximumTime;
    @GetMapping
    public String getResponse(){
        long randomNum = ThreadLocalRandom.current().nextInt(minimumTime, maximumTime + 1);
        try {
            Thread.sleep(randomNum * 1000L);
            return "I waited for " + randomNum + " seconds.";
        } catch (InterruptedException e) {
            e.printStackTrace();
            return "My sleep was inturrepted. I was supposed to wait for " + randomNum + " seconds.";
        }
    }
}
