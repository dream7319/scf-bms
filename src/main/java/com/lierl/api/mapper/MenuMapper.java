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
	List<Menu> getAllMenus(Pagination page);

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
	Menu selectMenuById(Integer id);

	@Select("select " +
			"menu.id," +
			"menu.menu_name," +
			"menu.parent_id," +
			"case when menu.id=rm.menu_id then 1 else 0 end checked " +
			"from scf_menu menu " +
			"left join scf_role_menu rm " +
			"on menu.id = rm.menu_id and rm.role_id=#{roleId}")
	List<Menu> selectMenusByRoleId(Integer roleId);
}