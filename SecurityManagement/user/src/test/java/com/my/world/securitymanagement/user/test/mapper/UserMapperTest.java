package com.my.world.securitymanagement.user.test.mapper;

import com.my.world.securitymanagement.user.mapper.UserMapper;
import com.my.world.securitymanagement.user.po.User;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @program: MyWorld
 * @description: 用户mapper测试类
 * @author: xue chi
 * @create: 2019-08-15 19:52
 **/
@RunWith(SpringJUnit4ClassRunner.class)
public class UserMapperTest extends BaseMapperTest{
    @Test
    public void getUserTest(){
        sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.selectById("2b17abe1972511e9bdeb8c1645bb7fe9");
        Assert.assertEquals("admin", user.getUserName());
    }
}
