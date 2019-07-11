package com.my.security.login.mapper;

import com.my.security.login.po.UserRoleR;

public interface UserRoleRMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserRoleR record);

    int insertSelective(UserRoleR record);

    UserRoleR selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserRoleR record);

    int updateByPrimaryKey(UserRoleR record);
}