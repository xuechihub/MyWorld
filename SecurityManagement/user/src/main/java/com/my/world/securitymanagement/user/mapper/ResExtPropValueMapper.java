package com.my.world.securitymanagement.user.mapper;

import com.my.world.securitymanagement.user.po.ResExtPropValue;

public interface ResExtPropValueMapper {
    int deleteByPrimaryKey(String id);

    int insert(ResExtPropValue record);

    int insertSelective(ResExtPropValue record);

    ResExtPropValue selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ResExtPropValue record);

    int updateByPrimaryKey(ResExtPropValue record);
}