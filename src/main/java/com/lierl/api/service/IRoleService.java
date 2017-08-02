package com.lierl.api.service;

import com.lierl.api.entity.Role;
import java.io.Serializable;

import java.util.List;

/**
 *
 * @author lierl
 * @since 2017-08-02
 */
public interface IRoleService extends IBaseService<Role> {

	Integer insertRole(Role entity) throws Exception;

	Integer updateRoleById(Role entity) throws Exception;

	Integer deleteRoleById(Serializable id) throws Exception;

	Integer deleteRoleByIds(List<Serializable> ids) throws Exception;
}
