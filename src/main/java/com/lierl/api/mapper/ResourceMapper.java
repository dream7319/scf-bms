package com.lierl.api.mapper;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.lierl.api.entity.Resource;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

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
			"on resource.menu_id = menu.id ")
	public List<Resource> getAllResources(Pagination page);
}