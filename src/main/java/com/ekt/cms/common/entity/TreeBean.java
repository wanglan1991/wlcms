/**   
* @Title: TreeBean.java 
* @Package com.ekt.cms.common.entity 
* @Description: TODO(用一句话描述该文件做什么) 
* @author wanglan
* @date 2016年8月11日 下午3:08:02 
* @version V1.0   
*/
package com.ekt.cms.common.entity;

import java.util.Date;

/** 
* @ClassName: TreeBean 
* @Description: TODO(树插件对象) 
* @author wanglan
* @date 2016年8月11日 下午3:08:02 
*  
*/
public class TreeBean {
	
	private int id;
	
	private String chkDisabled;
	
	private String name; 
	
	private String oncheck;
	
	private String open; 
	
	private int pId; 
	
	private Date expireTime;
	
	private Date defaultTime;
	
	public Date getDefaultTime() {
		return defaultTime;
	}

	public void setDefaultTime(Date defaultTime) {
		this.defaultTime = defaultTime;
	}

	public Date getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}

	public int getpId() {
		return pId;
	}

	public void setpId(int pId) {
		this.pId = pId;
	}

	private String parent;
	
	private String checked;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getChkDisabled() {
		return chkDisabled;
	}

	public void setChkDisabled(String chkDisabled) {
		this.chkDisabled = chkDisabled;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOncheck() {
		return oncheck;
	}

	public void setOncheck(String oncheck) {
		this.oncheck = oncheck;
	}

	public String getOpen() {
		return open;
	}

	public void setOpen(String open) {
		this.open = open;
	}

	

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}
	
	
}