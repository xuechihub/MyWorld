package com.my.world.securitymanagement.api.user.mapper;

import com.my.world.securitymanagement.api.user.po.Enum;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnumMapper {

    List<Enum> getList();

    int insert(Enum e);
}