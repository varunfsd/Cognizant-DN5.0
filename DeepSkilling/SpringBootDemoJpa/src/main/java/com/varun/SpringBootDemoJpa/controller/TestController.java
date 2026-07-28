package com.varun.SpringBootDemoJpa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/")
    public String testMethod(){
        return "Spring Boot Running..";
    }
}
