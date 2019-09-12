package com.my.world.securitymanagement.api.user.controller;

import com.my.world.common.rest.annotations.DefaultResultResponseBody;
import com.my.world.securitymanagement.api.user.mapper.EnumMapper;
import com.my.world.securitymanagement.api.user.po.Enum;
import com.my.world.securitymanagement.api.user.po.UserExample;
import com.my.world.common.rest.vo.TableViewResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: MyWorld
 * @description: 自定义枚举控制器
 * @author: xue chi
 * @create: 2019-08-16 15:08
 **/
@RestController
@RequestMapping("/rest/enum")
@DefaultResultResponseBody
public class EnumController {

    @Autowired
    private EnumMapper enumMapper;

    @RequestMapping(method = RequestMethod.GET)
    public TableViewResult getEnumList() {
        UserExample query = new UserExample();
        query.setLimitStart(0);
        query.setLimitSize(10);
        final List<Enum> enumList = enumMapper.getList();
        TableViewResult tableViewResult = new TableViewResult();
        tableViewResult.setTotal(enumList.size());
        tableViewResult.setPageSize(10);
        tableViewResult.setCurrentPage(1);
        tableViewResult.setItems(enumList);
        return tableViewResult;
    }
}
