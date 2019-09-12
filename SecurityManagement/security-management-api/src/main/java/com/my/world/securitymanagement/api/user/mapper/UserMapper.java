package com.my.world.securitymanagement.api.user.mapper;

import com.my.world.securitymanagement.api.user.po.User;
import com.my.world.securitymanagement.api.user.po.UserExample;

import java.util.List;

import com.my.world.common.rest.vo.RequestQueryCondition;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    List<User> getList(@Param("queryCondition") RequestQueryCondition queryCondition );

    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteById(String id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectById(String id);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByIdSelective(User record);

    int updateById(User record);
}