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
	
	private int id; //组卷Id
	private String testpaperName;//组卷名称
	private Date createTime;//创建时间
	private String knowledgePointArrVal;//知识点
	private String difficulty;//难易度
	private String digest;//简介
	private int userId;//创建人用户id
	private String nickname;//穿件人昵称
	private String type;//习题分类
	private int exerciseCount;//习题个数
	private int examineStatus =2;//APP端显示或者隐藏 0.隐藏 1.显示
	private String subject;//科目
	private String grade;//年级
	private String realName;//审核人名称
	private int subjectNo;
	private int gradeNo;
	private int difficultyNo;
	private int confine;//0隐藏 1显示

	
	public int getConfine() {
		return confine;
	}
	public void setConfine(int confine) {
		this.confine = confine;
	}
	private int typeNo;
	public int getTypeNo() {
		return typeNo;
	}
	public void setTypeNo(int typeNo) {
		this.typeNo = typeNo;
	}
	public int getSubjectNo() {
		return subjectNo;
	}
	public void setSubjectNo(int subjectNo) {
		this.subjectNo = subjectNo;
	}
	public int getGradeNo() {
		return gradeNo;
	}
	public void setGradeNo(int gradeNo) {
		this.gradeNo = gradeNo;
	}
	public int getDifficultyNo() {
		return difficultyNo;
	}
	public void setDifficultyNo(int difficultyNo) {
		this.difficultyNo = difficultyNo;
	}
	
	
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTestpaperName() {
		return testpaperName;
	}
	public void setTestpaperName(String testpaperName) {
		this.testpaperName = testpaperName;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getKnowledgePointArrVal() {
		return knowledgePointArrVal;
	}
	public void setKnowledgePointArrVal(String knowledgePointArrVal) {
		this.knowledgePointArrVal = knowledgePointArrVal;
	}
	public String getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}
	public String getDigest() {
		return digest;
	}
	public void setDigest(String digest) {
		this.digest = digest;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getExerciseCount() {
		return exerciseCount;
	}
	public void setExerciseCount(int exerciseCount) {
		this.exerciseCount = exerciseCount;
	}
	public int getExamineStatus() {
		return examineStatus;
	}
	public void setExamineStatus(int examineStatus) {
		this.examineStatus = examineStatus;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}

}
