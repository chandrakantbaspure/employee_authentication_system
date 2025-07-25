package com.employeeauthenticationsystem.controller;


import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/public")
public class Welcome {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(Welcome.class);

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to User Authentication System";
    }

}
