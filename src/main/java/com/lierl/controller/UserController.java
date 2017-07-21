package com.lierl.controller;

import com.google.common.collect.Maps;
import com.lierl.entity.User;
import com.lierl.service.IUserService;
import com.lierl.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by lierl on 2017/6/25.
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/lists")
    public List<User> showUsers(){
        List<User> users = userService.getAllUsers();
        return users;
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
        }
        return results;
    }

//    public User test(){
//        System.err.println("删除一条数据：" + userService.deleteById(1));
//
//        Page<User> userListPage = userService.selectPage(new Page<User>(1,5), new EntityWrapper<User>(new User()));
//        System.err.println("total="+userListPage.getTotal()+", current list size="+userListPage.getRecords().size());
//        return userService.selectById(1L);
//    }
}
