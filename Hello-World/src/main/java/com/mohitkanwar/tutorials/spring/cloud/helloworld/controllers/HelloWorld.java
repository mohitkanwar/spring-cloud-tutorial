package com.mohitkanwar.tutorials.spring.cloud.helloworld.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class HelloWorld {
    @GetMapping("greeting")
    public String greet(){
        return "Hello World";
    }
}
