package com.my.world.securitymanagement.user.test.controller;

import com.my.world.securitymanagement.user.test.TestApplication;
import com.my.world.securitymanagement.user.vo.TableViewResult;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @program: MyWorld
 * @description: 自定义枚举控制器测试类
 * @author: xue chi
 * @create: 2019-08-16 15:21
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EnumControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getEnumListTest(){
        try {
            TableViewResult object = restTemplate.getForObject("http://localhost:" + port + "/rest/enum", TableViewResult.class);
            Assert.assertNotNull(object);
        }catch (Exception e){
            e.printStackTrace();
            Assert.fail();
        }
    }
}
