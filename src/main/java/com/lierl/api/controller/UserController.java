package com.lierl.api.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.common.collect.Maps;
import com.lierl.api.entity.User;
import com.lierl.api.service.IUserService;
import com.lierl.api.util.Utils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by lierl on 2017/6/25.
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IUserService userService;

    @GetMapping("/{id}")
    public Map<String,Object> getUserById(@PathVariable Integer id){
        Map<String,Object> results = Maps.newHashMap();
        if(StringUtils.isNotEmpty(ObjectUtils.toString(id))){
            User user = userService.selectById(id);
            logger.info(user.toString());
            results.put("user",user);
        }else {
            results.put("user", new User());
        }
        return results;
    }

//    @GetMapping("/selectPage")
//    public Page<User> selectPage(){
//        return userService.selectPage(new Page<User>(0,1));
//    }

    @PostMapping("/add")
    public Map<String,Object> addUser(@RequestBody User user){
        Map<String,Object> results = Maps.newHashMap();
        results.put("result","error");
        try {
            user.setPassword(Utils.hashHmac(user.getPassword(),Utils.SECRET));
            Integer num = userService.insertUser(user);
            if(num > 0){
                results.put("result","success");
            }
        } catch (Exception e) {
            results.put("message","保存用户失败");
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return results;
    }

    @GetMapping("/test")
    public Map<String,Object> test(@RequestParam(value="username",defaultValue = "1") Integer username){
        Map<String,Object> results = Maps.newHashMap();
        EntityWrapper<User> wrapper = new EntityWrapper<User>();
        wrapper.addFilter("username={0}",username);
        Page<User> userListPage = userService.selectPage(new Page<User>(1, 5),wrapper);
        results.put("data",userListPage);
        return results;
    }

    @DeleteMapping("/delete/{id}")
    public Map<String,Object> deleteUserById(@PathVariable("id") Integer id){
        Map<String,Object> results = Maps.newHashMap();
        results.put("result","error");
        try {
            if(StringUtils.isNotEmpty(ObjectUtils.toString(id))){
                int num = userService.deleteUserById(id);
                if(num > 0){
                    results.put("result","success");
                }
            }
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return results;
    }

    @PutMapping("/update")
    public Map<String,Object> updateUserById(@RequestBody User user){
        Map<String,Object> results = Maps.newHashMap();
        results.put("result","error");
        try {
            if(user != null){
                int num = userService.updateUserById(user);
                if(num > 0){
                    results.put("result","success");
                }
            }
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return results;
    }


}
