package com.my.world.securitymanagement.user.controller;

import com.my.world.common.annotations.CommonResponseBody;
import com.my.world.common.annotations.UrlRequestBody;
import com.my.world.common.validator.NewPOValidatorGroup;
import com.my.world.securitymanagement.user.mapper.UserMapper;
import com.my.world.securitymanagement.user.po.User;
import com.my.world.securitymanagement.user.vo.RequestQueryCondition;
import com.my.world.securitymanagement.user.vo.TableViewResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @program: MyWorld
 * @description: 用户控制器
 * @author: xue chi
 * @create: 2019-07-10 09:09
 **/
@RestController
@RequestMapping("/rest/user")
@CommonResponseBody
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping(method = RequestMethod.GET)
    public TableViewResult getUserList(@UrlRequestBody RequestQueryCondition requestQueryCondition) {
        List<User> users = userMapper.getList(requestQueryCondition);
        TableViewResult tableViewResult = new TableViewResult();
        tableViewResult.setTotal(users.size());
        tableViewResult.setPageSize(10);
        tableViewResult.setCurrentPage(1);
        tableViewResult.setItems(users);
        return tableViewResult;
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
