package com.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.annotation.PassToken;
import com.example.annotation.ReadDataSource;
import com.example.annotation.UserLoginToken;
import com.example.domain.User;
import com.example.domain.UserTable;
import com.example.mapper.UserMapper;
import com.example.mapper.UserTableMapper;
import com.example.service.TokenService;
import com.example.service.UserService.UserService;
import com.example.service.UserService.UserServiceImpl.UserImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;


@Controller
public class UserController {

    @Autowired
    @Qualifier("UserImpl")
    @Resource(name = "UserImpl")
    private UserService userService;


    @Autowired
    private TokenService tokenService;

    @UserLoginToken
    @ResponseBody
    @RequestMapping(value = "/getuser")
    public UserTable getUser(){
//        UserTable userTable = userTableMapper.selectByPrimaryKey(1);
//        return userTable;
        UserTable userTable = userService.GetUserTable(1);
        return userTable;
    }

    @UserLoginToken
    @RequestMapping("/AddUser")
    @ResponseBody
    public Boolean AddUser(User user){
        System.out.println(user);
        int i = userService.AddUser(user);
        if (i>0)
            return true;
        return false;
    }


    @ResponseBody
    @RequestMapping("/login")
    public Object login(User user, HttpServletResponse response){
        JSONObject jsonObject=new JSONObject();
        System.out.println(user);
        User userForBase = userService.Login(user.getId());

        if(userForBase==null){
            jsonObject.put("message","登录失败,用户不存在");
            return jsonObject;
        }else {
            if (!userForBase.getPassword().equals(user.getPassword())){
                jsonObject.put("message","登录失败,密码错误");
                return jsonObject;
            }else {
                String token = tokenService.getToken(userForBase);
                jsonObject.put("token", token);

                Cookie cookie = new Cookie("token", token);
                cookie.setPath("/");
                response.addCookie(cookie);

                return jsonObject;
            }
        }
    }


}
