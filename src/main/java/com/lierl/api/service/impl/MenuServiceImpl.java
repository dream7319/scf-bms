package com.lierl.api.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.google.common.collect.Lists;
import com.lierl.api.entity.Menu;
import com.lierl.api.mapper.MenuMapper;
import com.lierl.api.service.IMenuService;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
/**
 *
 * @author lierl
 * @since 2017-07-31
 */
@Service
public class MenuServiceImpl extends BaseServiceImpl<MenuMapper, Menu> implements IMenuService {


	@Transactional
	public Integer insertMenu(Menu menu) throws Exception{
		return baseMapper.insert(menu);
	}

	@Transactional
	public Integer updateMenuById(Menu menu) throws Exception{
		return baseMapper.updateById(menu);
	}

	@Transactional
	public Integer deleteMenuById(Serializable id) throws Exception{
		return baseMapper.deleteById(id);
	}

	@Transactional
	public Integer deleteMenuByIds(List<Serializable> ids) throws Exception{
		return baseMapper.deleteBatchIds(ids);
	}

	public Page<Menu> getAllMenus(Page<Menu> page){
		page.setRecords(baseMapper.getAllMenus(page));
		return page;
	}

	public Menu selectMenuById(Integer id){
		return baseMapper.selectMenuById(id);
	}

	@Override
	public List<Menu> selectMenusByRoleId(Integer roleId) {
		return baseMapper.selectMenusByRoleId(roleId);
	}

	@Override
	public List<Menu> selectMenusByUserId(Integer userId) {
		List<Menu> menus =  baseMapper.selectMenusByUserId(userId);

		if(menus != null && !menus.isEmpty()){
			List<Menu> finalMenus = getMenu(menus,0);
			return finalMenus;
		}
		return Lists.newArrayList();
	}

	private List<Menu> getMenu(List<Menu> menus,Integer parentId){
		List<Menu> childMenus = Lists.newArrayList();
		for (Menu menu : menus) {
			Integer pId = menu.getParentId();
			Integer menuId = menu.getId();
			if(pId == parentId){
				List<Menu> childNodes = getMenu(menus,menuId);
				menu.setSubMenus(childNodes);
				childMenus.add(menu);
			}
		}
		return childMenus;
	}
}
