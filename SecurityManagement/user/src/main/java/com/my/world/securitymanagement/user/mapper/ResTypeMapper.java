package com.my.world.securitymanagement.user.mapper;


import com.my.world.securitymanagement.user.po.ResType;

public interface ResTypeMapper {
    int deleteByPrimaryKey(String id);

    int insert(ResType record);

    int insertSelective(ResType record);

    ResType selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ResType record);

    int updateByPrimaryKey(ResType record);
}