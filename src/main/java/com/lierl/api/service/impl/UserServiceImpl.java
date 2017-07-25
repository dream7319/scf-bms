package com.lierl.api.service.impl;

import com.lierl.api.entity.User;
import com.lierl.api.mapper.UserMapper;
import com.lierl.api.service.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lierl on 2017/6/25.
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper,User> implements IUserService {

    @Transactional(readOnly = true)
    public List<User> getAllUsers() throws Exception{
        return baseMapper.getAllUsers();
    }

    @Transactional
    public Integer insertUser(User entity) throws Exception{
        return baseMapper.insert(entity);
    }

    @Transactional
    public Integer updateUserById(User entity) throws Exception{
        return baseMapper.updateById(entity);
    }

    @Transactional
    public Integer deleteUserById(Serializable id) throws Exception{
        return baseMapper.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Integer deleteUserByIds(List<Serializable> ids) throws Exception{
        return baseMapper.deleteBatchIds(ids);
    }
}
