package com.xdj.robot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xdj.robot.mapper.auto.UsersMapper;
import com.xdj.robot.model.auto.Users;
import com.xdj.robot.service.IUsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xdj
 * @since 2021-04-16
 */
@Service
@RequiredArgsConstructor
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements IUsersService {

    @Resource
    private UsersMapper usersMapper;

    public Users getUsers() {
        QueryWrapper<Users> usersQueryWrapper = new QueryWrapper<>();
        usersQueryWrapper.eq("username", "nacos");
        List<Users> users = usersMapper.getUsers();
        Users users1 = usersMapper.selectOne(usersQueryWrapper);



        return users.get(0);
    }


}
