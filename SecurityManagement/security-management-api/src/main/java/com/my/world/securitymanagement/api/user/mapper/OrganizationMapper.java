package com.my.world.securitymanagement.api.user.mapper;

import com.my.world.securitymanagement.api.user.po.Organization;

public interface OrganizationMapper {
    int deleteByPrimaryKey(String id);

    int insert(Organization record);

    int insertSelective(Organization record);

    Organization selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Organization record);

    int updateByPrimaryKey(Organization record);
}