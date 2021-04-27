package com.xdj.robot.service;

import com.xdj.robot.model.auto.Users;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xdj
 * @since 2021-04-27
 */
public interface IUsersService extends IService<Users> {

    Users getUsers();

    void testExecutor();
}
