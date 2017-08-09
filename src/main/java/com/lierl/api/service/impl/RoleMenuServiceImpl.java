package com.lierl.api.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.lierl.api.entity.Resource;
import com.lierl.api.entity.RoleMenu;
import com.lierl.api.entity.RoleResource;
import com.lierl.api.mapper.RoleMenuMapper;
import com.lierl.api.service.IResourceService;
import com.lierl.api.service.IRoleMenuService;
import com.lierl.api.service.IRoleResourceService;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nullable;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author lierl
 * @since 2017-08-03
 */
@Service
public class RoleMenuServiceImpl extends BaseServiceImpl<RoleMenuMapper, RoleMenu> implements IRoleMenuService {

	@Autowired
	private IRoleResourceService roleResourceService;

	@Autowired
	private IResourceService resourceService;


	@Transactional
	public Integer insertRoleMenu(RoleMenu roleMenu) throws Exception{
		return baseMapper.insert(roleMenu);
	}

	@Transactional
	public Integer updateRoleMenuById(RoleMenu roleMenu) throws Exception{
		return baseMapper.updateById(roleMenu);
	}

	@Transactional
	public Integer deleteRoleMenuById(Serializable id) throws Exception{
		return baseMapper.deleteById(id);
	}

	@Transactional
	public Integer deleteRoleMenuByIds(List<Serializable> ids) throws Exception{
		return baseMapper.deleteBatchIds(ids);
	}

	@Override
	public Boolean addOrUpdate(String ids, String roleId) throws Exception {
		if(StringUtils.isNotEmpty(ids) && StringUtils.isNotEmpty(roleId)){
			//先删除
			Map<String,Object> params = Maps.newHashMap();
			params.put("role_id",roleId);
			List<RoleMenu> roleMenus = super.selectByMap(params);

			boolean deleteFlag = false;
			if(roleMenus!=null && roleMenus.size()>0){
				List<Integer> rmIds = Lists.newArrayList();
				for (RoleMenu roleMenu : roleMenus) {
					rmIds.add(roleMenu.getId());
				}
				deleteFlag = super.deleteBatchIds(rmIds);
			}else{
				deleteFlag = true;
			}

			String menusIds[] = ids.split(",");

			List<RoleMenu> rms = Lists.newArrayList();
			for (String menusId : menusIds) {
				RoleMenu rm = new RoleMenu();
				rm.setMenuId(Integer.valueOf(menusId));
				rm.setRoleId(Integer.valueOf(roleId));
				rms.add(rm);
			}


			//默认给此角色菜单下的所有资源权限
			Wrapper<Resource> wrapper = new EntityWrapper<Resource>();
			wrapper.in("menu_id",menusIds);
			List<Resource> resources = resourceService.selectList(wrapper);

			List<Integer> rIds = resources.stream().map(Resource::getId).collect(Collectors.toList());

			List<RoleResource> datas = Lists.transform(rIds, new Function<Integer, RoleResource>() {
				@Nullable
				@Override
				public RoleResource apply(@Nullable Integer input) {
					RoleResource rr = new RoleResource();
					rr.setResourceId(input);
					rr.setRoleId(Integer.valueOf(roleId));
					return rr;
				}
			});

			boolean batchInsertFlag = true;

			if(datas!=null && datas.size()>0)
				batchInsertFlag = roleResourceService.insertBatch(datas);


			boolean addFlag = super.insertBatch(rms);
			return deleteFlag && addFlag && batchInsertFlag;
		}
		return false;
	}
}
