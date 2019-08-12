package com.my.world.securitymanagement.user.mapper;

import com.my.world.securitymanagement.user.po.ResExtProp;

public interface ResExtPropMapper {
    int deleteByPrimaryKey(String id);

    int insert(ResExtProp record);

    int insertSelective(ResExtProp record);

    ResExtProp selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ResExtProp record);

    int updateByPrimaryKey(ResExtProp record);
}