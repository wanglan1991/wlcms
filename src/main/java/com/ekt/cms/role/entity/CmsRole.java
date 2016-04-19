package com.ekt.cms.role.entity;

import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;

/**
 * CMS 角色实体类
 * @author wanglan
 */
public class CmsRole {
	/** 角色ID**/
	private Integer id;
	
	@NotBlank(message="角色编码不能为空！")
	private String encoding;
	/** 角色名称**/
	@NotBlank(message="角色名称不能为空！")
	private String name;
	/** 角色状态**/
	private Integer status;
	/**创建时间**/
	private Date createTime;
	/**权限ID**/
	private Integer permissionId;
	
	public Integer getPermissionId() {
		return permissionId;
	}
	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEncoding() {
		return encoding;
	}
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
}
