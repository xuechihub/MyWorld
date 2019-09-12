package com.my.world.securitymanagement.api.user.mapper;

import com.my.world.securitymanagement.api.user.po.ResInstance;

public interface ResInstanceMapper {
    int deleteByPrimaryKey(String id);

    int insert(ResInstance record);

    int insertSelective(ResInstance record);

    ResInstance selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ResInstance record);

    int updateByPrimaryKey(ResInstance record);
}