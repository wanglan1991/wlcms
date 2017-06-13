package com.ekt.cms.news.entity;

import java.util.Date;

/**
 * 动态咨询实体
 * @author wanglan
 * 
 *2017-02-24
 */
public class CmsNews {
	
	private int id;//主键
	private String title;//标题
	private String titleImageUrl;//标题图片
	private String content;//内容
	private int status;//状态
	private Date createTime;//创建时间
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTitleImageUrl() {
		return titleImageUrl;
	}
	public void setTitleImageUrl(String titleImageUrl) {
		this.titleImageUrl = titleImageUrl;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


}
