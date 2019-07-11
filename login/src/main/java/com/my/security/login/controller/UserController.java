package com.my.security.login.controller;

import com.my.security.login.consts.MetaDataConsts;
import com.my.security.login.mapper.UserMapper;
import com.my.security.login.po.User;
import com.my.security.login.po.UserExample;
import com.my.security.login.utils.YmlUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * @program: securitymanagement
 * @description: 用户控制器
 * @author: xuechi
 * @create: 2019-07-10 09:09
 **/
@RestController
@RequestMapping("/rest/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping(method = RequestMethod.GET)
    public Object getUserList() {
        UserExample query = new UserExample();
        query.setLimitStart(0);
        query.setLimitSize(10);
        List<User> users = userMapper.selectByExample(query);
        HashMap<String, Object> list = new HashMap<String,Object>();
        HashMap<String, Object> data = new HashMap<String,Object>();
        list.put("list", data);
        data.put("total",users.size());
        data.put("pageSize",10);
        data.put("currentPage",1);
        data.put("data",users);
        return list;
    }

    @RequestMapping(value = {"/{id}"},method = RequestMethod.GET)
    public Object getUserById(@PathVariable("id") String id) {
        User user= userMapper.selectById(id);
        HashMap<String, Object> data = new HashMap<String,Object>();;
        user.setPassword(null);
        data.put("data",user);
        return data;
    }
}
