package com.lierl.api.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.google.common.collect.Maps;
import com.lierl.api.common.Constants;
import com.lierl.api.entity.User;
import com.lierl.api.service.IMenuService;
import com.lierl.api.service.IUserService;
import com.lierl.api.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
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
    public Map<String,Object> login(@RequestBody User user, HttpSession session){
        int status = 500;
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
            results.put("status",500);
            results.put("message","用户名或密码错误");
            return results;
        }
        Boolean userStatus = u.getUserStatus();

        if(!userStatus){
            results.put("status",202);
            results.put("message","用户已被禁用");
            return results;
        }

        Boolean deleteFlag = u.getDeleteFlag();
        if(deleteFlag){
            results.put("status",203);
            results.put("message","此用户已被删除");
            return results;
        }

        if(u == null){
            results.put("status",500);
            results.put("message","用户名或密码错误");
            return results;
        }

        if(!u.getPassword().equals(Utils.hashHmac(user.getPassword(),Utils.SECRET))){
            results.put("status",500);
            results.put("message","用户名或密码错误");
            return results;
        }

        session.setAttribute(Constants.CURRENT_USER,u);
        session.setAttribute(Constants.TOKEN,Utils.hashHmac(u.getPassword(),Utils.SECRET));

        results.put("status",200);
        results.put("data",u);
        return results;
    }

    @GetMapping("/error")
    public String error(){
        return "error";
    }
}
