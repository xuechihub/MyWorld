package com.my.security.login.mapper;

import com.my.security.login.po.ResExtPropValue;

public interface ResExtPropValueMapper {
    int deleteByPrimaryKey(String id);

    int insert(ResExtPropValue record);

    int insertSelective(ResExtPropValue record);

    ResExtPropValue selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ResExtPropValue record);

    int updateByPrimaryKey(ResExtPropValue record);
}