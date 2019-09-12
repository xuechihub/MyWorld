package com.my.world.securitymanagement.api.user.mapper;

import com.my.world.securitymanagement.api.user.po.UserRoleR;

public interface UserRoleRMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserRoleR record);

    int insertSelective(UserRoleR record);

    UserRoleR selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserRoleR record);

    int updateByPrimaryKey(UserRoleR record);
}