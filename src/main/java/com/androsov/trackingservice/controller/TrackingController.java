package com.androsov.trackingservice.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/tracking")
public class TrackingController {

    @GetMapping(path = "/test")
    public String test() {
        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return "Hello, " + username + "!";
    }

}
