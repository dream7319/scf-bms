package com.lierl.api.controller;

import com.google.common.collect.Maps;
import com.lierl.api.service.IRoleMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author lierl
 * @create 2017-08-04 15:25
 **/
@RestController
@RequestMapping("/api")
public class RoleMenuController {

	private final static Logger logger = LoggerFactory.getLogger(RoleMenuController.class);

	@Autowired
	IRoleMenuService roleMenuService;

	@GetMapping("/roleMenu/addOrUpdate")
	public Map<String,Object> addOrUpdate(@RequestParam String ids, @RequestParam String roleId){
		Map<String,Object> results = Maps.newHashMap();
		results.put("result","error");
		try {
			boolean bool = roleMenuService.addOrUpdate(ids,roleId);
			if(bool){
				results.put("result","success");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return results;
	}
}
