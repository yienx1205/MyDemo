package com.yienx.aspect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
// @ImportResource(locations = {"classpath:application.xml"})
public class AspectApplication {

    public static void main(String[] args) {
        SpringApplication.run(AspectApplication.class, args);
    }

}
