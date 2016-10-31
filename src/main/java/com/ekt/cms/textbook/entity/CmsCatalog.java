package com.ekt.cms.textbook.entity;

import java.util.List;

/**
 * 
 * @author wanglan 2016-05-05 教材目录实体类
 */
public class CmsCatalog {
	// 主键
	private Integer id;
	// 目录名称
	private String catalogName;
	// 教材id
	private Integer textbookId;
	// 排序
	private Integer orderNo;
	// 状态
	private Integer status;
	// 父级Id
	private Integer parentId;
	// 字典 目录级别
	private Integer catalogLevel;
	// 目录级别名称
	private String levelName;
	// 简介
	private String introduction;
	// 视频文件名
	private String videoFileName;
	// 目录集合
	private List<CmsCatalog> catalogList;

	public String getVideoFileName() {
		return videoFileName;
	}

	public void setVideoFileName(String videoFileName) {
		this.videoFileName = videoFileName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCatalogName() {
		return catalogName;
	}

	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}

	public Integer getTextbookId() {
		return textbookId;
	}

	public void setTextbookId(Integer textbookId) {
		this.textbookId = textbookId;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getCatalogLevel() {
		return catalogLevel;
	}

	public void setCatalogLevel(Integer catalogLevel) {
		this.catalogLevel = catalogLevel;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public List<CmsCatalog> getCatalogList() {
		return catalogList;
	}

	public void setCatalogList(List<CmsCatalog> catalogList) {
		this.catalogList = catalogList;
	}

	public CmsCatalog() {

	}

	public CmsCatalog(String catalogName, int textbookId, int orderNo, int parentId, int catalogLevel,
			String introduction, String videoFileName) {
		this.catalogName=catalogName;
		this.textbookId= textbookId;
		this.orderNo=orderNo;
		this.parentId=parentId;
		this.catalogLevel=catalogLevel;
		this.introduction=introduction;
		this.videoFileName=videoFileName;
	}

}
