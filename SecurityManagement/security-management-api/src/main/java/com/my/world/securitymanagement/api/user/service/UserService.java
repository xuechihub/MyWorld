package com.my.world.securitymanagement.api.user.service;

import com.my.world.securitymanagement.api.user.mapper.UserMapper;
import com.my.world.securitymanagement.api.user.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: securitymanagement
 * @description: 用户管理服务
 * @author: xuechi
 * @create: 2019-04-19 10:57
 **/
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User getUserList() {
        return null;
    }
}
