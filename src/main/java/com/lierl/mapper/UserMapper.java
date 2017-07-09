package com.lierl.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.lierl.entity.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by lierl on 2017/6/25.
 */
public interface UserMapper extends BaseMapper<User>{

//    int deleteAll();

    @Select("select " +
            "id, username, age, delete_flag,create_time,birthday,password,user_status," +
            "name,sex,email,telphone,photo,user_type,update_time"+
            " from user")
    public List<User> getAllUsers();
}
