package com.lierl.api.service;

import com.lierl.api.entity.RoleMenu;
import java.io.Serializable;

import java.util.List;

/**
 *
 * @author lierl
 * @since 2017-08-03
 */
public interface IRoleMenuService extends IBaseService<RoleMenu> {

	Integer insertRoleMenu(RoleMenu entity) throws Exception;

	Integer updateRoleMenuById(RoleMenu entity) throws Exception;

	Integer deleteRoleMenuById(Serializable id) throws Exception;

	Integer deleteRoleMenuByIds(List<Serializable> ids) throws Exception;
}
