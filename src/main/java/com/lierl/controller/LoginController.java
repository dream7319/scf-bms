package com.lierl.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.lierl.base.JSONResult;
import com.lierl.common.Constants;
import com.lierl.entity.User;
import com.lierl.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * Created by lierl on 2017/7/2.
 */
@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private IUserService userService;

    @PostMapping("/login")
    public JSONResult login(@RequestBody User user, HttpSession session){
        JSONResult result = new JSONResult();
        //1、根据用户名查找用户是否存在
        if(user == null){
            result.setStatus(201);
            result.setMessage("传递参数有误");
            return result;
        }

        Wrapper<User> conditions = new EntityWrapper();
        conditions.where("username={0}",user.getUsername());
        User u = userService.selectOne(conditions);

        Boolean userStatus = u.getUserStatus();

        if(!userStatus){
            result.setStatus(202);
            result.setMessage("用户已被禁用");
            return result;
        }

        if(u == null){
            result.setStatus(203);
            result.setMessage("用户名或密码错误");
            return result;
        }

//        if(!u.getPassword().equals(Utils.hashHmac(user.getPassword(),Utils.SECRET))){
//            result.setStatus(203);
//            result.setMessage("用户名或密码错误");
//            return result;
//        }

        session.setAttribute(Constants.CURRENT_USER,u);

//        session.setAttribute(Constants.TOKEN,Utils.hashHmac(u.getPassword(),Utils.SECRET));
        
        result.setStatus(200);
        result.setData(u);
        return result;
    }
}
