package com.my.security.login.mapper;

import com.my.security.login.po.UserUserGroupR;

public interface UserUserGroupRMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserUserGroupR record);

    int insertSelective(UserUserGroupR record);

    UserUserGroupR selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserUserGroupR record);

    int updateByPrimaryKey(UserUserGroupR record);
}