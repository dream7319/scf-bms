package com.lierl.api.service.impl;

import com.lierl.api.entity.Resource;
import com.lierl.api.mapper.ResourceMapper;
import com.lierl.api.service.IResourceService;
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
public class ResourceServiceImpl extends BaseServiceImpl<ResourceMapper, Resource> implements IResourceService {


	@Transactional
	public Integer insertResource(Resource resource) throws Exception{
		return baseMapper.insert(resource);
	}

	@Transactional
	public Integer updateResourceById(Resource resource) throws Exception{
		return baseMapper.updateById(resource);
	}

	@Transactional
	public Integer deleteResourceById(Serializable id) throws Exception{
		return baseMapper.deleteById(id);
	}

	@Transactional
	public Integer deleteResourceByIds(List<Serializable> ids) throws Exception{
		return baseMapper.deleteBatchIds(ids);
	}
}
