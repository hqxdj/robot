package com.xdj.robot.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

    /**
     * add
     *
     * @param userDto
     * @return
     */
    @PostMapping("/add-user")
    public User addUser(@RequestBody UserDto userDto) {
        return iUserService.addUser(userDto);
    }

    /**
     * query one
     *
     * @param userDto
     * @return
     */
    @PostMapping("/find-user")
    public User findUser(@RequestBody UserDto userDto) {
        return iUserService.findUser(userDto);
    }

    /**
     * update
     *
     * @param userDto
     * @return
     */
    @PostMapping("/update-user")
    public User updateUser(@RequestBody UserDto userDto) {
        return iUserService.updateUser(userDto);
    }

    /**
     * delete
     *
     * @param userDto
     */
    @PostMapping("/delete-user")
    public void deleteUser(@RequestBody UserDto userDto) {
        iUserService.deleteUser(userDto);
    }

    /**
     * query page
     *
     * @param userDto
     * @return
     */
    @PostMapping("/page-user")
    public Page<User> pageUser(@RequestBody UserDto userDto) {
        return iUserService.pageUser(userDto);
    }
}
