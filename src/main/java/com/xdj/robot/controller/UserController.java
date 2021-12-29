package com.xdj.robot.controller;


import com.xdj.robot.dto.UserDto;
import com.xdj.robot.model.User;
import com.xdj.robot.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author xdj
 * @since 2021-12-29
 */
@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final IUserService iUserService;

    @PostMapping("/add-user")
    public User addUser(@RequestBody UserDto userDto) {
        return iUserService.addUser(userDto);
    }


    @PostMapping("/find-user")
    public User findUser(@RequestBody UserDto userDto) {
        return iUserService.findUser(userDto);
    }

    @PostMapping("/update-user")
    public User updateUser(@RequestBody UserDto userDto) {
        return iUserService.updateUser(userDto);
    }

    @PostMapping("/delete-user")
    public void deleteUser(@RequestBody UserDto userDto) {
         iUserService.deleteUser(userDto);
    }
}
