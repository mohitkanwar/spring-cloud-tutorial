package com.mohitkanwar.tutorials.spring.cloud.helloworld.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
@RefreshScope
public class HelloWorld {

    @Value("${hello.name:Worl}")
    private String name;
    @GetMapping("greeting")
    public String greet(){
        return "Hello " + name;
    }
}
