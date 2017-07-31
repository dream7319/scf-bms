package com.lierl.api.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.common.collect.Maps;
import com.lierl.api.base.ResponseData;
import com.lierl.api.entity.User;
import com.lierl.api.service.IUserService;
import com.lierl.api.util.Utils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

/**
 * Created by lierl on 2017/6/25.
 */
@RestController
@RequestMapping("/api")
public class UserController {

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IUserService userService;

    @GetMapping("/user")
    public Map<String,Object> getUserById(@RequestParam Integer id){
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

    @PostMapping("/user/add")
    public Map<String,Object> addUser(@RequestBody User user){
        Map<String,Object> results = Maps.newHashMap();
        results.put("result","error");
        try {
            user.setPassword(Utils.hashHmac(user.getPassword(),Utils.SECRET));
            user.setCreateTime(new Date());
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

    @GetMapping("/user/list")
    public Map<String,Object> queryUsers(@RequestParam(value="pageNum",defaultValue = "1") Integer pageNum,
                                   @RequestParam(value="pageSize",defaultValue = "10") Integer pageSize){
        Map<String,Object> results = Maps.newHashMap();
        EntityWrapper<User> wrapper = new EntityWrapper<User>();
//        userService.selectList()
        Page<User> pages = userService.selectPage(new Page<User>(pageNum, pageSize));
        results.put("data",new ResponseData<User>(pages));
        return results;
    }

    @DeleteMapping("/user/delete/{id}")
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

    @PutMapping("/user/update")
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
