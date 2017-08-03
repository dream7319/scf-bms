package com.lierl.api.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 *
 * @author lierl
 * @since 2017-08-03
 */
@TableName("scf_resource")
public class Resource implements Serializable {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
	private Integer menuId;
	private String resourceUrl;
	private Boolean resourceStatus;
	private Boolean menuStatus;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public String getResourceUrl() {
		return resourceUrl;
	}

	public void setResourceUrl(String resourceUrl) {
		this.resourceUrl = resourceUrl;
	}

	public Boolean isResourceStatus() {
		return resourceStatus;
	}

	public void setResourceStatus(Boolean resourceStatus) {
		this.resourceStatus = resourceStatus;
	}

	public Boolean isMenuStatus() {
		return menuStatus;
	}

	public void setMenuStatus(Boolean menuStatus) {
		this.menuStatus = menuStatus;
	}

}
