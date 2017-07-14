package com.lierl.service;

import com.baomidou.mybatisplus.service.IService;
import com.lierl.entity.User;

import java.util.List;

/**
 * Created by lierl on 2017/6/25.
 */
public interface IUserService{
    public List<User> getAllUsers();

    public void insert(User entity) throws Exception;

}
