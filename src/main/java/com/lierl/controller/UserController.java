package com.lierl.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.lierl.entity.User;
import com.lierl.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by lierl on 2017/6/25.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/lists")
    public List<User> showUsers(){
        List<User> users = userService.getAllUsers();
        users.forEach(user->{
            System.out.println(user);
        });
        return users;
    }

    @GetMapping("/selectPage")
    public Page<User> selectPage(){
        return userService.selectPage(new Page<User>(0,1));
    }

    @GetMapping("/add")
    public User addUser(){
        User user = new User();
        try {
            user.setName("lierl1111");
            user.setAge(28);
            user.setUserType("1");
            userService.insert(user);
            throw new RuntimeException("保存异常");
        }catch (Exception e){
            e.printStackTrace();
        }

        return user;
    }

    public User test(){
        System.err.println("删除一条数据：" + userService.deleteById(1));

        Page<User> userListPage = userService.selectPage(new Page<User>(1,5), new EntityWrapper<User>(new User()));
        System.err.println("total="+userListPage.getTotal()+", current list size="+userListPage.getRecords().size());
        return userService.selectById(1L);
    }
}
