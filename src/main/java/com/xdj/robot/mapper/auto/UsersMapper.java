package com.xdj.robot.mapper.auto;

import com.xdj.robot.model.auto.Users;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xdj
 * @since 2021-04-16
 */
public interface UsersMapper extends BaseMapper<Users> {

    List<Users> getUsers();
}
