package com.corporation.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/user")
    public String getUser() {
        return "Vlad Mishustin";
    }

    private void greet() {
        System.out.println("Hello");
    }
}
