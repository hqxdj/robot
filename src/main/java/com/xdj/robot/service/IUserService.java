package com.xdj.robot.service;

import com.xdj.robot.dto.UserDto;
import com.xdj.robot.model.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author xdj
 * @since 2021-12-29
 */
public interface IUserService extends IService<User> {

    /**
     * 新增用户
     * @param dto
     * @return
     */
    User addUser(UserDto dto);

    /**
     * 查找用户
     * @param dto
     * @return
     */
    User findUser(UserDto dto);

    /**
     * 更新用户
     * @param dto
     * @return
     */
    User updateUser(UserDto dto);

    /**
     * 删除用户
     * @param userDto
     */
    void deleteUser(UserDto userDto);
}
