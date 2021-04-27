package com.xdj.robot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xdj.robot.mapper.auto.UsersMapper;
import com.xdj.robot.model.auto.Users;
import com.xdj.robot.service.IUsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xdj
 * @since 2021-04-16
 */
@Service
@RequiredArgsConstructor
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements IUsersService {

    @Resource
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Resource
    private UsersMapper usersMapper;

    @Override
    public Users getUsers() {
        QueryWrapper<Users> usersQueryWrapper = new QueryWrapper<>();
        usersQueryWrapper.eq("username", "nacos");
        List<Users> users = usersMapper.getUsers();
        Users users1 = usersMapper.selectOne(usersQueryWrapper);


        return users.get(0);
    }

    @Async(value = "threadPoolTaskExecutor")
    @Override
    public void testExecutor() {
        System.out.println("=====================" + Thread.currentThread().getName() + "=========================");
        for (int i = 0; i < 100; i++) {
            threadPoolTaskExecutor.execute(() -> {
                try {
                    Thread.sleep(1000L);
                    System.out.println("==============" + Thread.currentThread().getName() + "============");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        int activeCount = threadPoolTaskExecutor.getActiveCount();
        System.out.println("current active thread" + activeCount);
    }

}
