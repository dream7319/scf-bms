package com.lierl.api.service.impl;

import com.lierl.api.entity.RoleMenu;
import com.lierl.api.mapper.RoleMenuMapper;
import com.lierl.api.service.IRoleMenuService;
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
public class RoleMenuServiceImpl extends BaseServiceImpl<RoleMenuMapper, RoleMenu> implements IRoleMenuService {


	@Transactional
	public Integer insertRoleMenu(RoleMenu roleMenu) throws Exception{
		return baseMapper.insert(roleMenu);
	}

	@Transactional
	public Integer updateRoleMenuById(RoleMenu roleMenu) throws Exception{
		return baseMapper.updateById(roleMenu);
	}

	@Transactional
	public Integer deleteRoleMenuById(Serializable id) throws Exception{
		return baseMapper.deleteById(id);
	}

	@Transactional
	public Integer deleteRoleMenuByIds(List<Serializable> ids) throws Exception{
		return baseMapper.deleteBatchIds(ids);
	}
}
