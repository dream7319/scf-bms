package com.lierl.api.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.lierl.api.entity.Resource;
import org.apache.ibatis.annotations.Select;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author lierl
 * @since 2017-08-03
 */
public interface ResourceMapper extends BaseMapper<Resource> {

	@Select("select " +
			"resource.id," +
			"resource.resource_name," +
			"menu.menu_name as menuName," +
			"resource.create_time," +
			"resource.resource_url," +
			"resource.resource_status " +
			"from scf_resource resource " +
			"left join scf_menu menu " +
			"on resource.menu_id = menu.id order by resource.menu_id")
	public List<Resource> getAllResources(Pagination page);

	@Select("select " +
			"resource.id," +
			"resource.resource_name," +
			"menu.menu_name as menuName," +
			"resource.create_time," +
			"resource.resource_url," +
			"resource.resource_status " +
			"from scf_resource resource " +
			"left join scf_menu menu " +
			"on resource.menu_id = menu.id " +
			"where resource.id=#{id}")
	public Resource selectResourceById(Serializable id);
}