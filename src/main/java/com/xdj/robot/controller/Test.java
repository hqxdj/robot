package com.xdj.robot.controller;

import com.xdj.robot.annotation.TestAnnotation;
import com.xdj.robot.service.IUsersService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class Test {

    @Resource
    private IUsersService usersService;

    @GetMapping("/test/hello")
    public String test() {
        System.out.println("hello world!");
        return "hello world!";
    }

    @TestAnnotation
    @GetMapping("/test/testExecutor")
    public void testExecutor() {
        usersService.testExecutor();
    }

}
