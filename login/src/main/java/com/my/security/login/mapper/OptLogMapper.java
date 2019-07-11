package com.my.security.login.mapper;

import com.my.security.login.po.OptLog;

public interface OptLogMapper {
    int deleteByPrimaryKey(String id);

    int insert(OptLog record);

    int insertSelective(OptLog record);

    OptLog selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(OptLog record);

    int updateByPrimaryKey(OptLog record);
}