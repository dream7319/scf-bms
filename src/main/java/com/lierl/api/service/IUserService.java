package com.lierl.api.service;


import com.lierl.api.entity.User;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lierl on 2017/6/25.
 */
public interface IUserService extends IBaseService<User> {
    List<User> getAllUsers() throws Exception;
    Integer insertUser(User entity) throws Exception;
    Integer updateUserById(User entity) throws Exception;
    Integer deleteUserById(Serializable id) throws Exception;
    Integer deleteUserByIds(List<Serializable> ids) throws Exception;
}
