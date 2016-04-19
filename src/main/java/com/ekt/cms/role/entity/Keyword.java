package com.ekt.cms.role.entity;
/**
 * 用作Mybatis 关键字查询类
 * @author 王岚
 *
 */
public class Keyword {
	/**
	 * 角色ID
	 */
  private int roleId;
  /**
   * 菜单父级Id
   */
  private int parentId;
  /**
   * 菜单Id
   */
  private int menuId;
public int getRoleId() {
	return roleId;
}
public void setRoleId(int roleId) {
	this.roleId = roleId;
}
public int getParentId() {
	return parentId;
}
public void setParentId(int parentId) {
	this.parentId = parentId;
}
public int getMenuId() {
	return menuId;
}
public void setMenuId(int menuId) {
	this.menuId = menuId;
}

}
