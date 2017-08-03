package com.lierl.api.service.impl;

import com.lierl.api.entity.UserRole;
import com.lierl.api.mapper.UserRoleMapper;
import com.lierl.api.service.IUserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
/**
 *
 * @author lierl
 * @since 2017-08-03
 */
@Service
public class UserRoleServiceImpl extends BaseServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {


	@Transactional
	public Integer insertUserRole(UserRole userRole) throws Exception{
		return baseMapper.insert(userRole);
	}

	@Transactional
	public Integer updateUserRoleById(UserRole userRole) throws Exception{
		return baseMapper.updateById(userRole);
	}

	@Transactional
	public Integer deleteUserRoleById(Serializable id) throws Exception{
		return baseMapper.deleteById(id);
	}

	@Transactional
	public Integer deleteUserRoleByIds(List<Serializable> ids) throws Exception{
		return baseMapper.deleteBatchIds(ids);
	}
}
