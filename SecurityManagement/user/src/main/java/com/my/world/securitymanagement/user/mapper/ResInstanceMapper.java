package com.my.world.securitymanagement.user.mapper;

import com.my.world.securitymanagement.user.po.ResInstance;

public interface ResInstanceMapper {
    int deleteByPrimaryKey(String id);

    int insert(ResInstance record);

    int insertSelective(ResInstance record);

    ResInstance selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ResInstance record);

    int updateByPrimaryKey(ResInstance record);
}