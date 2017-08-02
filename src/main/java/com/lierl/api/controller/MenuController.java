package com.lierl.api.controller;

import com.lierl.api.base.ResponseData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.common.collect.Maps;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.lierl.api.entity.Menu;
import com.lierl.api.service.IMenuService;

import java.util.Map;


/**
 *
 * @author lierl
 * @since 2017-07-31
 */
@RestController
@RequestMapping("/api")
public class MenuController {

	private final static Logger logger = LoggerFactory.getLogger(MenuController.class);

    @Autowired
    private IMenuService menuService;

	/**
	* 分页查询列表
	*/
	@GetMapping("/menu/list")
    public Map<String,Object> getMenus(@RequestParam(value="pageNum",defaultValue = "1") Integer pageNum,
						@RequestParam(value="pageSize",defaultValue = "10") Integer pageSize){
		Map<String,Object> results = Maps.newHashMap();
		EntityWrapper<Menu> wrapper = new EntityWrapper<Menu>();
		Page<Menu> pages = menuService.selectPage(new Page<Menu>(pageNum, pageSize),wrapper);
		results.put("data",new ResponseData<Menu>(pages));
		return results;
	}

	/**
	* 根据id查询
	*/
	@GetMapping("/menu")
	public Map<String,Object> getMenuById(@RequestParam Integer id){
		Map<String,Object> results = Maps.newHashMap();
		if(StringUtils.isNotEmpty(ObjectUtils.toString(id))){
			Menu menu = menuService.selectById(id);
			results.put("menu",menu);
		}else {
			results.put("menu", new Menu());
		}
		return results;
    }

	/**
	* 添加
	*/
	@PostMapping("/menu/add")
    public Map<String,Object> insertMenu(@RequestBody Menu menu){
		Map<String,Object> results = Maps.newHashMap();
		results.put("result","error");

		try{
			if(menu != null){
                Integer num = menuService.insertMenu(menu);
                if(num > 0){
                    results.put("result","success");
                }
		    }
		}catch(Exception e){
			logger.error("保存失败："+e.getMessage());
		}
        return results;
    }
	/**
	* 删除
	*/
	@DeleteMapping("/menu/delete/{id}")
	public Map<String,Object> deleteMenuById(@PathVariable("id") Integer id){
		Map<String,Object> results = Maps.newHashMap();
		results.put("result","error");
		try {
			if(StringUtils.isNotEmpty(ObjectUtils.toString(id))){
				int num = menuService.deleteMenuById(id);
				if(num > 0){
					results.put("result","success");
				}
			}
		}catch (Exception e){
			logger.error(e.getMessage());
		}
		return results;
	}

	/**
	* 更新
	*/
	@PutMapping("/menu/update")
	public Map<String,Object> updateMenuById(@RequestBody Menu menu){
		Map<String,Object> results = Maps.newHashMap();
		results.put("result","error");
		try {
			if(menu != null){
				int num = menuService.updateMenuById(menu);
				if(num > 0){
					results.put("result","success");
				}
			}
		}catch (Exception e){
			logger.error(e.getMessage());
		}
		return results;
	}
}
