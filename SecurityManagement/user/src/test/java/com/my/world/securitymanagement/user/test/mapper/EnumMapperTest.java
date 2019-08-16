package com.my.world.securitymanagement.user.test.mapper;

import com.my.world.securitymanagement.user.mapper.EnumMapper;
import com.my.world.securitymanagement.user.po.Enum;
import com.my.world.securitymanagement.user.vo.EnumMap;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: MyWorld
 * @description: 枚举mapper测试类
 * @author: xue chi
 * @create: 2019-08-15 19:52
 **/
@RunWith(SpringJUnit4ClassRunner.class)
public class EnumMapperTest extends BaseMapperTest{

    private EnumMapper enumMapper;

    @Before
    public void init() throws IOException {
        super.init();
        sqlSession = sqlSessionFactory.openSession();
        enumMapper = sqlSession.getMapper(EnumMapper.class);
    }

    @Test
    public void getEnumTest(){
        List<Enum> list = enumMapper.getList();
        System.out.println(list);
    }

    @Test
    public void insertTest(){
        Enum e = new Enum();
        e.setCode("UserState");
        e.setName("用户状态");
        List<EnumMap> list = new ArrayList<>();
        list.add(new EnumMap("在线",1));
        list.add(new EnumMap("离线",2));
        e.setItems(list);
        enumMapper.insert(e);
        sqlSession.commit();

    }
}
