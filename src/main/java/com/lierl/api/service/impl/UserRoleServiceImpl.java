package com.lierl.api.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.google.common.collect.Lists;
import com.lierl.api.entity.UserRole;
import com.lierl.api.mapper.UserRoleMapper;
import com.lierl.api.service.IUserRoleService;
import org.apache.commons.lang.StringUtils;
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
public class UserRoleServiceImpl extends BaseServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {


	@Transactional
	public Integer insertUserRole(UserRole userRole) throws Exception{
		return baseMapper.insert(userRole);
	}

	@Transactional
	public Integer updateUserRoleById(UserRole userRole) throws Exception{
		return baseMapper.updateById(userRole);
	}

	@Transactional
	public Integer deleteUserRoleById(Serializable id) throws Exception{
		return baseMapper.deleteById(id);
	}

	@Transactional
	public Integer deleteUserRoleByIds(List<Serializable> ids) throws Exception{
		return baseMapper.deleteBatchIds(ids);
	}

	@Transactional
	public Boolean addOrUpdate(String ids, String userId) throws Exception{
		if(StringUtils.isNotEmpty(ids) && StringUtils.isNotEmpty(userId)){
			//根据用户查询
			//先删除,再插入
			Wrapper<UserRole> condition = new EntityWrapper<>();
			condition.where("user_id={0}",userId);
			List<UserRole> userRoles = super.selectList(condition);

			boolean deleteFlag = false;

			if(userRoles!=null && userRoles.size()>0){
				List<Integer> urIds = Lists.newArrayList();
				for (UserRole userRole : userRoles) {
					urIds.add(userRole.getId());
				}

				deleteFlag = super.deleteBatchIds(urIds);
			}else{
				deleteFlag = true;
			}

			String roleIds[] = ids.split(",");
			List<UserRole> urs = Lists.newArrayList();
			for (String roleId : roleIds) {
				UserRole ur = new UserRole();
				ur.setUserId(Integer.valueOf(userId));
				ur.setRoleId(Integer.valueOf(roleId));
				urs.add(ur);
			}
			boolean insertFlag = super.insertBatch(urs);

			return deleteFlag && insertFlag;
		}
		return false;
	}
}
