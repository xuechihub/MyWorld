package com.my.world.securitymanagement.user.controller;

import com.my.world.common.annotations.CommonRequestBody;
import com.my.world.common.validator.NewPOValidatorGroup;
import com.my.world.securitymanagement.user.mapper.UserMapper;
import com.my.world.securitymanagement.user.po.User;
import com.my.world.securitymanagement.user.po.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
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
@CommonRequestBody
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping(method = RequestMethod.GET)
    public Object getUserList() {
        UserExample query = new UserExample();
        query.setLimitStart(0);
        query.setLimitSize(10);
        List<User> users = userMapper.selectByExample(query);
        HashMap<String, Object> list = new HashMap<String, Object>(1);
        HashMap<String, Object> data = new HashMap<String, Object>(20);
        list.put("list", data);
        data.put("total", users.size());
        data.put("pageSize", 10);
        data.put("currentPage", 1);
        data.put("data", users);
        return list;
    }

    @RequestMapping(value = {"/{id}"}, method = RequestMethod.GET)
    public Object getUserById(@PathVariable("id") String id) {
        User user = userMapper.selectById(id);
        HashMap<String, Object> data = new HashMap<String, Object>(20);
        user.setPassword(null);
        data.put("data", user);
        return data;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public int saveUser(@RequestBody() @Validated(NewPOValidatorGroup.class) User user) {
        user.setCreateTime(new Date());
        user.setCreateUser("2b17abe1972511e9bdeb8c1645bb7fe9");
        int result = userMapper.insert(user);
        return result;
    }

    @RequestMapping(method = RequestMethod.POST)
    public int updateUser(@RequestBody() @Valid User user) {
        int result = userMapper.updateByIdSelective(user);
        return result;
    }

    @RequestMapping(value="/{ids}", method = RequestMethod.DELETE)
    public String[] deleteUser(@PathVariable("ids") String ids) {
        String[] idArrays= ids.split(",");
        for (String id : idArrays) {
            userMapper.deleteById(id);
        }
        return idArrays;
    }
}