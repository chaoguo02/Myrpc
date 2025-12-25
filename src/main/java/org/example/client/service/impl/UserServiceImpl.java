package org.example.client.service.impl;

import org.example.client.service.UserService;
import org.example.pojo.User;

import java.util.Random;
import java.util.UUID;

public class UserServiceImpl implements UserService {
    @Override
    public User getUserByUserId(Integer id) {
        System.out.println("客户端查询了" + id + "的用户");
        Random random = new Random();
        User user = User
                .builder()
                .id(id)
                .sex(random.nextBoolean())
                .userName(UUID.randomUUID().toString())
                .build();
        return user;
    }

    @Override
    public Integer insertUserId(User user) {
        System.out.println("插入数据成功：" + user);
        return 1;
    }
}
