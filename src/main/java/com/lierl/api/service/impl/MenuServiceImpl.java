package com.lierl.api.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.lierl.api.entity.Menu;
import com.lierl.api.entity.User;
import com.lierl.api.mapper.MenuMapper;
import com.lierl.api.service.IMenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
}
