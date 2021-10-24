package com.example.springrestcontrollereclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class SpringRestControllerEClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringRestControllerEClientApplication.class, args);
    }

}
