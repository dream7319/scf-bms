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

        /*
         * 实体带查询使用方法  输出看结果
        EntityWrapper<User> ew = new EntityWrapper<User>();
        ew.setEntity(new User(1));
        ew.where("user_name={0}", "'zhangsan'").and("id=1")
                .orNew("user_status={0}", "0").or("status=1")
                .notLike("user_nickname", "notvalue")
                .andNew("new=xx").like("hhh", "ddd")
                .andNew("pwd=11").isNotNull("n1,n2").isNull("n3")
                .groupBy("x1").groupBy("x2,x3")
                .having("x1=11").having("x3=433")
                .orderBy("dd").orderBy("d1,d2");
         */
        Wrapper<User> conditions = new EntityWrapper();
        conditions.where("username={0}",user.getUsername());
        User u = userService.selectOne(conditions);

        Boolean userStatus = u.getUserStatus();

        if(!userStatus){
            result.setStatus(202);
            result.setMessage("用户已被禁用");
            return result;
        }

        Boolean deleteFlag = u.getDeleteFlag();
        if(!deleteFlag){
            result.setStatus(203);
            result.setMessage("此用户已被删除");
            return result;
        }

        if(u == null){
            result.setStatus(204);
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
