package com.lierl.service;

import com.lierl.entity.User;

import java.util.List;

/**
 * Created by lierl on 2017/6/25.
 */
public interface IUserService extends IBaseService<User>{
    public List<User> getAllUsers();
    public Integer insertUser(User entity) throws Exception;
}
