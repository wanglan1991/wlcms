package com.ekt.cms.menu.entity;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

/**
 * CMS menu 实体类 author : chenxin create date : 2016.4.6
 * 
 * @ hibernate validation 验证 验证规则请参考 src/main/resources/doc 文件夹下面的hibernateValidation.txt
 **/
public class CmsMenu implements Serializable {
	private static final long serialVersionUID = -4969335067707922527L;

	@NotNull(message = "主键不能为空")
	private Integer id;

	@NotBlank(message = "菜单名称不能为空")
	private String menuName;

	/** 菜单URL访问地址 **/
	private String url;

	/** 菜单父级ID **/
	@NotNull(message = "父级ID不能为空")
	private Integer parentId;

	/** 菜单当前级别排序 **/
	@NotNull(message = "序号不能为空")
	private Integer orderNo;

	/** 菜单层级 **/
	private Integer level;

	public List<CmsMenu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<CmsMenu> menuList) {
		this.menuList = menuList;
	}

	/** 菜单状态 0 不可用 1 可用 **/
	@NotNull(message = "菜单状态不能为空")
	private Integer status;

	private String remark;
	/**
	 * 图标
	 */
	private String icon;
	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	/**
	 * 子菜单
	 */
	private List<CmsMenu> menuList;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}