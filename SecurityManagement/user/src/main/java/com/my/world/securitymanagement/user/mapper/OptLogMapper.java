package com.my.world.securitymanagement.user.mapper;

import com.my.world.securitymanagement.user.po.OptLog;

public interface OptLogMapper {
    int deleteByPrimaryKey(String id);

    int insert(OptLog record);

    int insertSelective(OptLog record);

    OptLog selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(OptLog record);

    int updateByPrimaryKey(OptLog record);
}