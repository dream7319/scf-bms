package com.lierl.api.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.common.collect.Maps;
import com.lierl.api.base.ResponseData;
import com.lierl.api.entity.Menu;
import com.lierl.api.service.IMenuService;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 *
 * @author lierl
 * @since 2017-08-02
 */
@RestController
@RequestMapping("/api")
public class MenuController {

	private final static Logger logger = LoggerFactory.getLogger(MenuController.class);

    @Autowired
    private IMenuService menuService;

	/**
	* 查询全部
	*/
	@GetMapping(value="/menu/lists",produces = "application/json")
    public Map<String,Object> getMenus(){
		Map<String,Object> results = Maps.newHashMap();
		List<Menu> menus = menuService.selectList(null);
		results.put("data",menus);
		return results;
    }

	/**
	* 分页查询列表
	*/
	@GetMapping("/menu/list")
    public Map<String,Object> getMenuByPages(@RequestParam(value="pageNum",defaultValue = "1") Integer pageNum,
						@RequestParam(value="pageSize",defaultValue = "10") Integer pageSize){
		Map<String,Object> results = Maps.newHashMap();
		Page<Menu> pages = menuService.getAllMenus(new Page<Menu>(pageNum, pageSize));
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
			Menu menu = menuService.selectMenuById(id);
			results.put("menu",menu);
		}else {
			results.put("menu", new Menu());
		}
		return results;
    }

    @GetMapping("/menu/queryKey")
    public Map<String,Object> queryKey(@RequestParam String key,@RequestParam String query){
		Map<String,Object> results = Maps.newHashMap();
		Wrapper<Menu> wrapper = new EntityWrapper<Menu>();
		if("parent".equals(query)){
			wrapper.where("menu_level={0}",1)
					.like("menu_name",key);
		}else{
			wrapper.like("menu_name",key);
		}

		List<Menu> menus = menuService.selectList(wrapper);
		results.put("data",menus);
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
				int level = menu.getMenuLevel();
				if(level == 1){
					menu.setParentId(0);
				}
				menu.setCreateTime(new Date());
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
				int level = menu.getMenuLevel();
				if(level == 1){
					menu.setParentId(0);
				}
				menu.setUpdateTime(new Date());
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

	@GetMapping("/menu/role/{id}")
	public Map<String,Object> selectMenusByRoleId(@PathVariable Integer id){
		Map<String,Object> results = Maps.newHashMap();
		List<Menu> menus = menuService.selectMenusByRoleId(id);
		results.put("menus",menus);
		return results;
	}

	@GetMapping("/menu/user/{id}")
	public Map<String,Object> selectMenusByUserId(@PathVariable Integer id){
		Map<String,Object> results = Maps.newHashMap();
		List<Menu> menus = menuService.selectMenusByUserId(id);
		results.put("menus",menus);
		return results;
	}
}
