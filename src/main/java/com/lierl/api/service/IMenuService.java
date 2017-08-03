package com.lierl.api.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.lierl.api.entity.Menu;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author lierl
 * @since 2017-08-02
 */
public interface IMenuService extends IBaseService<Menu> {

	Integer insertMenu(Menu entity) throws Exception;

	Integer updateMenuById(Menu entity) throws Exception;

	Integer deleteMenuById(Serializable id) throws Exception;

	Integer deleteMenuByIds(List<Serializable> ids) throws Exception;

	Page<Menu> getAllMenus(Page<Menu> page);

	Menu selectMenuById(Integer id);
}
