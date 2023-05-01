package com.ufcg.apihealthnotes.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
@SecurityRequirement(name = "bearer-key")
public class HelloWorldController {

    @GetMapping
    public String HelloWorld() {
        return "Hello World!";
    }
}
