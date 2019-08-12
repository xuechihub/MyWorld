package com.my.world.securitymanagement.user.mapper;


import com.my.world.securitymanagement.user.po.RoleResourceR;

public interface RoleResourceRMapper {
    int deleteByPrimaryKey(String id);

    int insert(RoleResourceR record);

    int insertSelective(RoleResourceR record);

    RoleResourceR selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RoleResourceR record);

    int updateByPrimaryKey(RoleResourceR record);
}