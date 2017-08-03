package com.lierl.api.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.common.collect.Maps;

import com.lierl.api.entity.RoleMenu;
import com.lierl.api.service.IRoleMenuService;

import java.util.Map;
import java.util.Date;
import java.util.List;

import com.lierl.api.base.ResponseData;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.ObjectUtils;

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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *
 * @author lierl
 * @since 2017-08-03
 */
@RestController
@RequestMapping("/api")
public class RoleMenuController {

	private final static Logger logger = LoggerFactory.getLogger(RoleMenuController.class);

    @Autowired
    private IRoleMenuService roleMenuService;

	/**
	* 查询全部
	*/
	@GetMapping("/roleMenu/lists")
    public Map<String,Object> getRoleMenus(){
		Map<String,Object> results = Maps.newHashMap();
		List<RoleMenu> roleMenus = roleMenuService.selectList(null);
		results.put("roleMenu",roleMenus);
		return results;
    }

	/**
	* 分页查询列表
	*/
	@GetMapping("/roleMenu/list")
    public Map<String,Object> getRoleMenuByPages(@RequestParam(value="pageNum",defaultValue = "1") Integer pageNum,
						@RequestParam(value="pageSize",defaultValue = "10") Integer pageSize){
		Map<String,Object> results = Maps.newHashMap();
		EntityWrapper<RoleMenu> wrapper = new EntityWrapper<RoleMenu>();
		Page<RoleMenu> pages = roleMenuService.selectPage(new Page<RoleMenu>(pageNum, pageSize),wrapper);
		results.put("roleMenu",new ResponseData<RoleMenu>(pages));
		return results;
	}

	/**
	* 根据id查询
	*/
	@GetMapping("/roleMenu")
	public Map<String,Object> getRoleMenuById(@RequestParam Integer id){
		Map<String,Object> results = Maps.newHashMap();
		if(StringUtils.isNotEmpty(ObjectUtils.toString(id))){
			RoleMenu roleMenu = roleMenuService.selectById(id);
			results.put("roleMenu",roleMenu);
		}else {
			results.put("roleMenu", new RoleMenu());
		}
		return results;
    }

	/**
	* 添加
	*/
	@PostMapping("/roleMenu/add")
    public Map<String,Object> insertRoleMenu(@RequestBody RoleMenu roleMenu){
		Map<String,Object> results = Maps.newHashMap();
		results.put("result","error");
		try{
			if(roleMenu != null){
                Integer num = roleMenuService.insertRoleMenu(roleMenu);
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
	@DeleteMapping("/roleMenu/delete/{id}")
	public Map<String,Object> deleteRoleMenuById(@PathVariable("id") Integer id){
		Map<String,Object> results = Maps.newHashMap();
		results.put("result","error");
		try {
			if(StringUtils.isNotEmpty(ObjectUtils.toString(id))){
				int num = roleMenuService.deleteRoleMenuById(id);
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
	@PutMapping("/roleMenu/update")
	public Map<String,Object> updateRoleMenuById(@RequestBody RoleMenu roleMenu){
		Map<String,Object> results = Maps.newHashMap();
		results.put("result","error");
		try {
			if(roleMenu != null){
				int num = roleMenuService.updateRoleMenuById(roleMenu);
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
