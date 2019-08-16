package com.my.world.securitymanagement.user.controller;

import com.my.world.common.annotations.CommonRequestBody;
import com.my.world.securitymanagement.user.mapper.EnumMapper;
import com.my.world.securitymanagement.user.po.Enum;
import com.my.world.securitymanagement.user.po.User;
import com.my.world.securitymanagement.user.po.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * @program: MyWorld
 * @description: 自定义枚举控制器
 * @author: xue chi
 * @create: 2019-08-16 15:08
 **/
@RestController
@RequestMapping("/rest/enum")
@CommonRequestBody
public class EnumController {

    @Autowired
    private EnumMapper enumMapper;

    @RequestMapping(method = RequestMethod.GET)
    public HashMap<String, Object> getEnumList() {
        UserExample query = new UserExample();
        query.setLimitStart(0);
        query.setLimitSize(10);
        final List<Enum> enumList = enumMapper.getList();
        HashMap<String, Object> list = new HashMap<String, Object>(1);
        HashMap<String, Object> data = new HashMap<String, Object>(20);
        list.put("list", data);
        data.put("total", enumList.size());
        data.put("pageSize", 10);
        data.put("currentPage", 1);
        data.put("data", enumList);
        return list;
    }
}
