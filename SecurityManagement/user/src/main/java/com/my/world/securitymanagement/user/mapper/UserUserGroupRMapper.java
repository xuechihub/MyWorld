package com.my.world.securitymanagement.user.mapper;

import com.my.world.securitymanagement.user.po.UserUserGroupR;

public interface UserUserGroupRMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserUserGroupR record);

    int insertSelective(UserUserGroupR record);

    UserUserGroupR selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserUserGroupR record);

    int updateByPrimaryKey(UserUserGroupR record);
}