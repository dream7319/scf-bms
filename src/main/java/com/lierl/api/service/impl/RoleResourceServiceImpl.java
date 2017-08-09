package com.lierl.api.service.impl;

import com.lierl.api.entity.RoleResource;
import com.lierl.api.mapper.RoleResourceMapper;
import com.lierl.api.service.IRoleResourceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
/**
 *
 * @author lierl
 * @since 2017-08-07
 */
@Service
public class RoleResourceServiceImpl extends BaseServiceImpl<RoleResourceMapper, RoleResource> implements IRoleResourceService {


	@Transactional
	public Integer insertRoleResource(RoleResource roleResource) throws Exception{
		return baseMapper.insert(roleResource);
	}

	@Transactional
	public Integer updateRoleResourceById(RoleResource roleResource) throws Exception{
		return baseMapper.updateById(roleResource);
	}

	@Transactional
	public Integer deleteRoleResourceById(Serializable id) throws Exception{
		return baseMapper.deleteById(id);
	}

	@Transactional
	public Integer deleteRoleResourceByIds(List<Serializable> ids) throws Exception{
		return baseMapper.deleteBatchIds(ids);
	}
}
