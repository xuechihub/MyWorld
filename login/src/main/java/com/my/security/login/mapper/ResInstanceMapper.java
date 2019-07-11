package com.my.security.login.mapper;

import com.my.security.login.po.ResInstance;

public interface ResInstanceMapper {
    int deleteByPrimaryKey(String id);

    int insert(ResInstance record);

    int insertSelective(ResInstance record);

    ResInstance selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ResInstance record);

    int updateByPrimaryKey(ResInstance record);
}