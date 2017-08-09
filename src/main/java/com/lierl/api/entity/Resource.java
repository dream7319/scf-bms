package com.lierl.api.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import java.util.List;

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

	private transient String menuName;

	private transient List<Resource> subResources;

	public List<Resource> getSubResources() {
		return subResources;
	}

	public void setSubResources(List<Resource> subResources) {
		this.subResources = subResources;
	}

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

	public Resource() {
	}

	public Resource(Integer id, Integer menuId, String resourceName, String resourceUrl, Boolean resourceStatus, String menuName) {
		this.id = id;
		this.menuId = menuId;
		this.resourceName = resourceName;
		this.resourceUrl = resourceUrl;
		this.resourceStatus = resourceStatus;
		this.menuName = menuName;
	}

	@Override
	public String toString() {
		return "Resource{" +
				"id=" + id +
				", menuId=" + menuId +
				", resourceName='" + resourceName + '\'' +
				", resourceUrl='" + resourceUrl + '\'' +
				", resourceStatus=" + resourceStatus +
				", menuName='" + menuName + '\'' +
				'}';
	}
}
