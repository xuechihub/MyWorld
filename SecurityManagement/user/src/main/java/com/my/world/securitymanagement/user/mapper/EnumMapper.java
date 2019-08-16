package com.my.world.securitymanagement.user.mapper;

import com.my.world.securitymanagement.user.po.Enum;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnumMapper {

    List<Enum> getList();

    int insert(Enum e);
}