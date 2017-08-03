package com.lierl.api.mapper;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.lierl.api.entity.Menu;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 *
 * @author lierl
 * @since 2017-07-31
 */
public interface MenuMapper extends BaseMapper<Menu> {

	@Select("SELECT " +
			"m1.id," +
			"m1.menu_name," +
			"m1.menu_level," +
			"m1.menu_status," +
			"m1.create_time," +
			"m1.menu_url," +
			"m2.id as parentId," +
			"m2.menu_name as parentMenuName " +
			"FROM scf_menu m1 " +
			"left join scf_menu m2 " +
			"on m1.parent_id=m2.id")
//    @Options(statementType = StatementType.CALLABLE)
	public List<Menu> getAllMenus(Pagination page);

	@Select("SELECT " +
			"m1.id," +
			"m1.menu_name," +
			"m1.menu_level," +
			"m1.menu_status," +
			"m1.create_time," +
			"m1.menu_url," +
			"m2.id as parentId," +
			"m2.menu_name as parentMenuName " +
			"FROM scf_menu m1 " +
			"left join scf_menu m2 " +
			"on m1.parent_id=m2.id " +
			"where m1.id=#{id}")
	public Menu selectMenuById(Integer id);
}