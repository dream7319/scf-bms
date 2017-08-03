package com.lierl.api.service;

import com.lierl.api.entity.UserRole;
import java.io.Serializable;

import java.util.List;

/**
 *
 * @author lierl
 * @since 2017-08-03
 */
public interface IUserRoleService extends IBaseService<UserRole> {

	Integer insertUserRole(UserRole entity) throws Exception;

	Integer updateUserRoleById(UserRole entity) throws Exception;

	Integer deleteUserRoleById(Serializable id) throws Exception;

	Integer deleteUserRoleByIds(List<Serializable> ids) throws Exception;
}
