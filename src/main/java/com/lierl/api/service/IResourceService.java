package com.lierl.api.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.lierl.api.entity.Resource;
import java.io.Serializable;

import java.util.List;

/**
 *
 * @author lierl
 * @since 2017-08-03
 */
public interface IResourceService extends IBaseService<Resource> {

	Integer insertResource(Resource entity) throws Exception;

	Integer updateResourceById(Resource entity) throws Exception;

	Integer deleteResourceById(Serializable id) throws Exception;

	Integer deleteResourceByIds(List<Serializable> ids) throws Exception;

	Page<Resource> getAllResources(Page<Resource> page);

	Resource selectResourceById(Serializable id);
}
