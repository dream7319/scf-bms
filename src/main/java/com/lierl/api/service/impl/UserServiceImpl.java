package com.lierl.api.service.impl;

import com.google.common.collect.Maps;
import com.lierl.api.entity.User;
import com.lierl.api.mapper.UserMapper;
import com.lierl.api.service.IUserRoleService;
import com.lierl.api.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by lierl on 2017/6/25.
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper,User> implements IUserService {

    @Autowired
    IUserRoleService userRoleService;

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
        Integer num = baseMapper.deleteById(id);//删除用户
        Map<String,Object> params = Maps.newHashMap();
        params.put("user_id",id);
        boolean bool = userRoleService.deleteByMap(params);//删除此用户对应的角色

        return bool && num > 0 ? 1 : 0;
    }

    @Transactional(readOnly = true)
    public Integer deleteUserByIds(List<Serializable> ids) throws Exception{
        return baseMapper.deleteBatchIds(ids);
    }
}
