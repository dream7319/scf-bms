package com.lierl.api.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.google.common.collect.Maps;
import com.lierl.api.common.Constants;
import com.lierl.api.entity.User;
import com.lierl.api.service.IMenuService;
import com.lierl.api.service.IUserService;
import com.lierl.api.util.Utils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Created by lierl on 2017/7/2.
 */
@RestController
public class LoginController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IMenuService menuService;

    @PostMapping("/api/login")
    public Map<String,Object> login(@RequestBody User user, HttpServletResponse response, HttpSession session){
        Map<String,Object> results = Maps.newHashMap();
        //1、根据用户名查找用户是否存在
        if(user == null){
            results.put("status",201);
            results.put("message","传递参数有误");
            return results;
        }

        Wrapper<User> conditions = new EntityWrapper();
        conditions.where("username={0}",user.getUsername());
        User u = userService.selectOne(conditions);

        if(u == null){
            results.put("status",201);
            results.put("message","用户名或密码错误");
            return results;
        }
        Boolean userStatus = u.getUserStatus();

        if(!userStatus){
            results.put("status",201);
            results.put("message","用户已被禁用");
            return results;
        }

        if(StringUtils.isNotEmpty(user.getToken())){//登录过
            if(!Utils.hashHmac(u.getPassword(),Utils.SECRET).equals(user.getToken())){
                results.put("status",201);
                results.put("message","用户名或密码错误");
                return results;
            }

        }else if(!u.getPassword().equals(Utils.hashHmac(user.getPassword(),Utils.SECRET))){
            results.put("status",201);
            results.put("message","用户名或密码错误");
            return results;
        }

        session.setAttribute(Constants.CURRENT_USER,u);

        results.put("token",Utils.hashHmac(u.getPassword(),Utils.SECRET));
        results.put("status",200);
        results.put("username",u.getUsername());
//        results.put("user",u);
        results.put("id",u.getId());

//        Cookie cookie = new Cookie("miao","miao");
//        cookie.setMaxAge(60*30);
//        response.addCookie(cookie);
        return results;
    }

    @GetMapping("/api/test")
    public Map<String,Object> getUser(@RequestParam String id){
        Map<String,Object> results = Maps.newHashMap();
        if(StringUtils.isNotEmpty(id)){
            User user = userService.selectById(id);
            results.put("user",user);
        }else{
            try {
                List<User> users = userService.getAllUsers();
                results.put("users",users);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return results;
    }
}
