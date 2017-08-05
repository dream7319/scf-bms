package com.lierl.api.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.lierl.api.entity.Role;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 *
 * @author lierl
 * @since 2017-08-02
 */
public interface RoleMapper extends BaseMapper<Role> {

	@Select("SELECT " +
			"role.id," +
			"role.role_name," +
			"case when role.id=ur.role_id then 1 else 0 end checked " +
			"FROM scf_role role " +
			"left join scf_user_role ur " +
			"on role.id = ur.role_id and ur.user_id=#{userId}")
	public List<Role> selectRolesByUserId(Integer userId);
}