package com.my.security.login.mapper;

import com.my.security.login.po.Enum;

public interface EnumMapper {
    int deleteByPrimaryKey(String id);

    int insert(Enum record);

    int insertSelective(Enum record);

    Enum selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Enum record);

    int updateByPrimaryKey(Enum record);
}