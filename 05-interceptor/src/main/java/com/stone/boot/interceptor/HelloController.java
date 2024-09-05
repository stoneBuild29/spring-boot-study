package com.stone.boot.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className: HelloController
 * @author: Scarlet
 * @date: 2024/8/13
 **/
@Slf4j
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        log.info("hello");
        return "Hello Spring Boot";
    }
}
