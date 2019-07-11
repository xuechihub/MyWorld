package com.my.security.login.controller;

import com.my.security.login.consts.MetaDataConsts;
import com.my.security.login.mapper.UserMapper;
import com.my.security.login.po.User;
import com.my.security.login.po.UserExample;
import com.my.security.login.utils.YmlUtils;
import com.my.security.login.vo.SecurityMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class HelloWorldController {
    @Autowired
    private SecurityMenu securityMenu;
    @Autowired
    private UserMapper userMapper;
    @RequestMapping("/hello")
    public Object index() {
//        User user = new User();
//        user.setUserName("admin2");
//        user.setPassword("admin");
//        user.setUserType(1);
//        user.setLastIp("127.0.0.1");
//        user.setLastVisit(new Date());
//        user.setCreateTime(new Date());
//        userMapper.insert(user);
        List<User> users = userMapper.selectByExample(new UserExample());
        Object security = YmlUtils.convertToMap(MetaDataConsts.HEADER_MENU_PATH);
        return users;
    }



    @RequestMapping("/test/cookie")
    public String cookie(@RequestParam("browser") String browser, HttpServletRequest request, HttpSession session) {
        //取出session中的browser
        Object sessionBrowser = session.getAttribute("browser");
        if (sessionBrowser == null) {
            System.out.println("不存在session，设置browser=" + browser);
            session.setAttribute("browser", browser);
        } else {
            System.out.println("存在session，browser=" + sessionBrowser.toString());
        }
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                System.out.println(cookie.getName() + " : " + cookie.getValue());
            }
        }
        return "index";
    }
}
