package com.xdj.robot.controller;


import com.xdj.robot.model.auto.Users;
import com.xdj.robot.service.IUsersService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xdj
 * @since 2021-04-16
 */
@RestController
@RequestMapping("/users")
public class UsersController {

    @Resource
    private IUsersService usersService;

    @GetMapping("/user")
    public Users getUsers() {
        return usersService.getUsers();
    }

}
