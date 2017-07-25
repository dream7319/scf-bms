package com.lierl.api.service.impl;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lierl.api.service.IBaseService;

/**
 * @author lierl
 * @create 2017-07-24 16:05
 **/
public class BaseServiceImpl<M extends BaseMapper<T>,T> extends ServiceImpl<M,T> implements IBaseService<T> {
}
