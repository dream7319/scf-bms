package com.lierl.api.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.common.collect.Maps;
import com.lierl.api.base.ResponseData;
import com.lierl.api.entity.Resource;
import com.lierl.api.service.IResourceService;
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
 * @since 2017-08-04
 */
@RestController
@RequestMapping("/api")
public class ResourceController {

	private final static Logger logger = LoggerFactory.getLogger(ResourceController.class);

    @Autowired
    private IResourceService resourceService;

	/**
	* 查询全部
	*/
	@GetMapping("/resource/lists")
    public Map<String,Object> getResources(){
		Map<String,Object> results = Maps.newHashMap();
		List<Resource> resources = resourceService.selectList(null);
		results.put("resource",resources);
		return results;
    }

	/**
	* 分页查询列表
	*/
	@GetMapping("/resource/list")
    public Map<String,Object> getResourceByPages(@RequestParam(value="pageNum",defaultValue = "1") Integer pageNum,
						@RequestParam(value="pageSize",defaultValue = "10") Integer pageSize){
		Map<String,Object> results = Maps.newHashMap();
		EntityWrapper<Resource> wrapper = new EntityWrapper<Resource>();

		Page<Resource> pages = resourceService.getAllResources(new Page<Resource>(pageNum, pageSize));
		results.put("resources",new ResponseData<Resource>(pages));
		return results;
	}

	/**
	* 根据id查询
	*/
	@GetMapping("/resource")
	public Map<String,Object> getResourceById(@RequestParam Integer id){
		Map<String,Object> results = Maps.newHashMap();
		if(StringUtils.isNotEmpty(ObjectUtils.toString(id))){
			Resource resource = resourceService.selectResourceById(id);
			results.put("resource",resource);
		}else {
			results.put("resource", new Resource());
		}
		return results;
    }

	/**
	* 添加
	*/
	@PostMapping("/resource/add")
    public Map<String,Object> insertResource(@RequestBody Resource resource){
		Map<String,Object> results = Maps.newHashMap();
		results.put("result","error");
		try{
			if(resource != null){
				resource.setCreateTime(new Date());
                Integer num = resourceService.insertResource(resource);
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
	@DeleteMapping("/resource/delete/{id}")
	public Map<String,Object> deleteResourceById(@PathVariable("id") Integer id){
		Map<String,Object> results = Maps.newHashMap();
		results.put("result","error");
		try {
			if(StringUtils.isNotEmpty(ObjectUtils.toString(id))){
				int num = resourceService.deleteResourceById(id);
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
	@PutMapping("/resource/update")
	public Map<String,Object> updateResourceById(@RequestBody Resource resource){
		Map<String,Object> results = Maps.newHashMap();
		results.put("result","error");
		try {
			if(resource != null){
				int num = resourceService.updateResourceById(resource);
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
