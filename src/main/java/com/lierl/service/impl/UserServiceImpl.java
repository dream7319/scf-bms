package com.lierl.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lierl.entity.User;
import com.lierl.mapper.UserMapper;
import com.lierl.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lierl on 2017/6/25.
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements IUserService{

    @Autowired
    private UserMapper userMapper;

    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return baseMapper.getAllUsers();
    }

    @Transactional
    public void insertUser(User entity) throws Exception{
//        userMapper.insert(entity);
        baseMapper.insert(entity);
        throw new RuntimeException("抛出异常");
    }


}
