package com.lierl.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lierl.entity.User;
import com.lierl.mapper.UserMapper;
import com.lierl.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lierl on 2017/6/25.
 */
@Service
public class UserServiceImpl implements IUserService{

    @Autowired
    private UserMapper userMapper;

    public List<User> getAllUsers() {
        return userMapper.getAllUsers();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void insert(User entity) throws Exception{
        userMapper.insert(entity);
        throw new RuntimeException("抛出异常");
    }
}
