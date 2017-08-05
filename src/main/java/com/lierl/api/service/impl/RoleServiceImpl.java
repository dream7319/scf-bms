package com.lierl.api.service.impl;

import com.google.common.collect.Maps;
import com.lierl.api.entity.Role;
import com.lierl.api.mapper.RoleMapper;
import com.lierl.api.service.IRoleMenuService;
import com.lierl.api.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 *
 * @author lierl
 * @since 2017-08-02
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<RoleMapper, Role> implements IRoleService {

	@Autowired
	IRoleMenuService roleMenuService;

	@Transactional
	public Integer insertRole(Role role) throws Exception{
		return baseMapper.insert(role);
	}

	@Transactional
	public Integer updateRoleById(Role role) throws Exception{
		return baseMapper.updateById(role);
	}

	@Transactional
	public Integer deleteRoleById(Serializable id) throws Exception{
		int num = baseMapper.deleteById(id);
		Map<String,Object> params = Maps.newHashMap();
		params.put("role_id",id);
		boolean b = roleMenuService.deleteByMap(params);
		return b && num > 0 ? 1 : 0;
	}

	@Transactional
	public Integer deleteRoleByIds(List<Serializable> ids) throws Exception{
		return baseMapper.deleteBatchIds(ids);
	}

	@Override
	public List<Role> selectRolesByUserId(Integer userId) {
		return baseMapper.selectRolesByUserId(userId);
	}
}
