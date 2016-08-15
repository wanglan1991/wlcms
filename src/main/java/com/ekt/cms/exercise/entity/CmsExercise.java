package com.ekt.cms.exercise.entity;

import java.util.Date;

/**
 * 2016-04-25 15:35
 * 
 * @author wanglan
 *
 */

public class CmsExercise {
	// 主键
	private Integer id;
	// 题目类容
	private String content;
	// 知识点
	private Integer knowledgeId;
	// 知识内容
	private String knowledges;
	// 字典 类型
	private Integer typeNo;
	// 类型value
	private String type;
	// 题目类型id
	private Integer categoryNo;
	// 题目
	private String category;
	// 难易度id
	private Integer difficultyNo;
	// 难易度value
	private String difficulty;
	// 字典 出版社id
	private Integer publisherNo;
	// 出版社 value
	private String publisher;
	// 创建时间
	private Date createTime;
	// 状态
	private Integer status;
	// 作者
	private String author;
	// 排序
	private Integer orderNo;
	// 字典 科目id
	private Integer subjectNo;
	// 科目
	private String subject;
	// 字典 年级
	private Integer gradeNo;
	// 年级 value
	private String grade;
	//字典 学段
	private Integer phaseNo;
	//学段value
	
	public Integer getId() {
		return id;
	}
	public Integer getPhaseNo() {
		return phaseNo;
	}
	public void setPhaseNo(Integer phaseNo) {
		this.phaseNo = phaseNo;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public Integer getKnowledgeId() {
		return knowledgeId;
	}
	public void setKnowledgeId(Integer knowledgeId) {
		this.knowledgeId = knowledgeId;
	}
	public String getKnowledges() {
		return knowledges;
	}
	public void setKnowledges(String knowledges) {
		this.knowledges = knowledges;
	}
	public Integer getTypeNo() {
		return typeNo;
	}
	public void setTypeNo(Integer typeNo) {
		this.typeNo = typeNo;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getCategoryNo() {
		return categoryNo;
	}
	public void setCategoryNo(Integer categoryNo) {
		this.categoryNo = categoryNo;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Integer getDifficultyNo() {
		return difficultyNo;
	}
	public void setDifficultyNo(Integer difficultyNo) {
		this.difficultyNo = difficultyNo;
	}
	public String getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}
	public Integer getPublisherNo() {
		return publisherNo;
	}
	public void setPublisherNo(Integer publisherNo) {
		this.publisherNo = publisherNo;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	public Integer getSubjectNo() {
		return subjectNo;
	}
	public void setSubjectNo(Integer subjectNo) {
		this.subjectNo = subjectNo;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public Integer getGradeNo() {
		return gradeNo;
	}
	public void setGradeNo(Integer gradeNo) {
		this.gradeNo = gradeNo;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	

}
