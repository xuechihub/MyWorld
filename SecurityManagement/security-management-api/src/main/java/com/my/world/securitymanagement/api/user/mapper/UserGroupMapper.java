package com.my.world.securitymanagement.api.user.mapper;


import com.my.world.securitymanagement.api.user.po.UserGroup;

public interface UserGroupMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserGroup record);

    int insertSelective(UserGroup record);

    UserGroup selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserGroup record);

    int updateByPrimaryKey(UserGroup record);
}