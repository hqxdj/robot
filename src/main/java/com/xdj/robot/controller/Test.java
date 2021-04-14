package com.xdj.robot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {

    @GetMapping("/test/hello")
    public String test() {
        System.out.println("hello world!");
        return "hello world!";
    }


}
