package com.lierl.api.service;

import com.lierl.api.entity.Menu;
import java.io.Serializable;

import java.util.List;

/**
 *
 * @author lierl
 * @since 2017-07-31
 */
public interface IMenuService extends IBaseService<Menu> {

	Integer insertMenu(Menu entity) throws Exception;

	Integer updateMenuById(Menu entity) throws Exception;

	Integer deleteMenuById(Serializable id) throws Exception;

	Integer deleteMenuByIds(List<Serializable> ids) throws Exception;
}
