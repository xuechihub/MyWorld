package com.my.world.securitymanagement.user.test.mapper;

import com.my.world.securitymanagement.user.mapper.EnumMapper;
import com.my.world.securitymanagement.user.mapper.UserMapper;
import com.my.world.securitymanagement.user.po.User;
import com.my.world.securitymanagement.user.vo.RequestQueryCondition;
import com.my.world.securitymanagement.user.vo.SearchCondition;
import com.my.world.securitymanagement.user.vo.SortCondition;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: MyWorld
 * @description: 用户mapper测试类
 * @author: xue chi
 * @create: 2019-08-15 19:52
 **/
@RunWith(SpringJUnit4ClassRunner.class)
public class UserMapperTest extends BaseMapperTest {
    private UserMapper userMapper;

    @Before
    public void init() throws IOException {
        super.init();
        sqlSession = sqlSessionFactory.openSession();
        userMapper = sqlSession.getMapper(UserMapper.class);
    }

    @Test
    public void getUserTest() {
        User user = userMapper.selectById("2b17abe1972511e9bdeb8c1645bb7fe9");
        Assert.assertEquals("admin", user.getUserName());
    }

    @Test
    public void getListTest() {
        RequestQueryCondition queryCondition = new RequestQueryCondition();
        queryCondition.setCurrentPage(1);
        queryCondition.setPageSize(10);
        List<SortCondition> sortConditions = new ArrayList<>();
        SortCondition sortCondition = new SortCondition("userName", false);
        sortConditions.add(sortCondition);
        queryCondition.setSortConditions(sortConditions);
        List<SearchCondition> searchConditions = new ArrayList<>();
        SearchCondition searchCondition = new SearchCondition("userName", true, "ad");
        searchConditions.add(searchCondition);
        queryCondition.setSearchConditions(searchConditions);
        List<User> userList = userMapper.getList(queryCondition);
        Assert.assertTrue(userList != null && userList.size() > 0);
    }
}
