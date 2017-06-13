package com.ekt.cms.video.entity;

import java.util.Date;

public class Testpaper {
	private int id; 
	private int userId;
	private Date createTime;
	private String testpaperName;
	private int subjectNo; 
	private int phaseNo;
	private String knowledgePointArr;
	private String knowledgePointArrVal;
	private int difficultyNo;
	private String digest;
	private String author;
	private int type;
	private int isHot;
	private int categoryNo;
	private int examineStatus;
	private int videoId;
	
	
	public int getVideoId() {
		return videoId;
	}
	public void setVideoId(int videoId) {
		this.videoId = videoId;
	}	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getTestpaperName() {
		return testpaperName;
	}
	public void setTestpaperName(String testpaperName) {
		this.testpaperName = testpaperName;
	}
	public int getSubjectNo() {
		return subjectNo;
	}
	public void setSubjectNo(int subjectNo) {
		this.subjectNo = subjectNo;
	}
	public int getPhaseNo() {
		return phaseNo;
	}
	public void setPhaseNo(int phaseNo) {
		this.phaseNo = phaseNo;
	}
	public String getKnowledgePointArr() {
		return knowledgePointArr;
	}
	public void setKnowledgePointArr(String knowledgePointArr) {
		this.knowledgePointArr = knowledgePointArr;
	}
	public String getKnowledgePointArrVal() {
		return knowledgePointArrVal;
	}
	public void setKnowledgePointArrVal(String knowledgePointArrVal) {
		this.knowledgePointArrVal = knowledgePointArrVal;
	}
	public int getDifficultyNo() {
		return difficultyNo;
	}
	public void setDifficultyNo(int difficultyNo) {
		this.difficultyNo = difficultyNo;
	}
	public String getDigest() {
		return digest;
	}
	public void setDigest(String digest) {
		this.digest = digest;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getIsHot() {
		return isHot;
	}
	public void setIsHot(int isHot) {
		this.isHot = isHot;
	}
	public int getCategoryNo() {
		return categoryNo;
	}
	public void setCategoryNo(int categoryNo) {
		this.categoryNo = categoryNo;
	}
	public int getExamineStatus() {
		return examineStatus;
	}
	public void setExamineStatus(int examineStatus) {
		this.examineStatus = examineStatus;
	}
	
	
	

}
