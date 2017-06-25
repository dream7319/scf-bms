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
        return userService.getAllUsers();
    }

    @GetMapping("/selectPage")
    public Page<User> selectPage(){
        return userService.selectPage(new Page<User>(0,1));
    }

    @GetMapping("/add")
    public User addUser(){
        User user = new User("hello",12,"hello world");
        userService.insert(user);
        return user;
    }

    public User test(){
        System.err.println("删除一条数据：" + userService.deleteById(1));
        System.err.println("deleteAll：" + userService.deleteAll());
        System.err.println("插入一条数据：" + userService.insert(new User( "张三", 17, "test")));
        User user = new User("张三", 17, "hello");
        boolean result = userService.insert(user);
        // 自动回写的ID
        Integer id = user.getId();
        System.err.println("插入一条数据：" + result + ", 插入信息：" + user.toString());
        System.err.println("查询：" + userService.selectById(id).toString());
        System.err.println("更新一条数据：" + userService.updateById(new User("三毛", 18, "2")));
        for(int i=0;i<5;++i){
            userService.insert(new User( "张三"+i, 17+i, ""+i));
        }
        Page<User> userListPage = userService.selectPage(new Page<User>(1,5), new EntityWrapper<User>(new User()));
        System.err.println("total="+userListPage.getTotal()+", current list size="+userListPage.getRecords().size());
        return userService.selectById(1L);
    }
}
