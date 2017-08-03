package com.lierl.api.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.common.collect.Maps;

import com.lierl.api.entity.UserRole;
import com.lierl.api.service.IUserRoleService;


import java.util.List;
import java.util.Map;
import java.util.Date;

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
public class UserRoleController {

	private final static Logger logger = LoggerFactory.getLogger(UserRoleController.class);

    @Autowired
    private IUserRoleService userRoleService;

	/**
	* 查询全部
	*/
	@GetMapping("/userRole/lists")
    public Map<String,Object> getUserRoles(){
		Map<String,Object> results = Maps.newHashMap();
		List<UserRole> userRoles = userRoleService.selectList(null);
		results.put("userRole",userRoles);
		return results;
    }

	/**
	* 分页查询列表
	*/
	@GetMapping("/userRole/list")
    public Map<String,Object> getUserRoleByPages(@RequestParam(value="pageNum",defaultValue = "1") Integer pageNum,
						@RequestParam(value="pageSize",defaultValue = "10") Integer pageSize){
		Map<String,Object> results = Maps.newHashMap();
		EntityWrapper<UserRole> wrapper = new EntityWrapper<UserRole>();
		Page<UserRole> pages = userRoleService.selectPage(new Page<UserRole>(pageNum, pageSize),wrapper);
		results.put("userRole",new ResponseData<UserRole>(pages));
		return results;
	}

	/**
	* 根据id查询
	*/
	@GetMapping("/userRole")
	public Map<String,Object> getUserRoleById(@RequestParam Integer id){
		Map<String,Object> results = Maps.newHashMap();
		if(StringUtils.isNotEmpty(ObjectUtils.toString(id))){
			UserRole userRole = userRoleService.selectById(id);
			results.put("userRole",userRole);
		}else {
			results.put("userRole", new UserRole());
		}
		return results;
    }

	/**
	* 添加
	*/
	@PostMapping("/userRole/add")
    public Map<String,Object> insertUserRole(@RequestBody UserRole userRole){
		Map<String,Object> results = Maps.newHashMap();
		results.put("result","error");
		try{
			if(userRole != null){
                Integer num = userRoleService.insertUserRole(userRole);
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
	@DeleteMapping("/userRole/delete/{id}")
	public Map<String,Object> deleteUserRoleById(@PathVariable("id") Integer id){
		Map<String,Object> results = Maps.newHashMap();
		results.put("result","error");
		try {
			if(StringUtils.isNotEmpty(ObjectUtils.toString(id))){
				int num = userRoleService.deleteUserRoleById(id);
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
	@PutMapping("/userRole/update")
	public Map<String,Object> updateUserRoleById(@RequestBody UserRole userRole){
		Map<String,Object> results = Maps.newHashMap();
		results.put("result","error");
		try {
			if(userRole != null){
				int num = userRoleService.updateUserRoleById(userRole);
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
