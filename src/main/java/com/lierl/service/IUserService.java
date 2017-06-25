package com.lierl.service;

import com.baomidou.mybatisplus.service.IService;
import com.lierl.entity.User;

import java.util.List;

/**
 * Created by lierl on 2017/6/25.
 */
public interface IUserService extends IService<User>{
    boolean deleteAll();
    public List<User> getAllUsers();
}
