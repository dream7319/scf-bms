package com.lierl.api.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.common.collect.Maps;
import com.lierl.api.base.ResponseData;
import com.lierl.api.entity.Role;
import com.lierl.api.service.IRoleService;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
public class RoleController {

	private final static Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private IRoleService roleService;

	/**
	 * 获取全部
	 * @return
	 */
	@GetMapping("/role/lists")
	public Map<String,Object> getRoles(){
		Map<String,Object> results = Maps.newHashMap();
		List<Role> roles = roleService.selectList(null);
		results.put("data",roles);
		return results;
	}

	/**
	* 分页查询列表
	*/
	@GetMapping("/role/list")
    public Map<String,Object> getRoleByPages(@RequestParam(value="pageNum",defaultValue = "1") Integer pageNum,
						@RequestParam(value="pageSize",defaultValue = "10") Integer pageSize){
		Map<String,Object> results = Maps.newHashMap();
		EntityWrapper<Role> wrapper = new EntityWrapper<Role>();
		Page<Role> pages = roleService.selectPage(new Page<Role>(pageNum, pageSize),wrapper);
		results.put("data",new ResponseData<Role>(pages));
		return results;
	}

	/**
	* 根据id查询
	*/
	@GetMapping("/role")
	public Map<String,Object> getRoleById(@RequestParam Integer id){
		Map<String,Object> results = Maps.newHashMap();
		if(StringUtils.isNotEmpty(ObjectUtils.toString(id))){
			Role role = roleService.selectById(id);
			results.put("role",role);
		}else {
			results.put("role", new Role());
		}
		return results;
    }

	/**
	* 添加
	*/
	@PostMapping("/role/add")
    public Map<String,Object> insertRole(@RequestBody Role role){
		Map<String,Object> results = Maps.newHashMap();
		results.put("result","error");
		try{
			if(role != null){
				role.setCreateTime(new Date());
                Integer num = roleService.insertRole(role);
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
	@DeleteMapping("/role/delete/{id}")
	public Map<String,Object> deleteRoleById(@PathVariable("id") Integer id){
		Map<String,Object> results = Maps.newHashMap();
		results.put("result","error");
		try {
			if(StringUtils.isNotEmpty(ObjectUtils.toString(id))){
				int num = roleService.deleteRoleById(id);
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
	@PutMapping("/role/update")
	public Map<String,Object> updateRoleById(@RequestBody Role role){
		Map<String,Object> results = Maps.newHashMap();
		results.put("result","error");
		try {
			if(role != null){
				role.setUpdateTime(new Date());
				int num = roleService.updateRoleById(role);
				if(num > 0){
					results.put("result","success");
				}
			}
		}catch (Exception e){
			logger.error(e.getMessage());
		}
		return results;
	}

	@GetMapping("/role/user/{id}")
	public Map<String,Object> selectRolesByUserId(@PathVariable Integer id){
		Map<String,Object> results = Maps.newHashMap();
		List<Role> roles = roleService.selectRolesByUserId(id);
		results.put("data",roles);
		return results;
	}
}
