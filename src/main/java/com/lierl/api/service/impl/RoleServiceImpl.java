package com.lierl.api.service.impl;

import com.lierl.api.entity.Role;
import com.lierl.api.mapper.RoleMapper;
import com.lierl.api.service.IRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
/**
 *
 * @author lierl
 * @since 2017-08-01
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<RoleMapper, Role> implements IRoleService {


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
		return baseMapper.deleteById(id);
	}

	@Transactional
	public Integer deleteRoleByIds(List<Serializable> ids) throws Exception{
		return baseMapper.deleteBatchIds(ids);
	}
}
