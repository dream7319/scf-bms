package com.lierl.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lierl.entity.User;
import com.lierl.mapper.UserMapper;
import com.lierl.service.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lierl on 2017/6/25.
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements IUserService{

    @Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly = true)
    public List<User> getAllUsers() {
        return baseMapper.getAllUsers();
    }
}
