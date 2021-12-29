package com.xdj.robot.mapper;

import com.xdj.robot.dto.UserDto;
import com.xdj.robot.model.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author xdj
 * @since 2021-12-29
 */
public interface UserMapper extends BaseMapper<User> {
    /**
     * 查找用户
     * @param userDto
     * @return
     */
    User selectByParams(UserDto userDto);

}
