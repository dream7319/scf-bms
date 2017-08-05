package com.lierl.api.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 *
 * @author lierl
 * @since 2017-08-04
 */
@TableName("scf_resource")
public class Resource implements Serializable {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
	private Integer menuId;
	private String resourceName;
	private String resourceUrl;
	private Date createTime;
	private Boolean resourceStatus;
	private Boolean menuStatus;

	private transient String menuName;

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

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

	public Date getCreateTime() {
		return createTime;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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
