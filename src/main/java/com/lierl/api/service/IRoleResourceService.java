package com.lierl.api.service;

import com.lierl.api.entity.RoleResource;
import java.io.Serializable;

import java.util.List;

/**
 *
 * @author lierl
 * @since 2017-08-07
 */
public interface IRoleResourceService extends IBaseService<RoleResource> {

	Integer insertRoleResource(RoleResource entity) throws Exception;

	Integer updateRoleResourceById(RoleResource entity) throws Exception;

	Integer deleteRoleResourceById(Serializable id) throws Exception;

	Integer deleteRoleResourceByIds(List<Serializable> ids) throws Exception;
}
