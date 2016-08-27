/**   
* @Title: Quintessence.java 
* @Package com.ekt.cms.quintessence.entity 
* @Description: TODO(用一句话描述该文件做什么) 
* @author wanglan
* @date 2016年8月25日 下午3:43:46 
* @version V1.0   
*/
package com.ekt.cms.quintessence.entity;

import java.util.Date;

/** 
* @ClassName: Quintessence 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author wanglan
* @date 2016年8月25日 下午3:43:46 
*  
*/
public class CmsQuintessence {
	
	//主键
	private int id; 
	//创建时间
	private Date createTime;
	//类型
	private int contentType;
	//内容id 组卷或课程
	private int contentId;
	//发布日期
	private Date publishDate;
	//名称
	private String name;
	//内容数量
	private int totalCount;
	//类型
	private String type;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public int getContentType() {
		return contentType;
	}
	public void setContentType(int contentType) {
		this.contentType = contentType;
	}
	public int getContentId() {
		return contentId;
	}
	public void setContentId(int contentId) {
		this.contentId = contentId;
	}
	public Date getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

}
