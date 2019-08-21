package com.my.world.securitymanagement.user.test.mapper;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.*;
import org.junit.After;
import org.junit.Before;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;

/**
 * @program: MyWorld
 * @description: maper测试基础类
 * @author: xue chi
 * @create: 2019-08-16 10:01
 **/
public class BaseMapperTest {

    SqlSessionFactory sqlSessionFactory;

    SqlSession sqlSession;

    @Before
    public void init() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis/mybatis-config-test.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
   }

   @After
   public void destroy(){
        if(sqlSession!=null){
            sqlSession.close();
        }
   }

}
