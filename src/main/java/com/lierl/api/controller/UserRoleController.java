package com.lierl.api.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.lierl.api.entity.UserRole;
import com.lierl.api.service.IUserRoleService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author lierl
 * @create 2017-08-04 11:02
 **/
@RestController
@RequestMapping("/api")
public class UserRoleController {

	private final static Logger logger = LoggerFactory.getLogger(UserRoleController.class);

	@Autowired
	IUserRoleService userRoleService;

	@GetMapping("/userRole/addOrUpdate")
	public Map<String,Object> addOrUpdate(@RequestParam String ids,@RequestParam String userId){
		Map<String,Object> results = Maps.newHashMap();
		results.put("result","error");
		try {
			boolean bool = userRoleService.addOrUpdate(ids,userId);
			if(bool){
				results.put("result","success");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return results;
	}
}
