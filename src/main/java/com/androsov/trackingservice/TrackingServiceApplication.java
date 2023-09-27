package com.androsov.trackingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
public class TrackingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrackingServiceApplication.class, args);
    }

}
