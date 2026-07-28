package com.varun.SpringBootDemoJpa.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {
    @RequestMapping("/")
    public String getBook(){
        return "Book is Not Available";
    }
}
